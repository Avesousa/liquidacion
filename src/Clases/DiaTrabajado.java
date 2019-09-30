package Clases;

import Conectores.dbPago;
import Conectores.dbTrabajador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DiaTrabajado implements Runnable {
    
    public int id;
    public int dias;
    public Trabajador trabajador;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaSalida;
    

    public DiaTrabajado(int id, int dias, java.sql.Date fs, java.sql.Date fe) {
        this.id = id;
        this.dias = dias;
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
            trabajador = new dbTrabajador().traerTrabajadores(id,dias);
            Condiciones con = new dbPago().traerCondiciones(id, fechaInicio, fechaSalida);
            trabajador.agregarMontoCondicional(con.getMonto());
            trabajador.setMetodoCondicional(con.getMetodo());
        } catch (SQLException ex) {
            System.out.println("[DiaTrabajado]- " + ex);
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error!");
        }
    }
    
    
}
