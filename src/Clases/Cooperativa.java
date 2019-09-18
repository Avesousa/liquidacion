package Clases;

import javax.swing.table.DefaultTableModel;

public class Cooperativa {
    
    public int id;
    private String nombre;
    private String abreviatura;
    private DefaultTableModel tabla;
    private Object [] lista;

    public Cooperativa(int id, String nombre, String abreviatura, DefaultTableModel tabla) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.tabla = tabla;
        agregarTabla();
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

    

    
    
    
}
