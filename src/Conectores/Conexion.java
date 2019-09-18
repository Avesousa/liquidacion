package Conectores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexion {
    private static final String PORT = "3306";
    private static final String HOST = "10.22.0.82";
    private static final String USERNAME = "avesousa";
    private static final String PASSWORD = "26390042Po.";
    private static final String URL = "jdbc:mysql://"+HOST+":"+PORT+"/?useSSL=false";
    public Connection conector = null;
    public ResultSet res = null;
    public PreparedStatement ps = null;
    public String sql;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conector = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
        } catch(Exception e){
            System.out.println("[CONEXION - ERROR]: " + e);
            e.printStackTrace();
        }
    }
    
    public void cerrarConexion(){
        try {
           if(ps != null)ps.close();
           if(res != null)ps.close();
           if(conector != null)conector.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
    }
}
