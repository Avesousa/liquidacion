package Pagos;

import Clases.DiaTrabajado;
import Clases.Trabajador;
import Archivos.Excel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Listar implements Runnable {
    private List<DiaTrabajado> trabajadores;
    private String ruta;
    private Excel documento;
    private int dias;
    private boolean esCabal = false;
    
    public Listar(List<DiaTrabajado> trabajadores, String ruta, int diasHabiles, boolean cabal){
        this.trabajadores = trabajadores;
        this.ruta = ruta;
        this.dias = diasHabiles;
        this.esCabal = cabal;
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
                    documento.agregarCelda(0, documento.fila, true).setCellValue(i+1);
                    //APELLIDO Y NOMBRE
                    documento.agregarCelda(1, documento.fila, true).setCellValue(trabajador.getApellido() + " " + trabajador.getNombre());
                    //DNI
                    documento.agregarCelda(2, documento.fila, true).setCellValue(trabajador.getDocumento());
                    //CUENTA CABAL
                    if(esCabal) documento.agregarCelda(3, documento.fila, true).setCellValue(trabajador.getCabal());
                    else documento.agregarCelda(3, documento.fila, true).setCellValue(trabajador.getCbu());
                    //IMPORTE
                    documento.agregarCelda(4, documento.fila, true).setCellValue(Sueldo.formatear(Sueldo.hacer(trabajadores.get(i).dias, trabajador.sueldo, dias)));
                    //IMPORTE TOTAL
                    montoTotal += Double.parseDouble(Sueldo.formatear(Sueldo.hacer(trabajadores.get(i).dias, trabajador.sueldo, dias)));
                } catch (Exception e) {
                    System.out.println("ERROR EN LISTADO DE CABA");
                }
            }
            documento.agregarCelda(4, documento.agregarFila(documento.hoja.getLastRowNum())).setCellValue(Sueldo.formatear(montoTotal));
            documento.guardarArchivo(ruta+"/LISTADO" + (fecha.getDate()) + "-" + (fecha.getMonth()+1) + ".xlsx");
            documento = null;
        }
    }

    private void crearCabecera() {
        documento.fila = documento.hoja.createRow(0);
        String[] cabecera = {"QTY","APELLIDO Y NOMBRE","DNI","CUENTA","IMPORTE"};
        
        for(int i = 0; i < cabecera.length; i++){
            documento.fila.createCell(i).setCellValue(cabecera[i]);
        }
    }
    
}
