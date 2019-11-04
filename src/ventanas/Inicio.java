package ventanas;

public class Inicio extends javax.swing.JFrame {

    private int user = 4;    
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cuerpo = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        logoba = new javax.swing.JLabel();
        logosed = new javax.swing.JLabel();
        programa = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ingreso_entrar = new javax.swing.JButton();
        ingreso_usuario = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        ingreso_clave = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1139, 746));
        setMinimumSize(new java.awt.Dimension(1139, 746));
        setPreferredSize(new java.awt.Dimension(1139, 746));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cuerpo.setBackground(new java.awt.Color(255, 255, 255));
        cuerpo.setForeground(new java.awt.Color(255, 255, 255));
        cuerpo.setMaximumSize(new java.awt.Dimension(1139, 746));
        cuerpo.setMinimumSize(new java.awt.Dimension(1139, 746));
        cuerpo.setPreferredSize(new java.awt.Dimension(1139, 746));
        cuerpo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cabecera.setBackground(new java.awt.Color(111, 116, 117));
        cabecera.setForeground(new java.awt.Color(111, 116, 117));
        cabecera.setMaximumSize(new java.awt.Dimension(836, 140));
        cabecera.setMinimumSize(new java.awt.Dimension(836, 140));
        cabecera.setPreferredSize(new java.awt.Dimension(836, 140));
        cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/header/logoBa.png"))); // NOI18N
        cabecera.add(logoba, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        logosed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/header/logoSed.png"))); // NOI18N
        cabecera.add(logosed, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        programa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/header/domiciliaria.png"))); // NOI18N
        cabecera.add(programa, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        cuerpo.add(cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jButton2.setText("CREAR FERIADO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/BA Ciudad Verde.png"))); // NOI18N
        cuerpo.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, -1, -1));

        jButton5.setText("ENVIAR CORREO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 148, -1));

        jButton1.setText("CREAR PLANILLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        ingreso_entrar.setText("Entrar");
        ingreso_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingreso_entrarActionPerformed(evt);
            }
        });
        cuerpo.add(ingreso_entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 582, 152, 55));

        ingreso_usuario.setText("Usuario");
        cuerpo.add(ingreso_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 464, 403, 44));

        jButton6.setText("CARGAR PLANILLAS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaPlanilla(evt);
            }
        });
        cuerpo.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jButton8.setText("CREAR CONDICIONES");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, -1, -1));

        ingreso_clave.setText("1234");
        cuerpo.add(ingreso_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 232, 44));

        jButton4.setText("ADMINISTRAR RECUPERADORES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jButton3.setText("ADMINISTRAR COOPERATIVAS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        jButton7.setText("GENERAR PAGOS");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        cuerpo.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, -1, -1));

        getContentPane().add(cuerpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingreso_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingreso_entrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingreso_entrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CrearPlanilla crearPlanilla = new CrearPlanilla(this);
        crearPlanilla.setVisible(true);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CrearFeriado feriado = new CrearFeriado();
        feriado.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AdministrarCooperativas coop = new AdministrarCooperativas();
        coop.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AdministrarRecuperadores recu = new AdministrarRecuperadores();
        recu.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        EnviarCorreo enviar = new EnviarCorreo();
        enviar.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cargaPlanilla(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaPlanilla
        CargarPlanillas cargar = new CargarPlanillas(user);
        cargar.setVisible(true);
        
    }//GEN-LAST:event_cargaPlanilla

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CrearPago crearPago = new CrearPago();
        crearPago.setVisible(true); 
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CrearCondiciones crearCondiciones = new CrearCondiciones(this);
    }//GEN-LAST:event_jButton8ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel cuerpo;
    private javax.swing.JPasswordField ingreso_clave;
    private javax.swing.JButton ingreso_entrar;
    private javax.swing.JTextField ingreso_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoba;
    private javax.swing.JLabel logosed;
    private javax.swing.JLabel programa;
    // End of variables declaration//GEN-END:variables
}
