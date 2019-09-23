/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author 20956852766
 */
public class Fecha {
    
    public static String Mes(int mes){
        switch(mes){
            case 0: return "Ene";
            case 1: return "Feb";
            case 2: return "Mar";
            case 3: return "Abr";
            case 4: return "May";
            case 5: return "Jun";
            case 6: return "Jul";
            case 7: return "Ago";
            case 8: return "Sep";
            case 9: return "Oct";
            case 10: return "Nov";
            default: return "Dic";
        }
    }
    
    public static String MesC(int mes){
        switch(mes){
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Noviembre";
            default: return "Diciembre";
        }
    }
    
    public static int Año(int año){
        return año + 1900;
    }
    
    public static String dia(int dia){
        switch(dia){
            case 0: return "Dom";
            case 1: return "Lun";
            case 2: return "Mar";
            case 3: return "Mie";
            case 4: return "Jue";
            case 5: return "Vie";
            default: return "Sab";
        }
    }
    
    public static String diaC(int dia){
        switch(dia){
            case 0: return "Domingo";
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miercoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            default: return "Sabado";
        }
    }
    
    public static java.sql.Date convertir(java.util.Date fecha){
        return new java.sql.Date(fecha.getTime());
    }
    
}
