package hilos;

import Clases.Condiciones;
import Clases.Trabajador;
import Conectores.dbPago;
import javax.swing.table.DefaultTableModel;

public class CorrerCondiciones implements Runnable {

    private java.sql.Date fs;
    private java.sql.Date fe;
    private Trabajador tr;
    private DefaultTableModel tabla;
    
    public CorrerCondiciones(java.sql.Date fs, java.sql.Date fe, Trabajador t, DefaultTableModel tabla){
        this.fs = fs;
        this.fe = fe;
        this.tr = t;
        this.tabla = tabla;
    }
    
    @Override
    public void run() {
        try {
            Condiciones condi = new dbPago().traerCondiciones(tr.getId(), fs, fe);
            tr.setMetodoCondicional(condi.getMetodo());
            tr.agregarMontoCondicional(condi.getMonto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void coloca(){
        tr.colocarTabla(tabla, false);
    }
    
    
    
}
