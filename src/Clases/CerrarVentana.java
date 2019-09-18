package Clases;

import javax.swing.JFrame;

public class CerrarVentana {
    
    public CerrarVentana(JFrame ventana, Thread condicion, JFrame casa){
        try {
            ventana.dispose();
            casa.setVisible(true);
            if(condicion != null)
                condicion.stop();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}


