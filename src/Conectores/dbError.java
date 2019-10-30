package Conectores;

import java.sql.Date;
import java.sql.SQLException;

public class dbError extends Conexion {
    
    public dbError(Date fecha, int employee, String error, String comentario,int user,String type) throws SQLException{
        
        sql = "INSERT INTO incentivo.error (id_asociado,error_tipo,comentario,fecha,usuario,tipo)"+
              "VALUES(?,?,?,?,?,?)";
        ps = conector.prepareStatement(sql);
        ps.setInt(1,employee);
        ps.setString(2, error);
        ps.setString(3, comentario);
        ps.setDate(4, fecha);
        ps.setInt(5, user);
        ps.setString(6, type);
        ps.executeUpdate();
        
    }
    
}
