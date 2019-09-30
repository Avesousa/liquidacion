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

    public Caja(List<DiaTrabajado> trabajadores, String ruta, int diasHabiles) {
        this.trabajadores = trabajadores;
        this.ruta = ruta + "/Caja Ahorro";
        this.dias = diasHabiles;
    }
    
    //AAAA = ANIO, MM = MES, DD = DIA --- CCC = CANTIDAD DE REGISTRO --- CCCCCC = CANTIDAD DE REGISTRO EN CASO DE SER MAYOR A 999
    private String primeraLinea() {
        String linea = "349990320890520100201687AAAAMMDDCCCIIIIIIIIIIII25001CCCCCC";
        char[] banderaLinea = linea.toCharArray();
        String cantidad = trabajadores.size() > 999 ? "000" : Sueldo.valorizar(String.valueOf(trabajadores.size()), "000", '0');
        String cantidadDos = trabajadores.size() > 999 ? Sueldo.valorizar(String.valueOf(trabajadores.size()),"000000",'0') : "";
        String monto = Sueldo.valorizar(Sueldo.generar(String.valueOf(trabajadores.stream()
                .mapToDouble(t -> Double.parseDouble(Sueldo.formatear(Sueldo.hacer(t.dias, t.trabajador.sueldo, dias))))
                .sum())),"000000000000",'0');
        Date fecha = new Date();
        String anio = String.valueOf(fecha.getYear() + 1900);
        String mes = (fecha.getMonth() + 1) < 10 ? "0"+String.valueOf(fecha.getMonth() + 1) : String.valueOf(fecha.getMonth() + 1);
        String dia = (fecha.getDay() < 10) ? "0"+String.valueOf(fecha.getDay()) : String.valueOf(fecha.getDay());
        
        for(int i = 24; i < 28; i++)
            banderaLinea[i] = anio.charAt(i-1);
        for(int j = 28; j < 30; j++)
            banderaLinea[j] = mes.charAt(j-5);
        for(int k = 30; k < 32; k++)
            banderaLinea[k] = dia.charAt(k-7);
        for(int h = 32; h < 35; h++)
            banderaLinea[h] = cantidad.charAt(h);
        for(int g = 35; g < 47; g++)
            banderaLinea[g] = monto.charAt(g);
        if(!cantidadDos.equals(""))
            for(int u = 52; u < 59; u++)
                banderaLinea[u] = cantidadDos.charAt(u);
        
        return String.valueOf(banderaLinea) + "\n";
    }
    private String linea(long cuil, String cbu, double montoR){
        String linea  = "DDDDDDDDDDDCCCCCCCCCCCCCCCCCCCCCCMMMMMMMMMM";
        char[] banderaLinea = linea.toCharArray();
        String monto = Sueldo.valorizar(Sueldo.generar(Sueldo.formatear(montoR)),"MMMMMMMMMM",'0');
        
        for(int i = 0; i < 11; i++)
            banderaLinea[i] = cbu.charAt(i);
        for(int i = 11; i < 33; i++)
            banderaLinea[i] = String.valueOf(cuil).charAt(i);
        for(int i = 33; i < 44; i++)
            banderaLinea[i] = monto.charAt(i);
        
        return banderaLinea.toString() + "\n";
        
    }
    
    @Override
    public void run() {
        if(!this.ruta.equals("")){
            Date fecha = new Date();
            try {
                cooperativas = new dbCooperativa().traerCooperativas();
                File archivo = new File(ruta + "/PagoCajaAhorro" + (fecha.getDate() > 9 ? fecha.getDate() : '0' + fecha.getDate())+ "-" +(fecha.getMonth()+1) + ".txt");
                if(!archivo.exists())
                    archivo.createNewFile();
                Thread listado = new Thread(new Listar(trabajadores,ruta,dias,true));
                listado.start();
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(primeraLinea());
                for(int i = 0; i < trabajadores.size(); i++){
                    Trabajador t = trabajadores.get(i).trabajador;
                    bw.write(linea(t.getCuil(),t.getCbu(),t.montoCobrar(dias)));
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
