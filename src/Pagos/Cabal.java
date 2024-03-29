package Pagos;

import Clases.FechaDeTrabajo;
import Clases.Trabajador;
import Conectores.dbCooperativa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class Cabal extends MetodoDePago implements Runnable{
    private String lineaCabal = "20CCCCCCCCCCCCCCCIIIIIIIIII AAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDD00000               ";

    public Cabal(List<FechaDeTrabajo> trabajadores, String ruta, int diasHabiles, String motivo, int id) {
        super(trabajadores,ruta+"/Cabal",diasHabiles,motivo,id);
    }
    //C:CUENTA / I:IMPORTE / A:ASOCIADO / D:DOCUMENTO
    //Métodos para construir Cabal TXT
    private String linea(String cabal, String asociado, String documento, String monto){
        char[] banderaLinea = lineaCabal.toCharArray();
        int doc = documento.length()-1;
        int nom = 0;
        int mon = monto.length()-1;
        int cab = cabal.length()-1;
        for(int i = banderaLinea.length -1; i >= 0; i--){
            if(i > 57 && i < 70 ){
                try{
                    banderaLinea[i] = documento.charAt(doc);
                    doc--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            } else if(i > 16 && i < 27){
                try{
                    banderaLinea[i] = monto.charAt(mon);
                    mon--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            } else if(i > 0 && i < 18){
                try{
                    banderaLinea[i] = cabal.charAt(cab);
                    cab--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            }
        }
        
        for(int i = 29; i < (29+29); i++){
            if(i < 59){
                try {
                    banderaLinea[i] = asociado.charAt(nom);
                    nom++;
                } catch (Exception e) {
                    banderaLinea[i] = ' ';
                }
            }
        }
        
        return "\n" + String.valueOf(banderaLinea);
        
    }
    private String primeraLinea() {
        String linea = "12018050101052000201687                    34999032089034180                             ";
        char[] banderaLinea = linea.toCharArray();
        
        Date fecha = new Date();
        
        String anio = String.valueOf(fecha.getYear() + 1900);
        String mes = (fecha.getMonth() + 1) < 10 ? "0"+String.valueOf(fecha.getMonth() + 1) : String.valueOf(fecha.getMonth() + 1);
        String dia = (fecha.getDay() < 10) ? "0"+String.valueOf(fecha.getDay()) : String.valueOf(fecha.getDay());
        
        for(int i = 1; i < 5; i++)
            banderaLinea[i] = anio.charAt(i-1);
        for(int j = 5; j < 7; j++)
            banderaLinea[j] = mes.charAt(j-5);
        for(int k = 7; k < 9; k++)
            banderaLinea[k] = dia.charAt(k-7);
        
        return String.valueOf(banderaLinea);
        
    }
    private String ultimaLinea(String cantidad, String monto) {
        String linea = "9CCCCCCCCCMMMMMMMMMMMMMM                                                                 ";
        char[] banderaLinea = linea.toCharArray();
        int cant = cantidad.length() - 1;
        int mont = monto.length() - 1;
        for(int i = banderaLinea.length -1; i > 0; i--){
            if(i > 9 && i < 24){
                try {
                    banderaLinea[i] = monto.charAt(mont);
                    mont--;
                } catch (IndexOutOfBoundsException e) {
                    banderaLinea[i] = '0';
                }
            }else if(i > 0 && i < 11){
                try {
                    banderaLinea[i] = cantidad.charAt(cant);
                    cant--;
                } catch (IndexOutOfBoundsException e) {
                    banderaLinea[i] = '0';
                }
            }
        }
        
        return "\n" + String.valueOf(banderaLinea) + "\n";
        
    }

    @Override
    public void run() {
        double montoTotal = 0;
        if(trabajadores.size() > 0)
            if(!ruta.equals("")){
                try{ 
                    File carpeta = new File(ruta);
                    carpeta.mkdirs();
                    cooperativas = new dbCooperativa().traerCooperativas();
                    File archivo = new File(ruta + "/Formato.txt");
                    if(!archivo.exists())
                        archivo.createNewFile();
                    Thread listado = new Thread(new Listar(trabajadores,ruta,dias,true));
                    listado.start();
                    FileWriter fw = new FileWriter(archivo);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(primeraLinea());
                    for(int i = 0; i < trabajadores.size(); i++){
                        Trabajador t = trabajadores.get(i).trabajador;
                        System.out.println("Persona en cabal " + t.getNombre());
                        bw.write(linea(t.getCabal(),t.getNombre() + " " + t.getApellido(),String.valueOf(t.getDocumento()),Sueldo.generar(Sueldo.formatear(t.montoCobrar(dias)))));
                        System.out.println(t.montoCobrar(dias));
                        montoTotal += (t.montoCobrar(dias));
                        cooperativas.get(t.getCoop()-1).add();
                        cooperativas.get(t.getCoop()-1).addMonto(t.montoCobrar(dias));
                        new Thread(new FinalizarPago(idPago,t.getId(),motivo,t.getUbicacion(),t.montoCobrar(dias),"CABAL")).start();
                    }
                    Thread documento = new Thread(new Documento(cooperativas,ruta,motivo,true));
                    documento.start();
                    bw.write(ultimaLinea(String.valueOf(trabajadores.size()),Sueldo.generar(Sueldo.formatear(montoTotal))));
                    bw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
     
     
}
