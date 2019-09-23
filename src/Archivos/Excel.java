package Archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    private Workbook libro;
    public Sheet hoja;
    public Row fila;
    public Row filaPlantilla;
    public Cell celda;
    public CellRangeAddress rango;
    
    public Excel(){
        libro = new XSSFWorkbook();
    }
    
    public Excel(String file){
        try {
        ZipSecureFile.setMinInflateRatio(0);
        FileInputStream archivo = new FileInputStream(file);
        libro = new XSSFWorkbook(archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void crearHoja(String nombre){
         hoja = libro.createSheet(nombre);
    }
    
    public void traerHoja(int ubi){
        hoja = libro.getSheetAt(ubi);
    }
    
    public CellRangeAddress generarRango(int fila, int priCol,int SegCol){
        return rango = new CellRangeAddress(fila,fila,priCol,SegCol);
    }
    
    public CellRangeAddress generarRango(int priFila, int segFila, int priCol, int segCol){
        return rango = new CellRangeAddress(priFila,segFila,priCol,segCol);
    }
    
    public CellRangeAddress generarRango(int filaSel, int colSel){
        return rango = new CellRangeAddress(filaSel,filaSel,colSel,colSel);
    }
    
    public void proteger(String clave){
        hoja.protectSheet(clave);
    }
    
    public Row agregarFila(int ubicacion){
        return this.fila = hoja.createRow(ubicacion);
    }
    
    public Row traerFila(int ubicacion){
        return this.fila = hoja.getRow(ubicacion);
    }
    
    //Define celda, sin adaptación de tamaño
    public Cell agregarCelda(int i, Row f){
        return this.celda = f.createCell(i);
    }
    //Define celda, con adaptación
    public void agregarCelda(Row f, int i){
        this.celda = f.createCell(i);
        adaptarCelda(i);
    }
    
    public Cell agregarCelda(int i, Row f, boolean t){
        this.celda = f.createCell(i);
        adaptarCelda(i);
        return this.celda;
    }
    
    public Cell traerCelda(int ubicacion){
        return this.celda = fila.getCell(ubicacion);
    }
    
    public void bloquear(boolean valor){
        try {
            CellStyle estilo = libro.createCellStyle();
            estilo.setLocked(valor);
            celda.setCellStyle(estilo);
            //System.out.println("BLOQUEADO " + fila.getRowNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void definirAncho(int col, int ancho){
        hoja.setColumnWidth(col, ancho);
    }
    
    public void guardarArchivo(String ruta){
        try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            libro.write(archivo);
            archivo.close();
            
        } catch (Exception e) {
            System.out.println("[EXCEL] ¡Ha ocurrido un error!: " + e);
        }
    }
    
    public void adaptarCelda(int col){
        hoja.autoSizeColumn(col);
    }
    
    public void formulaEvaluador(){
        libro.getCreationHelper().createFormulaEvaluator().evaluate(celda);
    }
    
    
    
}
