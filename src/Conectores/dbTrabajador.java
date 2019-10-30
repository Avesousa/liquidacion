package Conectores;

import Clases.Condiciones;
import Clases.FechaDeTrabajo;
import java.util.*;
import Clases.Trabajador;
import hilos.CorrerCondiciones;
import hilos.HacerBarra;
import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import ventanas.CrearCondiciones;

public class dbTrabajador extends Conexion{
    private List<Trabajador> trabajadores = new ArrayList();
    private List<FechaDeTrabajo> diasTrabajados = new ArrayList();
    
    public List<Trabajador> traerTrabajadores(String etapa) throws SQLException{
            sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal' "+
                  "FROM centroverde.asociados A, incentivo.sueldo P "+
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
    public Trabajador traerTrabajadores(int id, int dias) throws SQLException{
    
        sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal', A.cuenta_cbu, A.cuenta_cabal, A.documento, A.cooperativa, A.rai "+
                  "FROM centroverde.asociados A, incentivo.sueldo P "+
                  "WHERE estado = true and A.tipo_presentismo = P.medio and A.id_asociado = " + id;
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            if(res.next()){
                return new Trabajador(
                        res.getInt(1),
                        res.getLong(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(8),
                        res.getDouble(7),
                        res.getString(9),
                        res.getString(10),
                        res.getInt(11),
                        res.getInt(12),
                        dias,
                        res.getString(13));
            }
            return null;
    
    }
    public Trabajador traerTrabajadores(int id) throws SQLException{
    
        sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, "+
                  "A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal', A.cuenta_cbu, "+
                  "A.cuenta_cabal, A.documento, A.cooperativa, A.rai "+
                  "FROM centroverde.asociados A, incentivo.sueldo P "+
                  "WHERE estado = true and A.tipo_presentismo = P.medio and A.id_asociado = " + id;
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            if(res.next()){
                return new Trabajador(
                        res.getInt(1),
                        res.getLong(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(8),
                        res.getDouble(7),
                        res.getString(9),
                        res.getString(10),
                        res.getInt(11),
                        res.getInt(12),
                        res.getString(13));
            }
            return null;
    
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
                trabajadores.get(trabajadores.size()-1).colocarTabla(tabla,true);
            }
            System.out.println(trabajadores);
        } catch (Exception e) {
            System.out.println("[conectores.Trabajador.Recuperadores]: error encontrado: " + e);
            System.out.println("[conectores.Trabajador.Recuperadores[: id: " + valor);
        }
    }
    public void traerRecuperadores(DefaultTableModel tabla, java.sql.Date fechaInicio, java.sql.Date fechaFinal, CrearCondiciones condiciones){
        System.out.println("[conectores.Trabajador.traerRecuperadores]: ¡Ha comenzado a correr!");
        condiciones.texto_finalizar.setVisible(false);
        trabajadores = new ArrayList();
        dbPago conexion = new dbPago();
        try {
            sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, "+
                  "A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal', "+
                  "A.cuenta_cbu, A.cuenta_cabal, A.documento, A.cooperativa, A.rai "+
                  "FROM centroverde.asociados A, incentivo.sueldo P "+
                  "WHERE estado = true and A.tipo_presentismo = P.medio";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            int cantidad = 0;
            while(res.next()){
                trabajadores.add(new Trabajador(
                        res.getInt(1),
                        res.getLong(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(8),
                        res.getDouble(7),
                        res.getString(9),
                        res.getString(10),
                        res.getInt(11),
                        res.getInt(12),
                        res.getString(13)));
                CorrerCondiciones condi = new CorrerCondiciones(fechaInicio,fechaFinal,trabajadores.get(trabajadores.size()-1),tabla,conexion);
                condi.acoplarCondiciones();
                cantidad++;
                condiciones.texto_finalizar.setText("Se han cargado " + cantidad + " de recuperadores...");
                condiciones.texto_finalizar.setVisible(true);
            }
            condiciones.texto_finalizar.setText("Ha finalizado la carga... ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    /*public void traerRecuperadores(DefaultTableModel tabla, java.sql.Date fechaInicio, java.sql.Date fechaFinal, CrearCondiciones condiciones){
        System.out.println("[conectores.Trabajador.traerRecuperadores]: ¡Ha comenzado a correr!");
        condiciones.texto_finalizar.setVisible(false);
        DefaultTableModel modelo = (DefaultTableModel)condiciones.tabla.getModel();
        modelo.setRowCount(0);
        trabajadores = new ArrayList();
        lasCondiciones = new ArrayList();
        hiloCondiciones = new ArrayList();
        try {
            sql = "SELECT A.id_asociado, A.cuil, A.nombre, A.apellido, A.tipo_presentismo, "+
                  "A.funcion, P.monto, concat_ws(' ',ubicacion,division) AS 'ubicacionfinal', "+
                  "A.cuenta_cbu, A.cuenta_cabal, A.documento, A.cooperativa, A.rai "+
                  "FROM centroverde.asociados A, incentivo.sueldo P "+
                  "WHERE estado = true and A.tipo_presentismo = P.medio";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while(res.next()){
                trabajadores.add(new Trabajador(
                        res.getInt(1),
                        res.getLong(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(8),
                        res.getDouble(7),
                        res.getString(9),
                        res.getString(10),
                        res.getInt(11),
                        res.getInt(12),
                        res.getString(13)));
                CorrerCondiciones sinHilo = new CorrerCondiciones(fechaInicio,fechaFinal,trabajadores.get(trabajadores.size()-1),tabla);
                Thread hilo = new Thread(sinHilo);
                hilo.start();
                condiciones.texto_finalizar.setText("Se está cargando " + (i+1) + "...");
                condiciones.texto_finalizar.setVisible(true);
                hilo.join();
                sinHilo.coloca();
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
    
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
    
    public List<FechaDeTrabajo> traerDiasTrabajados(java.sql.Date fs, java.sql.Date fe, boolean conDias, boolean reclamo) throws SQLException, InterruptedException{
        sql =   !reclamo ? 
                "SELECT U.custom_id , COUNT(A.date) " +
                "FROM presentismo_db.assistance A, presentismo_db.user U " +
                "WHERE U.id = A.employee AND A.date BETWEEN '"+fs+"' AND '"+fe+"' " +
                "GROUP BY U.custom_id;" :
                "SELECT U.custom_id, COUNT(A.fecha) " +
                "FROM presentismo_db.user U, incentivo.reclamo A " +
                "WHERE U.id = A.asociado AND A.estado = 0 AND A.reclamo <> 'sancion' " + 
                "GROUP BY U.custom_id";
        ps = conector.prepareStatement(sql);
        res = ps.executeQuery();
        while(res.next()){
            FechaDeTrabajo fecha = conDias ? new FechaDeTrabajo(res.getInt(1),res.getInt(2),fs,fe) : new FechaDeTrabajo(res.getInt(1),fs,fe);
            Thread hilo = new Thread(fecha);
            hilo.start();
            hilo.join();
            diasTrabajados.add(fecha);
        }
        return diasTrabajados;
    }

}
