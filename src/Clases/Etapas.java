package Clases;

import java.util.List;

public class Etapas {
    
    public String etapa;
    private String rutaArchivo = "";
    private String nombreArchivo = "";
    private List<Trabajador> trabajadores;

    public Etapas(String etapa, List<Trabajador> trabajadores) {
        this.etapa = etapa;
        this.trabajadores = trabajadores;
    }
    
    public void agregarRuta(String ruta){
        this.rutaArchivo = ruta;
    }
    
    public String dameRuta(){
        return this.rutaArchivo;
    }
    
    public List<Trabajador> dameTrabajadores(){
        return trabajadores;
    }
    
    public void agregarNombre(String n){
        this.nombreArchivo = n;
    }
    
    public String dameNombre(){
        return this.nombreArchivo;
    }

    @Override
    public String toString() {
        return etapa;
    }
    
    
    
}
