package Pagos;

import Archivos.Word;
import Clases.Cooperativa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentoCabal implements Runnable {
    private List<Cooperativa> cooperativas;
    private String ruta;
    private Word documento;
    
    public DocumentoCabal(List<Cooperativa> cooperativas, String ruta){
        this.cooperativas = cooperativas;
        this.ruta = ruta;
    }
    
    @Override
    public void run() {
        Date fecha = new Date();
        documento = new Word("plantillaCabal.docx");
        List<String> valores = new ArrayList();
        int num = 1;
        for(Cooperativa cooperativa : cooperativas)
            if(cooperativa.getAsociados() > 0){
                valores.add(num + "- Cooperativa de " +
                            cooperativa.getRazon()+ " Ltda. de "+
                            cooperativa.getAsociados() + " personas por un monto total de $"+
                            cooperativa.getMonto()+ "(PESOS #MONTOLETRACU#)");
                num++;
            }
                documento.reemplazar("#DATOPORCOOPERATIVA#", valores);
        documento.guardarArchivo(ruta+"/DOCUMENTOCABAL" + (fecha.getDate()) + "-" + (fecha.getMonth()+1) + ".docx");
    }
    
    /**/
}
