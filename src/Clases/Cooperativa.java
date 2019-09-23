package Clases;

import javax.swing.table.DefaultTableModel;

public class Cooperativa {
    
    public int id;
    private String nombre;
    private String abreviatura;
    private DefaultTableModel tabla;
    private Object [] lista;
    private String razon;
    private double monto = 0;
    private int asociados = 0;

    public Cooperativa(int id, String nombre, String abreviatura, DefaultTableModel tabla) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.tabla = tabla;
        agregarTabla();
    }
    
    public Cooperativa(int id, String nombre, String abreviatura, String razon) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.razon = razon;
    }
    
    public Cooperativa(int id, String nombre, String abreviatura) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.tabla = tabla;
    }
    
    
    private void agregarTabla(){
       lista = new Object[2];
       lista[1] = nombre;
       lista[0] = id;
       this.tabla.addRow(lista);
      
   }
    
    public void add(){
        asociados++;
    }
    
    public void addMonto(double monto){
        this.monto += monto;
    }
    
    public int getAsociados(){
        return asociados;
    }
    
    @Override
    public String toString() {
        return "La cooperativa " + nombre + " tiene " + asociados + " asociados y un monto de " + this.monto;
    }
    
    
    
    public double getMonto(){
        return monto;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getRazon() {
        return razon;
    }

    

    
    
    
}
