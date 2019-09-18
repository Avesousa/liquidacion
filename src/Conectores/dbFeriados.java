package Conectores;

import Clases.Feriados;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class dbFeriados extends Conexion {
    
    public List<Feriados> lista = new ArrayList();
    
    public void traerFeriados(DefaultTableModel tabla){
        try {
            sql = "SELECT * FROM incentivo.feriados";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            while(res.next()){
                lista.add(
                new Feriados(res.getDate(2),res.getString(3),tabla,res.getInt(1)
                ));
            }
        } catch (Exception e) {
            System.out.println("[FERIADO-ERROR1]: " + e);
        }
    }
    
    public void traerFeriados(){
        try {
            sql = "SELECT * FROM incentivo.feriados";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            while(res.next()){
                lista.add(
                new Feriados(res.getDate(2),res.getString(3),res.getInt(1)
                ));
            }
        } catch (Exception e) {
            System.out.println("[FERIADO-ERROR1]: " + e);
        }
    }
    
    public void crearFeriados(DefaultTableModel tabla, Date fecha, String motivo){
        try {
            sql = "INSERT INTO incentivo.feriados (fecha,motivo) VALUES(?,?)";
            ps = conector.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(fecha.getTime()));
            ps.setString(2, motivo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("[FERIADO-ERROR2]: " + e);
        }
    }
    
    public void editarFeriados(Date fecha, int id, String motivo){
        try {
            sql = "UPDATE incentivo.feriados SET fecha = ?, motivo = ? WHERE id_feriado = ?";
            ps = conector.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(fecha.getTime()));
            ps.setString(2, motivo);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("[FERIADO-ERROR3]: " + e);
        }
    }
    
    public void eliminarFeriado(int id){
        try {
            sql ="DELETE FROM incentivo.feriados WHERE id_feriado = ?";
            ps = conector.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("[FERIADO-ERROR4]: " + e);
        }
    }

}
