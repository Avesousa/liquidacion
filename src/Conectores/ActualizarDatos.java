package Conectores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ventanas.CrearCondiciones;

public class ActualizarDatos extends Conexion implements Runnable{

    private int id;
    private double monto;
    private String metodo;
    private java.sql.Date fs;
    private java.sql.Date fe;
    private CrearCondiciones clase;

    public void colocarDatos(int id, double monto, String metodo, java.sql.Date fs, java.sql.Date fe, CrearCondiciones clase) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
        this.fs = fs;
        this.fe = fe;
        this.clase = clase;
    }
    
    @Override
    public void run(){
        
        try {
            subirDatos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"[ACTUALIZAR DATOS]: Error con base de datos : " + ex);
        }
    }
    
    private void subirDatos() throws SQLException{
        
        sql = "INSERT INTO incentivo.condiciones VALUES (null,null,"+id+","+monto+",'"
              +metodo+"', '"+fs+"', '"+fe+"')";
        ps = conector.prepareStatement(sql);
        ps.executeUpdate();
    }
}
