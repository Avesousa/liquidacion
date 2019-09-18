package Conectores;
import javax.swing.JComboBox;

public class dbFuncion extends Conexion{
    
    public void traerFuncion(JComboBox caja){
        try {
            sql = "SELECT descripcion FROM incentivo.funcion";
            ps = conector.prepareStatement(sql);
            res = ps.executeQuery();
            caja.removeAllItems();
            caja.addItem("Selecciona la función");
            while(res.next()){
                caja.addItem(res.getString("descripcion"));
            }
            System.out.println("[FUNCION-llamar]: ¡Se han cargado las procedencias correctamente!");
        } catch (Exception e) {
            System.out.println("[FUNCION-ERROR1]: " + e);
        }
    }  
}
