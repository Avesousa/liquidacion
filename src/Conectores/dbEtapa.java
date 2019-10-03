package Conectores;

import Clases.Cooperativa;
import Clases.Etapas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dbEtapa extends Conexion{
    
    private String cv = "centroverde.cooperativas";
    private String in = "incentivo.cooperativas";
    private String pr = "presentismo_db.company";
    private DefaultTableModel tabla;
    
    public List<Etapas> traerEtapas(int id) throws SQLException {
            List<Etapas> etapas = new ArrayList();
            
            sql = "SELECT proyecto FROM incentivo.responsable WHERE coordinador = ? and presentismo = 0";
            ps = conector.prepareStatement(sql);
            ps.setInt(1, id);
            res = ps.executeQuery();
            while(res.next()){
                dbTrabajador co = new dbTrabajador();
                etapas.add(new Etapas(res.getString(1),
                        co.traerTrabajadores(res.getString(1))));
            }
            return etapas;
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
}
