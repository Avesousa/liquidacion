package Conectores;

import Clases.Cooperativa;
import javax.swing.JComboBox;

public class dbProcedencia extends Conexion{
    
    private String cv = "centroverde.etapas";
    
    public void traerProcedencias(JComboBox caja, int coop){
        try {
            sql = "SELECT DISTINCT etapa_nombre FROM " + cv + " WHERE id_cooperativa = ? ORDER BY etapa_nombre";
            ps = conector.prepareStatement(sql);
            ps.setInt(1, coop);
            res = ps.executeQuery();
            caja.removeAllItems();
            caja.addItem("Selecciona la ubicación");
            while(res.next()){
                caja.addItem(res.getString("etapa_nombre"));
            }
            System.out.println("[PROCEDENCIA-llamar]: ¡Se han cargado las procedencias correctamente!");
        } catch (Exception e) {
            System.out.println("[PROCEDENCIA-ERROR1]: " + e);
        }
    } 
    
    public void traerDivision(JComboBox caja, String ubicacion){
        try {
            sql = "SELECT * FROM " + cv + " WHERE etapa_nombre = ? and etapa_division <> ''";
            ps = conector.prepareStatement(sql);
            ps.setString(1, ubicacion);
            res = ps.executeQuery();
            caja.removeAllItems();
            caja.addItem("Selecciona la división");
            while(res.next()){
                caja.addItem(res.getString("etapa_division"));
            }
            System.out.println("[DIVISION-llamar]: ¡Se han cargado las procedencias correctamente!");
        } catch (Exception e) {
            System.out.println("[DIVISION-ERROR1]: " + e);
        }
    } 
}
