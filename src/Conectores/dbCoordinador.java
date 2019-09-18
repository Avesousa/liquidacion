package Conectores;

import Clases.Coordinador;
import Clases.Etapas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dbCoordinador extends Conexion{
    
    List<Coordinador> coordinadores = new ArrayList();
    Coordinador coordinador;
    
    public List<Coordinador> traerCoordinadores() throws SQLException{
        dbEtapa co = new dbEtapa();
        sql = "SELECT * FROM incentivo.coordinador";
        res = conector.prepareStatement(sql).executeQuery();
        while(res.next()){
            coordinador = new Coordinador(
                    res.getInt(1),
                    res.getString(2),
                    res.getString(3),
                    co.traerEtapas(res.getInt(1)));
            coordinadores.add(coordinador);
        }
        return coordinadores;
    }
    
}