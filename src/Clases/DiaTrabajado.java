package Clases;

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

    public DiaTrabajado(int id, int dias) {
        this.id = id;
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "DiaTrabajado{" + "id=" + id + ", dias=" + dias + ", trabajador=" + trabajador + '}';
    }

    @Override
    public void run() {
        try {
            trabajador = new dbTrabajador().traerTrabajadores(this.id);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error!");
        }
    }
    
    
}
