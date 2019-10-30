package Conectores;

import Clases.Condiciones;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class dbPago extends Conexion{
    
    
    public double traerPago(String medio) throws SQLException{
            double valor = 0;
            sql = "SELECT monto FROM incentivo.sueldo WHERE medio = ?";
            ps = conector.prepareStatement(sql);
            ps.setString(1, medio);
            res = ps.executeQuery();
            while(res.next())
                valor = res.getDouble(1);
            return valor;
    }
    
    public boolean verificar(Date fS, Date fE){
        int valor = 0;
        try {
            sql = "SELECT * FROM incentivo.pago WHERE fecha_inicio BETWEEN '"+fS+"' AND '"+fE+"' OR fecha_final BETWEEN'"+fS+"' AND '"+fE+"'";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            if(res.next()){
               valor = JOptionPane.showConfirmDialog(null, "Ya existe generado un pago en las fechas colocadas, ¿Deseas Continuar?","Confirmación de pago",JOptionPane.YES_NO_OPTION);
                return (valor == JOptionPane.YES_OPTION);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbPago.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Se ha generado un error en Base de Datos...");
            return false;
        }
    }
    
    public Condiciones traerCondiciones(int id, Date fs, Date fe) throws SQLException{
        Condiciones valor = new Condiciones();
        sql = "SELECT monto, metodo FROM incentivo.condiciones WHERE id_asociado = " + id +
              " AND (fecha_inicio BETWEEN '" + fs + "' AND '" + fe + "'" +
              " OR fecha_final BETWEEN '" + fs + "' AND '" + fe + "') ORDER BY fecha_final";
        System.out.println(sql);
        ps = conector.prepareStatement(sql);
        res = ps.executeQuery();
        while(res.next()){
            try {
                if(res.getDouble(1) > 0)
                    valor.agregarMonto(res.getDouble(1));
                if(!res.getString(2).equals(""))
                    valor.agregarMetodo(res.getString(2));
            } catch (Exception e) {
                System.out.println("¡--!");
            }
            
        }
        
        
        return valor;
    }
    
    public int crearPago(Date fs, Date fe){
        try {
            sql = "INSERT INTO incentivo.pago(fecha_inicio,fecha_final) VALUES(?,?)";
            ps = conector.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, fs);
            ps.setDate(2, fe);
            ps.executeUpdate();
            res = ps.getGeneratedKeys();
            if(res.next())
                return res.getInt(1);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }    
    
    public void actualizarCondicion(int id){
        sql = "UPDATE incentivo.condiciones SET monto = 0 WHERE id_asociado = ?";
        try {
            ps = conector.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Ha generado un error en actualización de montos en condiciones!");
        }
        
    }    
}