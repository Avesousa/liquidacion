package Clases;

import Conectores.dbPago;
import Conectores.dbTrabajador;
import Pagos.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

public class GeneradorDePago {
    
    private int diasHabiles = 0;
    
    private String ruta;
    
    private java.util.Date fechaI;
    
    private java.util.Date fechaF;
    
    public GeneradorDePago(Calendar fechaI, Calendar fechaF, String ruta){
        this.fechaI = fechaI.getTime();
        this.fechaF = fechaF.getTime();
        this.ruta = ruta;
        if(new dbPago().verificar(Fecha.convertir(fechaI.getTime()), Fecha.convertir(fechaF.getTime()))){
            diasHabiles = traerDiasHabiles(fechaI,fechaF);
            try {
                List<FechaDeTrabajo> trabajador = new dbTrabajador().traerDiasTrabajados(Fecha.convertir(fechaI.getTime()), Fecha.convertir(fechaF.getTime()), true);
                crearPago(trabajador);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error en la Base de Datos!");
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error en los hilos!");
                ex.printStackTrace();
            }
            
        }
        
        //En caso de ser FALSE el programa no genera ningún dato, y cancela operación.
        
    }
    
    
    private int traerDiasHabiles(Calendar fechaI, Calendar fechaF){
        int num = 0;
        int resta = 0;
        while(fechaI.before(fechaF) || fechaI.equals(fechaF)){
            if(fechaI.get(Calendar.DAY_OF_WEEK) != 7 && fechaI.get(Calendar.DAY_OF_WEEK) != 1)
                if(VerificarFeriados.verificar(fechaI.getTime()))
                    num++;
            fechaI.add(Calendar.DATE, 1);
            resta++;
        }
        fechaI.add(Calendar.DATE,-resta);
        return num;
    }
    
    
    private void crearPago(List<FechaDeTrabajo> lista) {
        List<FechaDeTrabajo> listaCbu = new ArrayList();
        List<FechaDeTrabajo> listaCabal = new ArrayList();
        List<FechaDeTrabajo> listaManual = new ArrayList();
        List<FechaDeTrabajo> listaRai = new ArrayList();
        for(int i = 0; i < lista.size(); i++){
            Trabajador t = lista.get(i).trabajador;
            
            if(t.getMetodoCondicional().equals("")){
                if(t.getRai() != null && !t.getRai().equals(""))
                    listaRai.add(lista.get(i));
                else if(t.getCbu() != null && !t.getCbu().equals(""))
                    listaCbu.add(lista.get(i));
                else if(t.getCabal() != null && !t.getCabal().equals(""))
                    listaCabal.add(lista.get(i));
                else listaManual.add(lista.get(i));
            }else{
                switch(t.getMetodoCondicional()){
                    case "cabal":
                        listaCabal.add(lista.get(i));
                        break;
                    case "cbu":
                        listaCabal.add(lista.get(i));
                    case "manual":
                        listaManual.add(lista.get(i));
                    case "rai":
                        listaRai.add(lista.get(i));
                }
            }
        } 
        
        int id = new dbPago().crearPago(Fecha.convertir(fechaI),Fecha.convertir(fechaF));
        Thread cabal = new Thread(new Cabal(listaCabal,ruta,diasHabiles,"Incentivo",id));
        cabal.start();
        Thread cbu = new Thread(new Caja(listaCbu,ruta,diasHabiles,"Incentivo",id));
        cbu.start();
        
    }
}

