package pruebas;

import Archivos.Excel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

public class Correr {
    
    /*public static void main (String [] args){
    
        try {
            FileInputStream archivo = new FileInputStream("prueba.xls");
            Excel libro= new Excel(archivo);
            libro.traerHoja();
            libro.fila = libro.agregarFila(2);
            libro.agregarCelda(2, libro.fila).setCellValue("ESTO LO PUDE EDITAR CON ESTO :D");
            libro.guardarArchivo("pruebaSalida.xls"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }*/
    
}
