package ventanas;

import Clases.DiasMesAnio;
import Clases.Fecha;
import Clases.Feriados;
import Conectores.dbFeriados;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import liquidacion.Generar;

public class CrearPago extends javax.swing.JFrame {

    private String lineaPersona = "\n20CCCCCCCCCCCCCCCIIIIIIIIII AAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDD00000               ";
    public CrearPago() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.barraProgreso.setVisible(false);
        this.fechaPrimaria.setDate(new Date());
        this.fechaPrimaria.setMaxSelectableDate(new Date());
        this.fechaSecundaria.setMaxSelectableDate(new Date());
        //this.textoProgreso.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        fechaPrimaria = new com.toedter.calendar.JDateChooser();
        fechaSecundaria = new com.toedter.calendar.JDateChooser();
        obtener_fecha = new javax.swing.JButton();
        d_ruta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        textoProgreso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensaje = new javax.swing.JTextArea();
        boton_ruta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        obtener_fecha.setText("Generar Planillas");
        obtener_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obtener_fechaActionPerformed(evt);
            }
        });

        jLabel1.setText("Ruta para guardar.");

        barraProgreso.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        barraProgreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                barraProgresoStateChanged(evt);
            }
        });

        mensaje.setColumns(20);
        mensaje.setRows(5);
        mensaje.setText("Estas son las planillas del presente.");
        jScrollPane1.setViewportView(mensaje);

        boton_ruta.setText("...");
        boton_ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traerRuta(evt);
            }
        });

        jLabel2.setText("Fecha de inicio");

        jLabel3.setText("Fecha final");

        jLabel4.setText("Mensaje para incluir al correo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(obtener_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                            .addComponent(textoProgreso)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(306, 306, 306)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(fechaPrimaria, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fechaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(d_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(boton_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel4))))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoProgreso)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(d_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boton_ruta))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(fechaPrimaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(obtener_fecha)
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtener_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obtener_fechaActionPerformed
        if(!this.d_ruta.getText().equals("")){
            Date fechaUno = this.fechaPrimaria.getDate();
            Date fechaDos = this.fechaSecundaria.getDate();
            Calendar calenUno = this.fechaPrimaria.getCalendar();
            Calendar calenDos = this.fechaSecundaria.getCalendar();
            String rutaR = this.d_ruta.getText();
            List<DiasMesAnio> dias = new ArrayList();
            try{
            File archivo = new File(rutaR+"/prueba.txt");
            if(!archivo.exists())
                archivo.createNewFile();
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(primeraLinea());
            bw.write(crearLinea("297710296922000","AGUIRRE JORGE ANIBAL FIGUEIRA","31014853","1540020"));
            bw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_obtener_fechaActionPerformed
    
    private void barraProgresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_barraProgresoStateChanged
        
    }//GEN-LAST:event_barraProgresoStateChanged

    private void traerRuta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traerRuta
        JFileChooser box = new JFileChooser();
        box.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = box.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            this.d_ruta.setText(box.getSelectedFile().getPath() + "\\");
        }
        
    }//GEN-LAST:event_traerRuta
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton boton_ruta;
    public javax.swing.JTextField d_ruta;
    public com.toedter.calendar.JDateChooser fechaPrimaria;
    public com.toedter.calendar.JDateChooser fechaSecundaria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea mensaje;
    private javax.swing.JButton obtener_fecha;
    public javax.swing.JLabel textoProgreso;
    // End of variables declaration//GEN-END:variables
    
    //C:CUENTA / I:IMPORTE / A:ASOCIADO / D:DOCUMENTO
    
    private String crearLinea(String cabal, String asociado, String documento, String monto){
        char[] banderaLinea = lineaPersona.toCharArray();
        System.out.println("CREAR LINEA");
        int doc = documento.length()-1;
        int nom = 0;
        int mon = monto.length()-1;
        int cab = cabal.length()-1;
        for(int i = banderaLinea.length -1; i >= 0; i--){
            System.out.println(banderaLinea[i]);
            if(i > 57 && i < 70 ){
                try{
                    banderaLinea[i] = documento.charAt(doc);
                    doc--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            } else if(i > 16 && i < 28){
                try{
                    banderaLinea[i] = monto.charAt(mon);
                    mon--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            } else if(i > 0 && i < 18){
                try{
                    banderaLinea[i] = cabal.charAt(cab);
                    cab--;
                }catch(IndexOutOfBoundsException e){
                    banderaLinea[i] = '0';
                }
            }
        }
        
        for(int i = 29; i < (29+29); i++){
            if(i < 59){
                try {
                    banderaLinea[i] = asociado.charAt(nom);
                    nom++;
                } catch (Exception e) {
                    banderaLinea[i] = ' ';
                }
            }
        }
        
        return String.valueOf(banderaLinea);
        
    }
    
    private String primeraLinea() {
        String linea = "12018050101052000201687                    34999032089034180                             ";
        char[] banderaLinea = linea.toCharArray();
        
        Date fecha = new Date();
        
        String anio = String.valueOf(fecha.getYear() + 1900);
        String mes = (fecha.getMonth() + 1) < 10 ? "0"+String.valueOf(fecha.getMonth() + 1) : String.valueOf(fecha.getMonth() + 1);
        String dia = (fecha.getDay() < 10) ? "0"+String.valueOf(fecha.getDay()) : String.valueOf(fecha.getDay());
        
        for(int i = 1; i < 5; i++)
            banderaLinea[i] = anio.charAt(i-1);
        for(int j = 5; j < 7; j++)
            banderaLinea[j] = mes.charAt(j-5);
        for(int k = 7; k < 9; k++)
            banderaLinea[k] = dia.charAt(k-7);
        
        return String.valueOf(banderaLinea);
        
    }
}
