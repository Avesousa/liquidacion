package Dialogos;

import javax.swing.JOptionPane;
import ventanas.CrearCondiciones;

public class CondicionMasiva extends javax.swing.JDialog {

    private CrearCondiciones condiciones;
    public CondicionMasiva(java.awt.Frame parent, boolean modal, CrearCondiciones condi) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.condiciones = condi;
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new javax.swing.JLabel();
        metodo = new javax.swing.JComboBox<>();
        monto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/logo-peq.png"))); // NOI18N

        metodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIN", "MANUAL", "RAI", "CBU", "CABAL" }));

        monto.setText("0");
        monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montoActionPerformed(evt);
            }
        });
        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                verificarNumero(evt);
            }
        });

        jButton1.setText("CREAR CONDICIONES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masivo(evt);
            }
        });

        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(154, 154, 154))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(monto)
                        .addComponent(metodo, 0, 325, Short.MAX_VALUE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addGap(27, 27, 27)
                .addComponent(metodo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verificarNumero(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificarNumero
        try {
            Double.parseDouble(monto.getText());
        } catch (Exception e) {
            monto.setText(monto.getText().replace(monto.getText().substring(monto.getText().length()-1), ""));
        }
    }//GEN-LAST:event_verificarNumero

    private void masivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masivo
        condiciones.actualizar(metodo.getSelectedItem().toString(), monto.getText());
        JOptionPane.showMessageDialog(null, "¡Se ha generado la carga masiva de condiciones!");
        this.dispose();
        
    }//GEN-LAST:event_masivo

    private void montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoActionPerformed

    private void cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar
        this.dispose();
    }//GEN-LAST:event_cancelar

    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel logo;
    private javax.swing.JComboBox<String> metodo;
    private javax.swing.JTextField monto;
    // End of variables declaration//GEN-END:variables
}
