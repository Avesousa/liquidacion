package Pagos;

import Clases.FechaDeTrabajo;
import Clases.Trabajador;
import Archivos.Excel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Listar implements Runnable {
    private List<FechaDeTrabajo> trabajadores;
    private String ruta;
    private Excel documento;
    private int dias;
    private boolean esCabal = false;
    private String nombreDocumento;
    
    public Listar(List<FechaDeTrabajo> trabajadores, String ruta, int diasHabiles, boolean cabal){
        this.trabajadores = trabajadores;
        this.ruta = ruta;
        this.dias = diasHabiles;
        this.esCabal = cabal;
        this.nombreDocumento = cabal ? "/ListadoDeCabal " : "/ListadoDeCajaDeAhorro ";
    }
    
    @Override
    public void run() {
        Date fecha = new Date();
        if(trabajadores.size() > 0){
            documento = new Excel();
            documento.crearHoja("Listado");
            documento.proteger("dgrecpassword");
            crearCabecera();
            double montoTotal = 0;
            for(int i = 0; i < trabajadores.size(); i++){
                documento.fila = documento.hoja.createRow(i+1);
                Trabajador trabajador = trabajadores.get(i).trabajador;
                try {
                    //QTY
                    documento.agregarCelda(0, documento.fila).setCellValue(i+1);
                    documento.adaptarCelda(0);
                    //APELLIDO Y NOMBRE
                    documento.agregarCelda(1, documento.fila).setCellValue(trabajador.getApellido() + " " + trabajador.getNombre());
                    documento.adaptarCelda(1);
                    //DNI
                    documento.agregarCelda(2, documento.fila).setCellValue(esCabal ? trabajador.getDocumento() : trabajador.getCuil());
                    documento.adaptarCelda(2);
                    //CUENTA CABAL
                    documento.agregarCelda(3, documento.fila).setCellValue(esCabal ? trabajador.getCabal() : trabajador.getCbu());
                    documento.adaptarCelda(3);
                    //IMPORTE
                    documento.agregarCelda(4, documento.fila).setCellValue(Sueldo.formatear(Sueldo.hacer(trabajadores.get(i).dias, trabajador.sueldo, dias)));
                    documento.adaptarCelda(4);
                    //IMPORTE TOTAL
                    montoTotal += Double.parseDouble(Sueldo.formatear(Sueldo.hacer(trabajadores.get(i).dias, trabajador.sueldo, dias)));
                } catch (Exception e) {
                    System.out.println("ERROR EN LISTADO DE CABA");
                }
            }
            documento.agregarCelda(4, documento.agregarFila(documento.hoja.getLastRowNum()+1)).setCellValue(Sueldo.formatear(montoTotal));
            documento.guardarArchivo(ruta+ nombreDocumento + (fecha.getDate()) + "-" + (fecha.getMonth()+1) + ".xlsx");
            documento = null;
        }
    }

    private void crearCabecera() {
        documento.fila = documento.hoja.createRow(0);
        String[] cabecera = {"QTY","APELLIDO Y NOMBRE",esCabal ? "DNI" : "CUIL","CUENTA","IMPORTE"};
        
        for(int i = 0; i < cabecera.length; i++){
            documento.fila.createCell(i).setCellValue(cabecera[i]);
        }
    }
    
}
