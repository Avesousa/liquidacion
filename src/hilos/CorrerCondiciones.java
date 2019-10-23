package hilos;

import Clases.Condiciones;
import Clases.Trabajador;
import Conectores.dbPago;
import javax.swing.table.DefaultTableModel;

public class CorrerCondiciones{

    private java.sql.Date fs;
    private java.sql.Date fe;
    private Trabajador tr;
    private DefaultTableModel tabla;
    private dbPago con;
    
    public CorrerCondiciones(java.sql.Date fs, java.sql.Date fe, Trabajador t, DefaultTableModel tabla, dbPago con){
        this.fs = fs;
        this.fe = fe;
        this.tr = t;
        this.tabla = tabla;
        this.con = con;
    }
    
    public void acoplarCondiciones() {
        try {
            Condiciones condi = this.con.traerCondiciones(tr.getId(), fs, fe);
            tr.setMetodoCondicional(condi.getMetodo());
            tr.agregarMontoCondicional(condi.getMonto());
            coloca();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void coloca(){
        tr.colocarTabla(tabla, false);
    }
    
    
    
}
