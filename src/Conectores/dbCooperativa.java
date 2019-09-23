package Conectores;

import Clases.Cooperativa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class dbCooperativa extends Conexion{
    
    private String cv = "centroverde.cooperativas";
    private String in = "incentivo.cooperativas";
    private String pr = "presentismo_db.company";
    public List<Cooperativa> coops = new ArrayList();

    public void traerCooperativas(DefaultTableModel no){
        try {
            sql = "SELECT * FROM " + in;
            res = conector.prepareStatement(sql).executeQuery();
            while(res.next()){
                new Cooperativa(res.getInt(1), res.getString(2), res.getString(3),no);
            }
            System.out.println("[COOP-traer]: ¡Se han cargado las cooperativas correctamente!");
        } catch (Exception e) {
            System.out.println("[COOP-ERROR1]: " + e);
        }
    }    
    
    public List<Cooperativa> traerCooperativas() throws SQLException{
        List<Cooperativa> lista = new ArrayList();
        sql = "SELECT * FROM " + in + " ORDER BY id ASC";
        res = conector.prepareStatement(sql).executeQuery();
        while(res.next()){
            lista.add(
                    new Cooperativa(res.getInt(1), res.getString(2), res.getString(3),res.getString(6))
            );
        }
        return lista;
    }
    
    public void actualizarCooperativa(int id,boolean estado){
        try {
            sql = "UPDATE " + in + " SET presentismo = ? WHERE id = ?";
            ps = conector.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("[COOP-ERROR2]: " + e);
        }
    }
    
    public void agregarCooperativa(String nombre,String abreviatura){
        String [] va = {cv,in,pr};
        String [][] no = {{"nombre_cooperativa","abreviatura_cooperativa"},
            {"nombre","abreviatura"},
            {"name","abreviatura"}};
        for(int i = 0; i < va.length;i++){
            try {
                sql = "INSERT INTO " + va[i] + " ("+ no[i][0] + ", " + no[i][1] + ") "
                      + "VALUES('"+ nombre +"', '" + abreviatura + "')";
                System.out.println(sql);
                ps = conector.prepareStatement(sql);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("[COOP-ERROR"+(i+3)+"]: " + e);
            }
            
        }
        
        
    }
    
    public void llamarCooperativas(JComboBox caja){
        try {
            sql = "SELECT * FROM " + in;
            res = conector.prepareStatement(sql).executeQuery();
            caja.removeAllItems();
            caja.addItem("Selecciona la cooperativa");
            while(res.next()){
                caja.addItem(res.getString(3));
                coops.add(new Cooperativa(res.getInt(1), res.getString(2), res.getString(3)));
            }
            System.out.println("[COOP-llamar]: ¡Se han cargado las cooperativas correctamente!");
        } catch (Exception e) {
            System.out.println("[COOP-ERROR6]: " + e);
            e.printStackTrace();
        }
    } 
    
    
    
}
