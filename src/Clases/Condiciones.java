package Clases;

public class Condiciones {
   
    private double monto = 0;
    private String metodo = "";
    
    public void agregarMonto(double monto){
        this.monto += monto;
    }
    
    public void agregarMetodo(String metodo){
        if(!metodo.equals(""))
            this.metodo = metodo;
    }

    public double getMonto() {
        return monto;
    }

    public String getMetodo() {
        return metodo;
    }
    
    
}
