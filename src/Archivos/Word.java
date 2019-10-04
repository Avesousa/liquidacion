package Archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Word {
   private XWPFDocument d; 
   
   public Word(){
       d = new XWPFDocument();
   }
   
   public Word(String file){
       try {
           ZipSecureFile.setMinInflateRatio(0);
           d = new XWPFDocument(new FileInputStream(file));
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void crearParrafo(String texto, boolean negrita, int size){
       XWPFParagraph parrafo = d.createParagraph();
       XWPFRun escribir = parrafo.createRun();
       escribir.setBold(negrita);
       escribir.setFontSize(size);
       escribir.setText(texto);
   }
   
   public void reemplazar(String clave, String valor){
       for(XWPFParagraph parrafo : d.getParagraphs())
           for(XWPFRun escrito : parrafo.getRuns()){
               String texto = escrito.getText(0);
               if(texto != null && texto.contains(clave)){
                   texto = texto.replace(clave, valor);
                   escrito.setText(texto,0);
               }
           }
   }
   
   public void reemplazar(String clave, List<String> valores){
       for(XWPFParagraph parrafo : d.getParagraphs())
           for(XWPFRun escrito : parrafo.getRuns()){
               String texto = escrito.getText(0);
               if(texto != null && texto.contains(clave)){
                   texto = texto.replace(clave,"");
                   escrito.setText(texto,0);
                   for(String valor: valores){
                            escrito.setText(valor);
                            escrito.addBreak();
                            escrito.addBreak();
                   }
               }
           }              
   }
   
    public void reemplazar(String[] llaves, String[] valores){
        for(XWPFParagraph parrafo : d.getParagraphs())
            for(XWPFRun escrito : parrafo.getRuns()){
                String texto = escrito.getText(0);
                for(int i = 0; i < llaves.length; i++)
                    if(texto != null && texto.contains(llaves[i])){
                        texto = texto.replace(llaves[i], valores[i]);
                        escrito.setText(texto,0);
                }
           }
   }
   
   public void guardarArchivo(String ruta){
       try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            d.write(archivo);
            archivo.close();
       } catch (Exception e) {
           System.out.println("[WORD] ¡Ha ocurrido un error!: " + e);
       }
   }
}
