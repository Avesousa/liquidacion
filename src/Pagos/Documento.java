package Pagos;

import Archivos.Word;
import Clases.Cooperativa;
import Clases.Fecha;
import Metodos.CantidadLetra;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Documento implements Runnable {
    private List<Cooperativa> cooperativas;
    private String ruta;
    private Word documento;
    private String planilla;
    private String nombreDocumento;
    
    public Documento(List<Cooperativa> cooperativas, String ruta, boolean cabal){
        this.cooperativas = cooperativas;
        this.ruta = ruta;
        this.planilla = cabal ? "plantillaCabal.docx" : "plantillaAhorro.docx";
        this.nombreDocumento = cabal ? "/DocumentoCabal " : "/DocumentoCajaDeAhorro ";
    }
    
    @Override
    public void run() {
        Date fecha = new Date();
        documento = new Word(this.planilla);
        List<String> valores = new ArrayList();
        int num = 1;
        double monto = 0;
        for(Cooperativa cooperativa : cooperativas)
            if(cooperativa.getAsociados() > 0){
                valores.add(num + "- Cooperativa de " +
                            cooperativa.getRazon()+ " Ltda. de "+
                            cooperativa.getAsociados() + " personas por un monto total de $"+
                            Sueldo.formatear(cooperativa.getMonto())+ "("+CantidadLetra.dameLetra(cooperativa.getMonto())+")");
                num++;
                monto += cooperativa.getMonto();
            }
        documento.reemplazar("#DATOPORCOOPERATIVA#", valores);
        documento.reemplazar("#MONTOPESOTOTAL#", Sueldo.formatear(monto));
        documento.reemplazar("#MONTOLETRATOTAL#", CantidadLetra.dameLetra(monto));
        documento.reemplazar("#FECHA#", fecha.getDate() + " de " + Fecha.MesC(fecha.getMonth()) + " de " + Fecha.Año(fecha.getYear()));
        documento.reemplazar("#MESANIO#", Fecha.MesC(fecha.getMonth()) + " de " + Fecha.Año(fecha.getYear()));
        documento.guardarArchivo(ruta + nombreDocumento + (fecha.getDate()) + "-" + (fecha.getMonth()+1) + ".docx");
    }
    /**/
}
