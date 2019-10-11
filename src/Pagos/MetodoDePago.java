package Pagos;

import Clases.Cooperativa;
import Clases.FechaDeTrabajo;
import Conectores.dbCooperativa;
import java.util.List;

public class MetodoDePago {
    public List<FechaDeTrabajo> trabajadores;
    public List<Cooperativa> cooperativas;
    public String ruta;
    public int dias = 0;
    public String motivo;
    public int idPago;

    public MetodoDePago(List<FechaDeTrabajo> trabajadores, String ruta, int diasHabiles, String motivo, int id) {
        this.trabajadores = trabajadores;
        this.ruta = ruta;
        this.dias = diasHabiles;
        this.motivo = motivo;
        this.idPago = id;
        try {
            cooperativas = new dbCooperativa().traerCooperativas();
        } catch (Exception e) {
            System.out.println("[MÉTODO DE PAGO] ERROR CON CONEXIÓN A COOPERATIVA - " + e);
        }
    }
    
}
