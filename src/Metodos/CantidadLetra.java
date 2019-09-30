package Metodos;

import Pagos.Sueldo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class CantidadLetra {
    private static final double billonD = 1000000000000.0;
    private static final double milMillonD = 1000000000.0;
    private static final double millonD = 1000000.0;
    private static final double milD = 1000.0;

    public static String dameLetra(double nm){
        System.out.println(nm);
        String nl = Sueldo.formatear(nm);
        String[] n = nl.split(Pattern.quote("."));
        String letra = "";
        long numero = Long.parseLong(n[0]);
        long nBillon = (long)(numero/billonD);
        long nMilMillon = (long)(numero/milMillonD);
        long nMillon = (long)(numero/millonD);
        long nMil = (long)(numero/milD);
        
        int[] nt = separarNumero(numero);
        if(nBillon >= 1){
            for(int i = 0; i < nt.length; i++){
                switch(i){
                    case 0:
                    letra = sacarNumero(nt[i],letra,true,1 == String.valueOf(nt[i]).length()) + " " + billon;
                    break;
                    case 1:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? (nt[i+1] > 0 ? mil : mil + " " + millones) : "");
                    break;
                    case 2:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? millones : "");
                    break;
                    case 3:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? mil : "");
                    break;
                    case 4:
                    letra = sacarNumero(nt[i],letra,false,false);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"¡Ha ocurrido un error dando el monto a texto!");
                    break;
                }
            }
            return letra + " con " + n[1] + "/100";
        } else if(nMilMillon >= 1){
            for(int i = 0; i < nt.length; i++){
                switch(i){
                    case 0:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? (nt[i+1] > 0 ? mil : mil + " " + millones) : "");
                    break;
                    case 1:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? millones : "");
                    break;
                    case 2:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? mil : "");
                    break;
                    case 3:
                    letra = sacarNumero(nt[i],letra,false,false);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"¡Ha ocurrido un error dando el monto a texto!");
                    break;
                }
            }
            return letra + " con " + n[1] + "/100";
        }else if(nMillon >= 1){
            for(int i = 0; i < nt.length; i++){
                
                switch(i){
                    case 0:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? millones : "");
                    break;
                    case 1:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? mil : "");
                    break;
                    case 2:
                    letra = sacarNumero(nt[i],letra,false,false);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"¡Ha ocurrido un error dando el monto a texto!");
                    break;
                }
            }
            return letra + " con " + n[1] + "/100";
        }else if(nMil >= 1){
            System.out.println("ENTRO EN MIL");
            for(int i = 0; i < nt.length; i++){
                System.out.println(nt[i]);
                switch(i){
                    case 0:
                    letra = sacarNumero(nt[i],letra,true,false) + " " + (nt[i] > 0 ? mil : "");
                    break;
                    case 1:
                    letra = sacarNumero(nt[i],letra,false,false);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"¡Ha ocurrido un error dando el monto a texto!");
                    break;
                }
            }
            System.out.println(letra);
            return letra + " con " + n[1] + "/100";
        }else{
            for(int i = 0; i < nt.length; i++){
                
                switch(i){
                    case 0:
                    letra = sacarNumero(nt[i],letra,false,false);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"¡Ha ocurrido un error dando el monto a texto!");
                    break;
                }
            }
            System.out.println(letra);
            return letra + " con " + n[1] + "/100";
        }        
    }
    private static String sacarNumero(int n, String letra, boolean alto, boolean repite){
        
        String [] decena = separar((double)n/10);
        String [] centena = separar((double)n/100);
        String valor = ("").equals(letra) ? letra : letra + " ";
        
        if(Integer.parseInt(centena[0]) > 0){
            
            int numero = Integer.parseInt(centena[1]);
            if(numero<9 && numero > 0)
                return valor + darCentena(Integer.parseInt(centena[0])) + " " + darUnidad(numero);
            else
                switch(numero){
                    case 0:
                        return valor + darCentena(Integer.parseInt(centena[0]));
                    
                    default:
                        return sacarNumero(numero, valor + darCentena(Integer.parseInt(centena[0])),alto,true);
                }
            
            
            
        }else if(Integer.parseInt(decena[0]) > 0){
            
            int numeroUno = Integer.parseInt(decena[0]);
            int numeroDos = Integer.parseInt(separar((double)Integer.parseInt(decena[1])/10)[0]);
            
            if(numeroUno == 1 || numeroUno == 2)
                switch(numeroDos){
                    case 0:
                        return valor + (numeroUno > 1 ? veinte : darDecena(1));
                    default:
                        return valor + (numeroUno > 1 ? (darDecena(numeroUno) + (numeroDos == 1 ? (alto ? un : darUnidad(numeroDos)) : darUnidad(numeroDos))) : darDiez(numeroDos));
                }
            else
                switch(numeroDos){
                    case 0:
                        return valor + darDecena(numeroUno);
                    default:
                        return valor + darDecena(numeroUno) + " y " + darUnidad(numeroDos);
                }
            
        }else{
            //Valores para uno
            String uno = "";
            if(alto && repite)
                uno = un;
            else if(repite && !alto || !repite && !alto)
                uno = uno;
            else if(!repite && alto)
                uno = mil;
            
            return valor + (n == 1 ? uno : darUnidad(n));
            
        }
    }
    private static String[] separar(double valor){
        String[] cero = {"0","0"};
        if((int)valor > 0)
            return Sueldo.formatear(valor).split(Pattern.quote("."));
        else
            return cero;
    }
    private static int[] separarNumero(long numero){
        
        System.out.println("SEPARAR NUMERO: " + numero);
        String n = String.valueOf(numero);
        System.out.println("SEPARAR STRING: " + n);
        int dato = ((Integer.parseInt(separar((double)n.length()/3)[1]) > 0) ? (Integer.parseInt(separar((int)n.length()/3)[0]) + 1) : (int)(n.length()/3));
        int[] valor = new int[dato];
        dato--;
        System.out.println("DATO: " + dato);
        System.out.println("VALOR: " + valor.length);
        
        for(int i = n.length()-1; i >= 0; i = i-3){
            System.out.println("---------* EL FOR: " + i);
            String tema = String.valueOf(n.charAt(i));
            if((i - 1) >= 0){
                tema = String.valueOf(n.charAt(i-1)) + tema;
                if((i - 2) >= 0)
                    tema = String.valueOf(n.charAt(i-2)) + tema;
            }
            valor[dato] = Integer.parseInt(tema);
            dato--;
        }
        
        return valor;
        
    }

    // GLOSARIO - UNIDAD
    private static final String uno = "uno";
    private static final String un = "un";
    private static final String dos = "dos";
    private static final String tres = "tres";
    private static final String cuatro = "cuatro";
    private static final String cinco = "cinco";
    private static final String seis = "seis";
    private static final String siete = "siete";
    private static final String ocho = "ocho";
    private static final String nueve = "nueve";
    //DECENAS DE 10
    private static final String diez = "diez";
    private static final String once = "once";
    private static final String doce = "doce";
    private static final String trece = "trece";
    private static final String catorce = "catorce";
    private static final String quince = "quince";
    private static final String dieciseis = "dieciseis";
    private static final String diecisiete = "diecisiete";
    private static final String dieciocho = "dieciocho";
    private static final String diecinueve = "diecinueve";
    
    //DECENAS
    private static final String veinte = "veinte";
    private static final String veinti = "veinti";
    private static final String treinta = "treinta";
    private static final String cuarenta = "cuarenta";
    private static final String cincuenta = "cincuenta";
    private static final String sesenta = "sesenta";
    private static final String setenta = "setenta";
    private static final String ochenta = "ochenta";
    private static final String noventa = "noventa";
    
    //CENTENAS
    private static final String cien = "cien";
    private static final String ciento = "ciento";
    private static final String cientos = "cientos";
    private static final String quinientos = "quinientos";
    private static final String setecientos = "setecientos";
    private static final String novecientos = "novecientos";
    
    //MILES
    private static final String mil = "mil";
    
    //MILLON
    private static final String millon = "millón";
    private static final String millones = "millones";
    
    //BILLONES
    private static final String billon = "billón";
    private static final String billones = "billones";
    
    private static String darCentena(int n){
        String [] valor = {
            ciento,
            dos+cientos,
            tres+cientos,
            cuatro+cientos,
            quinientos,
            seis+cientos,
            setecientos,
            ocho+cientos,
            novecientos};
        return valor[n-1];
    }
    private static String darDiez(int n){
        String [] valor = {
            once,
            doce,
            trece,
            catorce,
            quince,
            dieciseis,
            diecisiete,
            dieciocho,
            diecinueve
        };
        return valor[n-1];
    }
    private static String darDecena(int n){
        String [] valor = {
            diez,
            veinti,
            treinta,
            cuarenta,
            cincuenta,
            sesenta,
            setenta,
            ochenta,
            noventa
        };
        return valor[n-1];
    }
    private static String darUnidad(int n){
        String [] valor = {
            uno,
            dos,
            tres,
            cuatro,
            cinco,
            seis,
            siete,
            ocho,
            nueve
        };
        return valor[n-1];
    }
}
