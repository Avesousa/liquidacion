package hilos;

import javax.swing.JProgressBar;

public class HacerBarra implements Runnable {
    JProgressBar barra;
    int valor;
    
    public HacerBarra(int valor, JProgressBar barra){
        this.valor = valor;
        this.barra = barra;
    }
    
    @Override
    public void run() {
        barra.setValue(valor);
    }
    
    
    
}
