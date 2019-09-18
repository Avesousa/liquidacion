package Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;


public class Feriados {
    
   private Date fecha = null;
   private int id;
   private String motivo = "";
   private DefaultTableModel tabla;
   private Object [] lista;
   private SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
   
   public Feriados(java.sql.Date _fecha, String _motivo, DefaultTableModel _tabla, int _id){
       this.id = _id;
       fecha = new Date(_fecha.getTime());
       motivo = _motivo;
       this.tabla = _tabla;
       this.agregarTabla();
   }
   
   public Feriados(java.sql.Date _fecha, String _motivo, int _id){
       this.id = _id;
       fecha = new Date(_fecha.getTime());
       motivo = _motivo;
   }
   
   public int darMes(){
       return this.fecha.getMonth();
   }
   
   public int darDia(){
       return this.fecha.getDate();
   }
   
   public Date darFecha(){
       return this.fecha;
   }
   
   private void agregarTabla(){
       lista = new Object[3];
       lista[1] = formato.format(fecha);
       lista[2] = motivo;
       lista[0] = id;
       this.tabla.addRow(lista);
      
   }
   
   
}
