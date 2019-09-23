package Conectores;

import java.sql.SQLException;
import java.sql.Date;
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
}