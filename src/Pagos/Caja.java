package Pagos;

import Clases.Cooperativa;
import Clases.DiaTrabajado;
import Clases.Trabajador;
import Conectores.dbCooperativa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class Caja implements Runnable{
    private List<DiaTrabajado> trabajadores;
     private List<Cooperativa> cooperativas;
     private String ruta;
     private int dias = 0;
     private String motivo;

    public Caja(List<DiaTrabajado> trabajadores, String ruta, int diasHabiles, String motivo) {
        this.trabajadores = trabajadores;
        this.ruta = ruta;
        this.dias = diasHabiles;
        this.motivo = motivo;
    }
    
    //AAAA = ANIO, MM = MES, DD = DIA --- CCC = CANTIDAD DE REGISTRO --- CCCCCC = CANTIDAD DE REGISTRO EN CASO DE SER MAYOR A 999
    private String primeraLinea() {
        String linea = "349990320890520100201687AAAAMMDDCCCIIIIIIIIIIII25001";
        char[] continuidad = "CCCCCC".toCharArray();
        char[] banderaLinea = linea.toCharArray();
        String cantidad = trabajadores.size() > 999 ? "000" : Sueldo.valorizar(String.valueOf(trabajadores.size()), "000", '0');
        String cantidadDos = trabajadores.size() > 999 ? Sueldo.valorizar(String.valueOf(trabajadores.size()),"000000",'0') : "";
        String monto = Sueldo.valorizar(Sueldo.generar(String.valueOf(trabajadores.stream()
                .mapToDouble(t -> Double.parseDouble(Sueldo.formatear(Sueldo.hacer(t.dias, t.trabajador.sueldo, dias))))
                .sum())),"000000000000",'0');
        Date fecha = new Date();
        String anio = String.valueOf(fecha.getYear() + 1900);
        String mes = (fecha.getMonth() + 1) < 10 ? "0"+String.valueOf(fecha.getMonth() + 1) : String.valueOf(fecha.getMonth() + 1);
        String dia = (fecha.getDate() < 10) ? "0"+String.valueOf(fecha.getDate()) : String.valueOf(fecha.getDate());
        
        for(int i = 24; i < 28; i++)
            banderaLinea[i] = anio.charAt(i-24);
        for(int j = 28; j < 30; j++)
            banderaLinea[j] = mes.charAt(j-28);
        for(int k = 30; k < 32; k++)
            banderaLinea[k] = dia.charAt(k-30);
        for(int h = 32; h < 35; h++)
            banderaLinea[h] = cantidad.charAt(h-32);
        for(int g = 35; g < 47; g++){
            try {
                banderaLinea[g] = monto.charAt(g-35);
            } catch (StringIndexOutOfBoundsException e) {
                banderaLinea[g] = '0';
            }
            
        }
        for(int u = 52; u < 58; u++)
            continuidad[u] = (!cantidadDos.equals("")) ? cantidadDos.charAt(u-52) : '0';
        
        
        return String.valueOf(banderaLinea) + ((!cantidadDos.equals("")) ? String.valueOf(continuidad) : "" ) + "\n";
    }
    private String linea(long cuil, String cbu, double montoR){
        String linea  = "DDDDDDDDDDDCCCCCCCCCCCCCCCCCCCCCCMMMMMMMMMM";
        char[] banderaLinea = linea.toCharArray();
        String monto = Sueldo.valorizar(Sueldo.generar(Sueldo.formatear(montoR)),"MMMMMMMMMM",'0');
        
        for(int i = 0; i < 11; i++)
            banderaLinea[i] = String.valueOf(cuil).charAt(i);
        for(int i = 11; i < 33; i++)
            banderaLinea[i] = cbu.charAt(i-11);
        for(int i = 33; i < 43; i++)
            banderaLinea[i] = monto.charAt(i-33);
        
        return String.valueOf(banderaLinea) + "\n";
        
    }
    
    @Override
    public void run() {
        if(!this.ruta.equals("")){
            Date fecha = new Date();
            try {
                cooperativas = new dbCooperativa().traerCooperativas();
                File archivo = new File(ruta + "/PagoCajaAhorro " + fecha.getDate()+ "-" +(fecha.getMonth()+1) + ".txt");
                if(!archivo.exists())
                    archivo.createNewFile();
                Thread listado = new Thread(new Listar(trabajadores,ruta,dias,false));
                listado.start();
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(primeraLinea());
                for(int i = 0; i < trabajadores.size(); i++){
                    Trabajador t = trabajadores.get(i).trabajador;
                    bw.write(linea(t.getCuil(),t.getCbu(),t.montoCobrar(dias)));
                    cooperativas.get(t.getCoop()-1).add();
                    cooperativas.get(t.getCoop()-1).addMonto(Double.parseDouble(Sueldo.formatear(t.montoCobrar(dias))));
                }
                Thread documento = new Thread(new Documento(cooperativas,ruta,false));
                documento.start();
                bw.close();
                        
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
