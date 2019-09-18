package Clases;

import java.util.ArrayList;
import java.util.List;

public class Coordinador {
    private String coordinador;
    private int id;
    private String correo;
    public List<Etapas> etapas;

    public Coordinador( int id,String coordinador, String correo,List<Etapas> etapas) {
        this.coordinador = coordinador;
        this.id = id;
        this.correo = correo;
        this.etapas = etapas;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public int getId() {
        return id;
    }
    
    public String getCorreo(){
        return correo;
    }

    public void addEtapa(Etapas etapa) {
        etapas.add(etapa);
    }
    
    public void addEtapas(List<Etapas> etapas){
        etapas = etapas;
    }
    
    
    
    
}
