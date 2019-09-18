package Conectores;

import java.sql.SQLException;

public class dbPago extends Conexion{
    
    public double traerPago(String medio) throws SQLException{
            double valor = 0;
            sql = "SELECT monto FROM incentivo.pago WHERE medio = ?";
            ps = conector.prepareStatement(sql);
            ps.setString(1, medio);
            res = ps.executeQuery();
            while(res.next())
                valor = res.getDouble(1);
            return valor;
    }
}