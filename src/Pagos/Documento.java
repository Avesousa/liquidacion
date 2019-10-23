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
    private String motivo;
    
    public Documento(List<Cooperativa> cooperativas, String ruta, String motivo, boolean cabal){
        this.cooperativas = cooperativas;
        this.ruta = ruta;
        this.planilla = cabal ? "cabal.docx" : "cajaAhorro.docx";
        this.nombreDocumento = "/Nota";
        this.motivo = motivo;
    }
    
    @Override
    public void run() {
        Date fecha = new Date();
        documento = new Word(planilla);
        List<String> coope = new ArrayList();
        int num = 1;
        double monto = 0;
        for(Cooperativa cooperativa : cooperativas)
            if(cooperativa.getAsociados() > 0){
                coope.add(num + "- Cooperativa de " +
                            cooperativa.getRazon()+ " Ltda. de "+
                            cooperativa.getAsociados() + " personas por un monto total de $"+
                            Sueldo.formatear(cooperativa.getMonto())+ "("+CantidadLetra.dameLetra(cooperativa.getMonto())+")");
                num++;
                monto += cooperativa.getMonto();
            }
        String[] llaves = {"#FECHA#","#MOTIVO#","#MONTOLETRATOTAL#","#MONTOPESOTOTAL#","#MESANIO#"};
        String[] valores = {fecha.getDate() + " de " + Fecha.MesC(fecha.getMonth()) + " de " + Fecha.Año(fecha.getYear()),
        motivo,CantidadLetra.dameLetra(monto),Sueldo.formatear(monto),Fecha.MesC(fecha.getMonth()) + " de " + Fecha.Año(fecha.getYear())};
        
        documento.reemplazar("#DATOPORCOOPERATIVA#", coope);
        documento.reemplazar(llaves, valores);
        documento.guardarArchivo(ruta + nombreDocumento + ".docx");
    }
    /**/
}
