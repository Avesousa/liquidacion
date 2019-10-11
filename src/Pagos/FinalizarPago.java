package Pagos;

import Conectores.Conexion;

public class FinalizarPago extends Conexion implements Runnable{

    private int id;
    private int idAsociado;
    private String motivo;
    private String etapa;
    private double monto;
    private String formato;

    public FinalizarPago(int id, int idAsociado, String motivo, String etapa, double monto, String formato) {
        this.id = id;
        this.idAsociado = idAsociado;
        this.motivo = motivo;
        this.etapa = etapa;
        this.monto = monto;
        this.formato = formato;
    }
    
    @Override
    public void run() {
        sql = "INSERT INTO incentivo.historia VALUES(null,?,?,?,?,?,?)";
        try {
            ps = conector.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idAsociado);
            ps.setString(3, motivo);
            ps.setString(4, etapa);
            ps.setDouble(5, monto);
            ps.setString(6, formato);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
