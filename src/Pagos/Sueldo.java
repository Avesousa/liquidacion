package Pagos;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Sueldo {
    
    public static double hacer(int dias,double sueldo, int diasHabiles){
        return sueldo/diasHabiles * dias;
    }
    
    public static String formatear(Double monto){
        DecimalFormat formato = new DecimalFormat("#.00");
        return formato.format(monto);
    }
    
    public static String formatear(Double monto,int cantidad){
        if(cantidad > 2 || cantidad < 1)
            JOptionPane.showMessageDialog(null, "No se puede realizar el formateo");
        else{
            DecimalFormat formato = new DecimalFormat("#." + (cantidad == 1 ? "0" : "00"));
            return formato.format(monto);
        }
        return "";
    }
    
    public static String generar(String valor){
        String value = "";
        for(int i = 0; i < String.valueOf(valor).length(); i++){
            if(!String.valueOf(valor.charAt(i)).equals(",") && !String.valueOf(valor.charAt(i)).equals("."))
                value += String.valueOf(valor).charAt(i);
        }
        return value;
    }
    
    public static String valorizar(String valor, String como, char vacio){
        char[] respuesta = como.toCharArray();
        if(valor.length() <= como.length())
            for(int i = respuesta.length; i >= 0; i--){
                try {
                    respuesta[i] = valor.charAt(i);
                } catch (IndexOutOfBoundsException e) {
                    respuesta[i] = vacio;
                }
            }
        return respuesta.toString();
    }
}
