package ventanas;

import Clases.DiasMesAnio;
import Clases.Fecha;
import Clases.Feriados;
import Clases.GeneradorDePago;
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

    public CrearPago() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.barraProgreso.setVisible(false);
        //this.fechaSecundaria.setDate(new Date());
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
        boton_ruta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        motivo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

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

        boton_ruta.setText("...");
        boton_ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traerRuta(evt);
            }
        });

        jLabel2.setText("Fecha de inicio");

        jLabel3.setText("Fecha final");

        jButton1.setText("PRUEBA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Incentivo", "Reclamos", "Productividad" }));

        jLabel5.setText("PAGO DE:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(obtener_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoProgreso)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(306, 306, 306)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(d_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(boton_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(fechaPrimaria, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fechaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(obtener_fecha)
                    .addComponent(jButton1))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtener_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obtener_fechaActionPerformed
        if(!this.d_ruta.getText().equals("")){
            new GeneradorDePago(this.fechaPrimaria.getCalendar(),this.fechaSecundaria.getCalendar(),this.d_ruta.getText(),this.motivo.getSelectedItem().toString());
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton boton_ruta;
    public javax.swing.JTextField d_ruta;
    public com.toedter.calendar.JDateChooser fechaPrimaria;
    public com.toedter.calendar.JDateChooser fechaSecundaria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JComboBox<String> motivo;
    private javax.swing.JButton obtener_fecha;
    public javax.swing.JLabel textoProgreso;
    // End of variables declaration//GEN-END:variables
    
}
