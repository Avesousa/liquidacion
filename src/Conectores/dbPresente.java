package Conectores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String reclamo;
    private String usuario;
    private String type;
    private int days = 0;
    
    private void actualizar(){
        this.certificado[0] =   "SELECT id FROM presentismo_db.certificate WHERE employee = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "' AND status = 'APPROVED'";
        this.certificado[1] =   "INSERT INTO presentismo_db.certificate(user,date_from,date_to,status,supervisor,type,employee) "+
                                "VALUES("+user+",'"+date+"','"+date+"','APPROVED',"+user+","+6+","+
                                "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee + "))";
        this.sancionado[0] =    "SELECT id FROM presentismo_db.issue WHERE user_related = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "' AND status = 'APPROVED'";
        this.sancionado[1] =    "INSERT INTO presentismo_db.issue(user,type,name,date,comment,user_related,status) "+
                                "VALUES("+user+","+23+",'Sanción de planilla','"+ date + "'," +
                                "'Está sanción creada automáticamente desde las planillas'," + 
                                "(SELECT id FROM presentismo_db.user WHERE custom_id = " + employee +"),'APPROVED')";
        this.asistio[0] =   "SELECT id FROM presentismo_db.assistance WHERE employee = (SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+") AND date = '" + date + "'";
        this.asistio[1] =   "INSERT INTO presentismo_db.assistance (date,employee,user,method,certificate) "+
                            "VALUES('"+date+"',(SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+")"+
                            ","+user+",'PLANILLA',"+primaryKey+")";
        this.reclamo = "INSERT INTO incentivo.reclamo VALUES(NULL,NULL,'"+date+"',(SELECT id FROM presentismo_db.user WHERE custom_id = "+employee+"),'"+type+"','"+user+"','"+tipo+"')";
        this.usuario = "SELECT id FROM presentismo_db.user WHERE custom_id = "+employee;
    }
    public void datos(Date date, int employee, int user, String tipo, int days){
        this.date = date;
        this.employee = employee;
        this.user = user;
        this.type = tipo;
        this.days = days;
    }
    public void definir(String tipo){
        this.tipo = tipo;
    }
    public void agregarAreaDeTexto(JTextArea area){
        this.area = area;
    }
    
    private void hacer(){
        try {
            switch(tipo){
                case "asistencia":
                    crear(asistio[1],false);
                    break;
                case "certificado":
                    crear(certificado[1],true);
                    actualizar();
                    crear(asistio[1],false);
                    break;
                case "sancion":
                    crear(sancionado[1],false);
                    break;
            }
            if(type.equals("Reclamos")){
                try {
                    crear(reclamo,false);
                } catch (Exception e) {
                     area.append("ERROR - asociado: " + employee + " - RECLAMOS: "+ e + "\n");
                     new dbError(date,employee,"Error en: " + tipo,"Se ha generado el siguiente error: " + e, user, type);
                }
            }
            area.append("[" + tipo + "]: Se ha generado correctamente del id " + employee + " para el día " + date + "\n");
        } catch (SQLIntegrityConstraintViolationException vi){
            area.append("ERROR - asociado: " + employee + " - "+tipo+": No está definido el asociado y lo\n");
            try{
                new dbError(date,employee,"Error en: " + tipo,"El asociado no está definido en base de datos", user, type);
            } catch (SQLException ex) {
                area.append("ERROR - asociado: " + employee + " - Ocurrió un error al guardar el error\n");
                area.append("ERROR: " + ex);
            }
            
        } catch (Exception e) {
            area.append("ERROR - asociado: " + employee + " - "+tipo+": " + e + "\n");
            try {   
                new dbError(date,employee,"Error en: " + tipo,"Se ha generado el siguiente error: " + e, user, type);
            } catch (SQLException ex) {
                area.append("ERROR - asociado: " + employee + " - PROBLEMA EN EL ERROR: " + e + "\n");
                area.append("ERROR: " + ex);
            }
        }
        
    }
    private void crear(String sql, boolean certificado) throws SQLException{
        ps = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        System.out.println(sql);
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
            if(ver(this.usuario)){
                if(verificar())
                    hacer();
                else
                    new dbError(date,employee,"Tiene registrado una fecha","El asociado ya tiene un registro en está fecha", user, type);
            }else 
                area.append("ERROR - asociado: " + employee + " - "+tipo+": No está definido el asociado y lo\n");
                
        } catch (Exception e) {
            area.append("Error en hacer: " + e + "\n");
        }
    }
}
