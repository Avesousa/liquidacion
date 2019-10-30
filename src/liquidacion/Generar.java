
package liquidacion;

import Clases.Coordinador;
import Clases.Correo;
import Clases.DiasMesAnio;
import Archivos.Excel;
import java.util.List;
import Clases.Trabajador;
import Conectores.dbCoordinador;
import hilos.HacerBarra;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.apache.poi.ss.usermodel.Row;
import ventanas.CrearPlanilla;


public class Generar implements Runnable{
    
    private String ruta;
    private String nombre;
    private String fecha;
    private String coordinador;
    private Excel documento;
    List<Trabajador> datos;
    private List<DiasMesAnio> diasTrabajados;
    private CrearPlanilla ob;
    private JProgressBar barra;
    private JLabel texto;
    private String textoMail;
    private int CantidadDeRecuperadores = 0;
    public List<Coordinador> coordinadores;
    
    public Generar(String rutaR,String nombreHoja, List<DiasMesAnio> diasTrabajados, CrearPlanilla ob){
        super();
        ruta = rutaR;
        nombre = nombreHoja;
        fecha = nombreHoja;
        this.diasTrabajados = diasTrabajados;
        this.ob = ob;
        barra = ob.barraProgreso;
        texto = ob.textoProgreso;
        textoMail = ob.mensaje.getText();
        dbCoordinador coor = new dbCoordinador();
        try {
            coordinadores = coor.traerCoordinadores();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "[Coordinadores] Ha ocurrido el siguiente error: " + e);
        }
    }

    @Override
    public void run(){
        barra.setVisible(true);
        //barraMail.setVisible(true);
        
        for(int i = 0; i < coordinadores.size(); i++){
            Coordinador coor = coordinadores.get(i);
            coordinador = coor.getCoordinador();
            System.out.println("------ " + coor.getCoordinador() + " ------");
            if(coor.etapas.size() > 0){
                for(int etapa = 0; etapa < coor.etapas.size(); etapa++){
                    System.out.println("++++ " + coor.etapas.get(etapa).etapa + " ++++");
                    datos = coor.etapas.get(etapa).dameTrabajadores();
                    if(datos.size() > 0){
                        coor.etapas.get(etapa).agregarNombre(coor.etapas.get(etapa).etapa + " " + coor.getCoordinador() + " " + nombre + " - " + ob.tipo.getSelectedItem().toString()+".xlsx");
                        coor.etapas.get(etapa).agregarRuta(ruta);
                        documento = new Excel("plantillaPresentismo.xlsx"); // El libro que trae la plantilla.
                        documento.traerHoja(0); //La hoja correspondiente a la plantilla.
                        documento.proteger("dgrecpassword"); //Protección de hoja.
                        agregarCabecera();
                        for(int j = 0; j < datos.size(); j++){
                            documento.fila = documento.hoja.createRow(7+j);
                            for(int h = 0; h < datos.get(j).dameDatos().size();h++){
                                try {
                                    documento.agregarCelda(h, documento.fila).setCellValue(datos.get(j).dameDatos().get(h).toString());
                                    documento.adaptarCelda(h);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            new Thread(new HacerBarra(((j+1)*100)/datos.size(),barra));
                            texto.setText("Se está cargando " + coor.etapas.get(etapa).toString() + " " + (j+1) + "/" + datos.size());
                            deshabilitarCelda(datos.get(j).sueldo,documento.fila,(String)datos.get(j).dameDatos().get(5));
                        }
                        
                        documento.guardarArchivo(coor.etapas.get(etapa).dameRuta());
                        documento = null;
                        CantidadDeRecuperadores += datos.size();
                        texto.setText("Enviando correo de " + coor.getCoordinador() + " a " + coor.getCorreo());
                    }
                }
                    Correo correo = new Correo("avesousapersonal@gmail.com", "26390042");
                    try {
                        correo.Conectar(coor.getCorreo(), 
                                "Planillas de " + ob.tipo.getSelectedItem().toString() + " de "+ coor.getCoordinador() + ": " + fecha,
                                coor.etapas,
                                textoMail+
                                "\nFecha: " + fecha,
                                texto);
                        correo.start();
                        correo = null;
                                
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error de conexión!");
                    }
            }

        }
        barra.setVisible(false);
        texto.setText("La carga ha finalizado...");
        
    }

    private void agregarCabecera() {
        documento.fila = documento.hoja.getRow(1);
        documento.fila.getCell(3).setCellValue(coordinador);
        documento.fila = documento.hoja.getRow(2);
        documento.fila.getCell(3).setCellValue(datos.size());
        documento.fila = documento.hoja.getRow(3);
        documento.fila.getCell(3).setCellValue(diasTrabajados.size());
        documento.fila = documento.hoja.getRow(4);
        documento.fila.getCell(3).setCellValue(ob.tipo.getSelectedItem().toString());
        for(int i = 0; i < (diasTrabajados.size()); i++){
            documento.agregarCelda(i+7,documento.fila).setCellValue(diasTrabajados.get(i).mesEnt);
            documento.bloquear(true);
        }
        documento.fila = documento.hoja.getRow(5);
        for(int i = 0; i < (diasTrabajados.size()); i++){
            documento.definirAncho(i+7, 1300);
            documento.agregarCelda(i+7, documento.fila).setCellValue(diasTrabajados.get(i).dia);
            documento.bloquear(true);
        }
        documento.fila = documento.hoja.getRow(6);
        for(int i = 0; i < diasTrabajados.size(); i++){
            documento.agregarCelda(i+7,documento.fila).setCellValue(diasTrabajados.get(i).diaEnt);
            documento.bloquear(true);
        }
        String[] textos = {"Dias Trabajados", "Importe Total", "Observación"};
        for(int i = 0; i < 3; i++){
            documento.agregarCelda((i+7+diasTrabajados.size()),documento.fila).setCellValue(textos[i]);
            documento.definirAncho(documento.celda.getColumnIndex(), 9000);
            //documento.adaptarCelda(documento.celda.getColumnIndex());
            
        }
        
        
    }

    private void deshabilitarCelda(double sueldo, Row fila, String tipo) {
        int dias  = 6;
        for(int q = 0; q < diasTrabajados.size(); q++){
            int valor = 1;
            documento.agregarCelda(q+7,fila);
            if(!("CRONICO").equals(tipo)){
                documento.bloquear(false); 
                valor = 0;
            }
            documento.celda.setCellValue(valor);
            documento.celda = null;
            dias++;
        }
        documento.agregarCelda(diasTrabajados.size()+9, fila);
        documento.bloquear(false);
        documento.celda = null;
        colocarFormulas(dias, sueldo, fila, tipo);
        
   
    }
    
    private void colocarFormulas(int dias, double sueldo, Row fila, String tipo){
         
        //Agrega formula con respecto a los días
        documento.generarRango(fila.getRowNum(),7,dias);
        documento.celda = fila.createCell(dias+1);
        
        if(!("CRONICO").equals(tipo))
            documento.celda.setCellFormula(
                "IF(OR(COUNTIF("+documento.rango.formatAsString()+",\">3\")>0,"+
                "COUNTIF("+documento.rango.formatAsString()+",\"*\")>0)"+
                ",\"HAY UN ERROR\",COUNTIFS("+documento.rango.formatAsString()+",\"<3\","+
                documento.rango.formatAsString()+",\">0\"))");
        else
            documento.celda.setCellValue(diasTrabajados.size());
        
        //documento.formulaEvaluador();
        
        //Agregar fórmula con respecto al pago diario
        double pago = sueldo/diasTrabajados.size();
        documento.generarRango(fila.getRowNum(),(dias+1));
        documento.celda = fila.createCell(dias+2);
        
        if(!("CRONICO").equals(tipo))
            documento.celda.setCellFormula(
                "IF("+ documento.rango.formatAsString() + " <> \"HAY UN ERROR\"," +
                "ROUND(" + documento.rango.formatAsString() + "*" + pago + ",2),"+
                "\"HAY UN ERROR\")");
        else
            documento.celda.setCellValue(sueldo);
        //documento.formulaEvaluador();

    }
    
    
}

