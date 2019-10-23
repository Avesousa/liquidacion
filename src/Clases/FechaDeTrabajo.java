package Clases;

import Conectores.dbPago;
import Conectores.dbTrabajador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FechaDeTrabajo implements Runnable {
    
    public int id;
    public int dias = 0;
    public Trabajador trabajador;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaSalida;
    

    public FechaDeTrabajo(int id, int dias, java.sql.Date fs, java.sql.Date fe) {
        this.id = id;
        this.dias = dias;
        this.fechaInicio = fs;
        this.fechaSalida = fe;
    }
    
    public FechaDeTrabajo(int id, java.sql.Date fs, java.sql.Date fe) {
        this.id = id;
        this.fechaInicio = fs;
        this.fechaSalida = fe;
    }

    @Override
    public String toString() {
        return "DiaTrabajado{" + "id=" + id + ", dias=" + dias + ", trabajador=" + trabajador + '}';
    }

    @Override
    public void run() {
        try {
            trabajador = (dias > 0 ? new dbTrabajador().traerTrabajadores(id,dias) : new dbTrabajador().traerTrabajadores(id));
            Condiciones con = new dbPago().traerCondiciones(id, fechaInicio, fechaSalida);
            trabajador.agregarMontoCondicional(con.getMonto());
            trabajador.setMetodoCondicional(con.getMetodo());
            System.out.println(trabajador);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error!");
        }
    }
    
    
}
