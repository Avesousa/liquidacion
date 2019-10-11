package Pagos;

import Clases.FechaDeTrabajo;
import Clases.Trabajador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class Caja extends MetodoDePago implements Runnable{

    public Caja(List<FechaDeTrabajo> trabajadores, String ruta, int diasHabiles, String motivo, int id) {
        super(trabajadores, ruta, diasHabiles, motivo, id);
    }
    
    //AAAA = ANIO, MM = MES, DD = DIA --- CCC = CANTIDAD DE REGISTRO --- CCCCCC = CANTIDAD DE REGISTRO EN CASO DE SER MAYOR A 999
    private String primeraLinea() {
        String linea = "349990320890520100201687AAAAMMDDCCCIIIIIIIIIIII25001";
        char[] continuidad = "CCCCCC".toCharArray();
        char[] banderaLinea = linea.toCharArray();
        String cantidad = this.trabajadores.size() > 999 ? "000" : Sueldo.valorizar(String.valueOf(trabajadores.size()), "000", '0');
        String cantidadDos = this.trabajadores.size() > 999 ? Sueldo.valorizar(String.valueOf(trabajadores.size()),"000000",'0') : "";
        String monto = Sueldo.valorizar(Sueldo.generar(String.valueOf(this.trabajadores.stream()
                .mapToDouble(t -> Double.parseDouble(Sueldo.formatear(Sueldo.hacer(t.dias, t.trabajador.sueldo, this.dias))))
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
        for(int u = 0; u < continuidad.length; u++)
            continuidad[u] = (!cantidadDos.equals("")) ? cantidadDos.charAt(u) : '0';
        
        
        return String.valueOf(banderaLinea) + ((!cantidadDos.equals("")) ? String.valueOf(continuidad) : "" ) + "\n";
    }
    private String linea(long cuil, String cbu, double montoR,boolean ultima){
        String linea  = "DDDDDDDDDDDCCCCCCCCCCCCCCCCCCCCCCMMMMMMMMMM";
        char[] banderaLinea = linea.toCharArray();
        String monto = Sueldo.valorizar(Sueldo.generar(Sueldo.formatear(montoR)),"MMMMMMMMMM",'0');
        
        for(int i = 0; i < 11; i++)
            banderaLinea[i] = String.valueOf(cuil).charAt(i);
        for(int i = 11; i < 33; i++)
            banderaLinea[i] = cbu.charAt(i-11);
        for(int i = 33; i < 43; i++)
            banderaLinea[i] = monto.charAt(i-33);
        
        return String.valueOf(banderaLinea) + (ultima ? "" : "\n");
        
    }
    
    @Override
    public void run() {
        if(!this.ruta.equals("")){
            Date fecha = new Date();
            try {
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
                    bw.write(linea(t.getCuil(),t.getCbu(),t.montoCobrar(dias),(i+1) == trabajadores.size()));
                    cooperativas.get(t.getCoop()-1).add();
                    cooperativas.get(t.getCoop()-1).addMonto(Double.parseDouble(Sueldo.formatear(t.montoCobrar(dias))));
                    new Thread(new FinalizarPago(idPago,t.getId(),motivo,t.getUbicacion(),t.montoCobrar(dias),"CAJA AHORRO")).start();
                }
                Thread documento = new Thread(new Documento(this.cooperativas,this.ruta,false,this.motivo));
                documento.start();
                bw.close();
                        
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
