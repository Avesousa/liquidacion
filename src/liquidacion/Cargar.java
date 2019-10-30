package liquidacion;

import Conectores.dbError;
import Conectores.dbPresente;
import Archivos.Excel;
import Clases.Fecha;
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
    private int recuperadores;
    private String tipo;

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
        for(int hoja = 0; hoja < lista.size(); hoja++){
            System.out.println(lista.get(hoja));
            doc = new Excel(lista.get(hoja));
            doc.traerHoja(0);
            dias = doc.traerFila(3).getCell(3).getNumericCellValue();
            recuperadores =(int)doc.traerFila(2).getCell(3).getNumericCellValue();
            tipo = doc.traerFila(4).getCell(3).getStringCellValue();
            for(int fila = 0; fila < recuperadores; fila++){
                doc.fila = doc.traerFila(fila+7);
                try {
                    int id = Integer.parseInt(doc.fila.getCell(0).getStringCellValue());
                    escribirComentario(fila,id);
                    CellType tipoCelda = doc.fila.getCell((7+(int)dias)).getCachedFormulaResultType();
                    if(tipoCelda == CellType.NUMERIC){
                        for(int j = 7; j < dias+7; j++ ){
                            doc.fila = doc.traerFila(fila+7);
                            destinador(doc.fila.getCell(j).getNumericCellValue(),id,doc.fila.getCell(j).getColumnIndex());
                        }
                    } else {
                        for(int j = 7; j < dias+7; j++){
                            doc.fila = doc.traerFila(fila+7);
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
                barra.setValue((fila+1)*100/recuperadores);

            }
        }
    }     
    
    private void escribirComentario(int fila, int id){
        
        CellType tipo = doc.traerFila(fila+6).getCell(8+(int)dias).getCellType();
        String valor;
        System.out.println(tipo);
        if(tipo == CellType.NUMERIC)
            valor = String.valueOf(doc.traerFila(fila+7).getCell(9+(int)dias).getNumericCellValue());
        else
            valor = doc.traerFila(fila+7).getCell(9+(int)dias).getStringCellValue();

        try {
            if(!valor.equals("") || !valor.equals(" ")){
                new dbError(Fecha.convertir(new Date()), id, "COMENTARIO EN PLANILLA", valor, user, this.tipo);
                respuesta.append("[CARGANDO] - CARGANDO COMENTARIO: " + valor + " de " + id + "\n" );
            }
                
        } catch (Exception e) {
            respuesta.append("[CARGANDO] - ERROR AL CARGAR COMENTARIO de " + id + "\n");
        }
    
    }
 
    
    private void destinador(double valor, int id, int col){
        int dia = (int)doc.traerFila(6).getCell(col).getNumericCellValue();
        int mes = (int)doc.traerFila(4).getCell(col).getNumericCellValue();
        int anio = 0;
        Date fechaActual = new Date();
        int mesActual = (fechaActual.getMonth()+1);
        if(mes > mesActual)
            anio = fechaActual.getYear()-1;
        else
            anio = fechaActual.getYear();
        java.sql.Date fecha = new java.sql.Date(anio,mes-1,dia);
        dbPresente co = new dbPresente();
        co.agregarAreaDeTexto(respuesta);
        co.datos(fecha,id,user,tipo,(int)dias);
        switch((int)valor){
            case(1):
                co.definir("asistencia");
                break;
            case(2):
                co.definir("certificado");
                break;
            case(3):
                co.definir("sancion");
                break;
            case(0):
                return;
            default:
                try {
                    new dbError(fecha, id, "ASISTENCIA ERRÓNEA", "EL IDENTIFICADOR DE LA ASISTENCIA ES ERRONEO", user,tipo);
                    respuesta.append("[CARGANDO] - ASISTENCIA ERRÓNEA DE: " + id + "\n" );
                } catch (Exception e) {
                    respuesta.append("[CARGANDO] - ERROR AL CARGAR UNA ASISTENCIA ERRÓNEA: " + e + "\n" );
                }
                return;
        }
        try {
            new Thread(co).start();
        } catch (Exception e) {
            respuesta.append("[CARGANDO] - HUBO UN ERROR: " + e + "\n" );
        }
    }
    
}
