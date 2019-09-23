package liquidacion;

import Conectores.dbError;
import Conectores.dbPresente;
import Archivos.Excel;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import ventanas.CargarPlanillas;


public class Cargar extends Thread {
    private JTextArea respuesta;
    private JProgressBar barra;
    private JTextField ruta;
    private List<String> lista;
    private Excel doc;
    private double dias;
    private int user;
    private dbPresente co;
    private int recuperadores;

    public Cargar(List<String> lista, CargarPlanillas ob){
        super();
        this.lista = lista;
        respuesta = ob.areaDeRespuesta;
        barra = ob.barra;
        ruta = ob.ruta;
        user = ob.user;
    }
    
    @Override
    public void run() {
        barra.setVisible(true);
        System.out.println(lista);
        co = new dbPresente();
        for(int hoja = 0; hoja < lista.size(); hoja++){
            System.out.println(lista.get(hoja));
            doc = new Excel(lista.get(hoja));
            doc.traerHoja(0);
            dias = doc.traerFila(3).getCell(3).getNumericCellValue();
            recuperadores =(int)doc.traerFila(2).getCell(3).getNumericCellValue();
            for(int fila = 0; fila < recuperadores; fila++){
                doc.fila = doc.traerFila(fila+6);
                    try {
                        int id = Integer.parseInt(doc.fila.getCell(0).getStringCellValue());
                        CellType tipoCelda = doc.fila.getCell((7+(int)dias)).getCachedFormulaResultType();
                        if(tipoCelda == CellType.NUMERIC){
                            for(int j = 7; j < dias+7; j++ ){
                                doc.fila = doc.traerFila(fila+6);
                                double v = doc.fila.getCell(j).getNumericCellValue();
                                int b = doc.fila.getCell(j).getColumnIndex();
                                int f = doc.fila.getRowNum();
                                destinador(doc.fila.getCell(j).getNumericCellValue(),id,doc.fila.getCell(j).getColumnIndex());
                                System.out.println(id);
                                System.out.println(f);
                                //System.out.println(doc.fila.getCell(j).getColumnIndex());
                            }
                        } else {
                            for(int j = 7; j < dias+7; j++){
                                doc.fila = doc.traerFila(fila+6);
                                CellType celda = doc.fila.getCell(j).getCellType();
                                if(celda == CellType.NUMERIC){
                                    if(doc.fila.getCell(j).getNumericCellValue() > 0
                                       && doc.fila.getCell(j).getNumericCellValue() < 3)
                                       destinador((int)doc.fila.getCell(j).getNumericCellValue(),id, doc.fila.getCell(j).getColumnIndex());
                                    else if(doc.fila.getCell(j).getNumericCellValue() > 3)
                                        destinador(4,id,doc.fila.getCell(j).getColumnIndex());
                                }else
                                    destinador(4,id,doc.fila.getCell(j).getColumnIndex());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }        
 
    
    private void destinador(double valor, int id, int col){
        int dia = (int)doc.traerFila(5).getCell(col).getNumericCellValue();
        int mes = (int)doc.traerFila(3).getCell(col).getNumericCellValue();
        int anio = 0;
        Date fechaActual = new Date();
        int mesActual = (fechaActual.getMonth()+1);
        if(mes > mesActual)
            anio = fechaActual.getYear()-1;
        else
            anio = fechaActual.getYear();
        java.sql.Date fecha = new java.sql.Date(anio,mes-1,dia);
        co.datos(fecha,id,user);
        System.out.println(co);
        System.out.println(valor);
        try{
        switch((int)valor){
            case(1):
                co.crearAsistencia();
                System.out.println("ASISTIÓ");
                break;
            case(2):
                co.crearCertificado();
                co.crearAsistencia();
                System.out.println("LICENCIA");
                break;
            case(3):
                co.crearSancion();
                System.out.println("SANCIÓN");
                break;
            case(0):
                System.out.println("INACISTENTE");
                break;
            default:
                new dbError(fecha, id, "ASISTENCIA ERRÓNEA", "EL IDENTIFICADOR DE LA ASISTENCIA ES ERRONEO", user);
                System.out.println("ERRONEA");
                break;
        }
        }catch(Exception e){
            System.out.println("[ERROR] - Error en cargar asistencias");
            e.printStackTrace();
        }
    }
    
}
