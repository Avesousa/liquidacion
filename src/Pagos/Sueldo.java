package Pagos;

import java.text.DecimalFormat;

public class Sueldo {
    
    public static double hacer(int dias,double sueldo, int diasHabiles){
        return sueldo/diasHabiles * dias;
    }
    
    public static String formatear(Double monto){
        DecimalFormat formato = new DecimalFormat("#.00");
        return formato.format(monto);
    }
    
    public static String generar(String valor){
        String value = "";
        for(int i = 0; i < String.valueOf(valor).length(); i++){
            if(!String.valueOf(valor.charAt(i)).equals(",") && !String.valueOf(valor.charAt(i)).equals("."))
                value += String.valueOf(valor).charAt(i);
        }
        return value;
    }
}
