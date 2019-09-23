package Clases;

import Conectores.dbFeriados;
import java.util.Date;
import java.util.List;

public class VerificarFeriados {
    
    public static boolean verificar(Date dia) {
        dbFeriados co = new dbFeriados();
        co.traerFeriados();
        List<Feriados> feriado = co.lista;
        for(int i = 0; i < feriado.size(); i++){
            if((dia.getMonth() == feriado.get(i).darMes()
                    && dia.getDate() == feriado.get(i).darDia())){
                return false;
            }
        }
        return true;
    }
    
}
