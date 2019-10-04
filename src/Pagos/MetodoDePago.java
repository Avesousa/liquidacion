package Pagos;

import Clases.Cooperativa;
import Clases.DiaTrabajado;
import Conectores.dbCooperativa;
import java.util.List;

public class MetodoDePago {
    public List<DiaTrabajado> trabajadores;
    public List<Cooperativa> cooperativas;
    public String ruta;
    public int dias = 0;
    public String motivo;

    public MetodoDePago(List<DiaTrabajado> trabajadores, String ruta, int diasHabiles, String motivo) {
        this.trabajadores = trabajadores;
        this.ruta = ruta;
        this.dias = diasHabiles;
        this.motivo = motivo;
        try {
            cooperativas = new dbCooperativa().traerCooperativas();
        } catch (Exception e) {
            System.out.println("[MÉTODO DE PAGO] ERROR CON CONEXIÓN A COOPERATIVA - " + e);
        }
    }
    
}
