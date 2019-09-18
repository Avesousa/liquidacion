package Metodos;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CorrerBarra extends Thread {
    
    JProgressBar barra;
    JLabel texto;
    int datos;
    int cantidad;
    int progreso;

    public CorrerBarra(JProgressBar barra, JLabel texto) {
        super();
        this.barra = barra;
        this.texto = texto;
    }

    public void cargar(int datos, int cantidad, int progreso){
        
       this.cantidad = cantidad;
       this.datos = datos;
       this.progreso = progreso;
       this.run();
        
    }

    @Override
    public void run() {
       barra.setValue(progreso);
       texto.setText("Se han cargado "+ cantidad + "/" + datos);
    }
    
    
    
    
}
