package Conectores;

import java.util.*;
import Clases.Trabajador;
import java.math.BigInteger;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class dbTrabajador extends Conexion{
    private List<Trabajador> trabajadores = new ArrayList();
    
    public List<Trabajador> traerTrabajadores(String etapa) throws SQLException{
            sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal' "+
                  "FROM centroverde.asociados A, incentivo.pago P "+
                  "WHERE A.medio = 1 and tipo <> 'LE' and estado = true and A.tipo_presentismo = P.medio and A.proyecto_presentismo = '" + etapa + "'";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            while(res.next()){
                trabajadores.add(new Trabajador(
                        res.getInt(1),
                        res.getLong(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(8),
                        res.getDouble(7)));
            }
            return trabajadores;
    }
    
    public void traerRecuperadores(DefaultTableModel tabla){
        System.out.println("[conectores.Trabajador.traerRecuperadores]: ¡Ha comenzado a correr!");
        int valor = 0;
        try {
            
            sql = "SELECT *, C.abreviatura_cooperativa " +
                  "FROM centroverde.asociados, centroverde.cooperativas C " +
                  "WHERE cooperativa = C.id_cooperativa and medio = 1";
            res = conector.prepareStatement(sql).executeQuery();
            while(res.next()){
                valor = res.getInt(1);
                trabajadores.add(new Trabajador(
                   
                    res.getInt("id_asociado"),
                    res.getInt("documento"),
                    res.getString("nombre"),
                    res.getString("apellido"),
                    res.getString("tipo"),
                    res.getString("funcion"),
                    res.getString("ubicacion"),
                    res.getString("division"),
                    res.getString("C.abreviatura_cooperativa"),
                    res.getString("cuenta_cbu"),
                    res.getString("cuenta_cabal"),
                    res.getInt("id_bolson"),
                    res.getString("turno"),
                    res.getLong("cuil"),
                    res.getDate("fecha_nacimiento"),
                    res.getString("correo"),
                    res.getString("genero").equals("H")? "MASCULINO" : "FEMENINO",
                    res.getInt("rur"),
                    res.getString("telefono")));
                trabajadores.get(trabajadores.size()-1).colocarTabla(tabla);
            }
            System.out.println(trabajadores);
        } catch (Exception e) {
            System.out.println("[conectores.Trabajador.Recuperadores]: error encontrado: " + e);
            System.out.println("[conectores.Trabajador.Recuperadores[: id: " + valor);
        }
    }
    
    public void traerTipos(JComboBox caja){
        try {
            sql = "SELECT DISTINCT tipo FROM centroverde.asociados WHERE tipo <> '' and medio = 1";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            caja.removeAllItems();
            caja.addItem("Selecciona el tipo");
            while(res.next()){
                caja.addItem(res.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println("[conectores.Trabajador.Tipo]: error encontrado: " + e);
        }
        
        
    }
    
    public List<Trabajador> darListaTrabajadores(){
        return this.trabajadores;
    }
    

}
