package Clases;

import Pagos.Sueldo;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Trabajador{
    
    private List informacion = new ArrayList();
    public double sueldo;
    public String tipo;
    private double montoCondicional = 0;
    private String metodoCondicional = "";
    private double montoCobrar;
    public int diasTrabajados;
    
    //Datos de las personas
    private int id;
    private int documento;
    private String nombre;
    private String apellido;
    private String tipoR;
    private String funcion;
    private String ubicacion;
    private String division;
    private String cooperativa;
    private int coop;
    private String cbu = "";
    private String cabal = "";
    private String rai = "";
    private String genero;
    private String correo;
    private int bolson;
    private String turno;
    private long cuil;
    private Date fecha;
    private int rur;
    private String telefono;
    private List<JComboBox> select;
    

    public Trabajador(int id, int documento, String nombre, String apellido, String tipoR, String funcion, String ubicacion, String division, String cooperativa, String cbu, String cabal, int bolson, String turno, long cuil, Date fecha, String correo, String genero, int rur, String telefono) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoR = tipoR;
        this.funcion = funcion;
        this.ubicacion = ubicacion;
        this.division = division;
        this.cooperativa = cooperativa;
        this.cbu = cbu;
        this.cabal = cabal;
        this.bolson = bolson;
        this.turno = turno;
        this.cuil = cuil;
        this.fecha = fecha;
        this.correo = correo;
        this.genero = genero;
        this.rur = rur;
        this.telefono = telefono;
    }
    public Trabajador(int id, long doc, String nom, String apel, String tipo, String funcion, String ubicacion, double monto){
        
        informacion.add(String.valueOf(id));
        informacion.add(String.valueOf(doc));
        informacion.add(nom);
        informacion.add(apel);
        informacion.add(funcion);
        informacion.add(tipo);
        informacion.add(ubicacion);
        sueldo = monto;
        tipo = tipo;
        this.id = id;
        this.cuil = doc;
        this.nombre = nom;
        this.apellido = apel;
        this.tipoR = tipo;
        this.funcion = funcion;
        this.ubicacion = ubicacion;
        
    } 
    public Trabajador(int id, long doc, String nom, String apel, String tipo, String funcion, String ubicacion, double monto, String cbu, String cabal, int dni, int cooperativa, int dias, String rai){
        
        sueldo = monto;
        this.id = id;
        this.cuil = doc;
        this.nombre = nom;
        this.apellido = apel;
        this.tipoR = tipo;
        this.funcion = funcion;
        this.ubicacion = ubicacion;
        this.cbu = cbu == null ? "" : cbu;
        this.cabal = cabal == null ? "" : cabal;
        this.documento = dni;
        this.coop = cooperativa;
        this.diasTrabajados = dias;
        this.rai = rai == null ? "" : rai;
        
    } 
    
    
    public List dameDatos(){
        return informacion;
    }
    
    //Tomador de información
    public int getCoop(){
        return coop;
    }
    public int getId() {
        return id;
    }
    public int getDocumento() {
        return documento;
    }
    public long getCuil(){
        return this.cuil;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getCbu() {
        return cbu;
    }
    public String getCabal() {
        return cabal;
    }
    public String getRai(){
        return this.rai;
    }
    public int getRur() {
        return rur;
    }
    public double getMontoCondicional(){
        return montoCondicional;
    }
    public String getMetodoCondicional(){
        return metodoCondicional;
    }
    
    //Insertación de información
    
    public void setMetodoCondicional(String metodo){
        metodoCondicional = metodo;
    } 
    public void agregarMontoCondicional(double monto){
        montoCondicional += monto;
    }
    public double montoCobrar(int diasHabiles){
    montoCobrar = Sueldo.hacer(diasTrabajados, sueldo, diasHabiles) + this.montoCondicional;
        return montoCobrar;
    }
    
    public void colocarTabla(DefaultTableModel tabla){
        Object [] lista = new Object[9];
                lista[0] = id;
                lista[1] = nombre;
                lista[2] = apellido;
                lista[3] = documento;
                lista[4] = tipoR;
                lista[5] = cooperativa;
                lista[6] =  funcion;
                lista[7] = ubicacion;
                lista[8] = division;
                tabla.addRow(lista);
    }
    public void colocarInput( List<JTextField> inputs,List<JComboBox> select){
        
        inputs.get(0).setText(apellido);
        inputs.get(1).setText(cabal);
        inputs.get(2).setText(cbu); 
        inputs.get(3).setText(String.valueOf(cuil));
        inputs.get(4).setText(String.valueOf(documento));
        inputs.get(5).setText(correo);
        inputs.get(6).setText(nombre);
        inputs.get(7).setText(String.valueOf(rur));
        inputs.get(8).setText(telefono);
        inputs.get(9).setText(String.valueOf(id));
        
        select.get(0).setSelectedIndex(buscador(cooperativa,select.get(0)));
        select.get(2).setSelectedIndex(buscador(funcion,select.get(2)));
        select.get(3).setSelectedIndex(buscador(genero,select.get(3)));
        select.get(5).setSelectedIndex(buscador(tipoR,select.get(5)));
        this.select = select;
        
    }    
    public void colocarProcedencia(){
        select.get(4).setSelectedIndex(buscador(ubicacion,select.get(4)));
    }
    public void colocarDivision(){
        try {
            select.get(1).setSelectedIndex(buscador(division,select.get(1)));
        } catch (Exception e) {
            System.out.println("No tiene ninguna division");
        }
    }
    public Date fecha(){
        System.out.println(this.fecha);
        return this.fecha;
    }   
    private int buscador(String texto,JComboBox select){
        for(int i = 0; i < select.getItemCount(); i++)
            if(texto.equals(select.getItemAt(i).toString()))
                return i;
        return 0;
    }
    
    @Override
    public String toString() {
        return "Trabajador{" + "sueldo=" + sueldo + ", id=" + id + ", documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoR=" + tipoR + ", funcion=" + funcion + ", ubicacion=" + ubicacion + ", division=" + division + ", cooperativa=" + cooperativa + ", cbu=" + cbu + ", cabal=" + cabal + ", bolson=" + bolson + ", turno=" + turno + ", cuil=" + cuil + ", fecha=" + fecha + '}';
    }

    
    
    
    
    
}
