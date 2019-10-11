package Conectores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class dbPresente extends Conexion implements Runnable {
    private Date date;
    private int employee;
    private int user;
    private int primaryKey = 0;
    private String tipo;
    private JTextArea area;
    private String[] asistio = {"",""};
    private String[] sancionado = {"",""};
    private String[] certificado = {"",""};
    
    private void actualizar(){
        this.certificado[0] =   "SELECT status FROM presentismo_db.certificate WHERE employee = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "' AND status = 'APPROVED'";
        this.certificado[1] =   "INSERT INTO presentismo_db.issue(user,type,name,date,comment,user_related,status) "+
                                "VALUES("+user+","+23+",'Sanción de planilla','"+ date + "'," +
                                "'Está sanción creada automáticamente desde las planillas'," + 
                                "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee +"),'APPROVED')";
        this.sancionado[0] =    "SELECT type FROM presentismo_db.issue WHERE user_related = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "' AND status = 'APPROVED'";
        this.sancionado[1] =    "INSERT INTO presentismo_db.certificate(user,date_from,date_to,status,supervisor,type,employee) "+
                                "VALUES("+user+",'"+date+"','"+date+"','APPROVED',"+user+","+6+","+
                                "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee + "))";
        this.asistio[0] =   "SELECT method FROM presentismo_db.assistance WHERE employee = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "'";
        this.asistio[1] =   "INSERT INTO presentismo_db.assistance (date,employee,user,method,certificate) "+
                            "VALUES('"+date+"',(SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+")"+
                            ","+user+",'PLANILLA',"+primaryKey+")";
    }
    public void datos(Date date, int employee, int user){
        this.date = date;
        this.employee = employee;
        this.user = user;
    }
    public void definir(String tipo){
        this.tipo = tipo;
    }
    public void agregarAreaDeTexto(JTextArea area){
        this.area = area;
    }
    private void hacer(){
        switch(tipo){
            case "asistencia":
                try {
                    crear(asistio[1],false);
                } catch (Exception e) {
                    area.append("Error en crear asistencia: " + e + "\n");
                }
                break;
                
            case "certificado":
                try {
                    crear(certificado[1],true);
                    actualizar();
                    crear(asistio[1],false);
                } catch (Exception e) {
                    area.append("Error en crear certificado: " + e + "\n");
                }
                break;
            
            case "sancion":
                try {
                    crear(sancionado[1],false);
                } catch (Exception e) {
                    area.append("Error en crear sanción: " + e + "\n");
                }
                break;
        }
        area.append("[" + tipo + "]: Se ha generado correctamente del id " + employee + " para el día " + date + "\n");
    }
    private void crear(String sql, boolean certificado) throws SQLException{
        ps = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        res = ps.getGeneratedKeys();
        if(certificado)
            if(res.next())
                primaryKey = res.getInt(1);
            
    }
    private boolean verificar() throws SQLException{
        return !ver(asistio[0]) && !ver(sancionado[0]) && !ver(certificado[0]);
    }
    private boolean ver(String sql) throws SQLException{
        ps = conector.prepareStatement(sql);
        res = ps.executeQuery();
        return res.next();
    }
    
    @Override
    public String toString() {
        return "dbPresente{" + "date=" + date + ", employee=" + employee + ", user=" + user + ", primaryKey=" + primaryKey + '}';
    }
    @Override
    public void run() {
        try {
            actualizar();
            if(verificar())
                hacer();
            else
                new dbError(date,employee,"Tiene registrado una fecha","El empleado ya tiene registrado una sanción, un certificado o un presente", user);
        } catch (Exception e) {
            area.append("Error en hacer: " + e + "\n");
        }
    }
}
