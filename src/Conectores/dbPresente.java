package Conectores;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class dbPresente extends Conexion {
    
    private Date date;
    private int employee;
    private int user;
    private int primaryKey = 0;
    
    public void datos(Date date, int employee, int user){
        this.date = date;
        this.employee = employee;
        this.user = user;
        System.out.println("ID DE RECUPERADOR: " + this.employee);
    }
    
    public void crearAsistencia() throws SQLException{
        
        sql = "INSERT INTO presentismo_db.assistance (date,employee,user,method,certificate) "+
              "VALUES('"+date+"',(SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+")"+
                ","+user+",'PLANILLA',"+primaryKey+")";
        ps = conector.prepareStatement(sql);
        ps.executeUpdate();
    }
   
    public void crearCertificado() throws SQLException{
        
        sql = "INSERT INTO presentismo_db.certificate(user,date_from,date_to,status,supervisor,type,employee) "+
              "VALUES("+user+",'"+date+"','"+date+"','APPROVED',"+user+","+6+","+
              "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee + "))";
        ps = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        res = ps.getGeneratedKeys();
        while(res.next())
            primaryKey = res.getInt(1);
    }
    
    public void crearSancion() throws SQLException{
        
        sql = "INSERT INTO presentismo_db.issue(user,type,name,date,comment,user_related,status) "+
              "VALUES("+user+","+23+",'Sanción de planilla','"+ date + "'," +
              "'Está sanción creada automáticamente desde las planillas'," + 
              "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee +"),'APPROVED')";
        ps = conector.prepareStatement(sql);
        ps.executeUpdate();
    }

    @Override
    public String toString() {
        return "dbPresente{" + "date=" + date + ", employee=" + employee + ", user=" + user + ", primaryKey=" + primaryKey + '}';
    }
    
    
    
}
