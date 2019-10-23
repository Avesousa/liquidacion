/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import Clases.Fecha;
import javax.swing.table.DefaultTableModel;
import ventanas.CrearCondiciones;

/**
 *
 * @author 20956852766
 */
public class TraerTrabajadores implements Runnable{

    private CrearCondiciones condi;
    
    public TraerTrabajadores(CrearCondiciones condi){
        this.condi = condi;
        this.condi.esPrimeraVez = false;
    }
    
    @Override
    public void run() {
        java.sql.Date fechaInicial = Fecha.convertir(condi.DateStart.getDate());
        java.sql.Date fechaFinal = Fecha.convertir(condi.DateEnd.getDate());
        DefaultTableModel tabla = (DefaultTableModel) condi.tabla.getModel();
        condi.coTrabajadores.traerRecuperadores(tabla, fechaInicial, fechaFinal, condi);
        condi.trabajadores = condi.coTrabajadores.darListaTrabajadores();
        
    }
    
}
