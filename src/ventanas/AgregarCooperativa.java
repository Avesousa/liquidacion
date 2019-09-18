package ventanas;

public class AgregarCooperativa extends javax.swing.JDialog {

    AdministrarCooperativas administracion;
    
    public AgregarCooperativa(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public AgregarCooperativa(javax.swing.JFrame parent, boolean modal,AdministrarCooperativas admin) {
        super(parent, modal);
        this.administracion = admin;
        initComponents();
        this.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input_razon = new javax.swing.JTextField();
        input_abreviatura = new javax.swing.JTextField();
        texto_razon = new javax.swing.JLabel();
        texto_abreviatura = new javax.swing.JLabel();
        boton_agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        input_razon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hacerMayuscula(evt);
            }
        });

        input_abreviatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                limitarAbreviatura(evt);
                hacerMayuscula(evt);
            }
        });

        texto_razon.setText("RAZÓN SOCIAL DE COOPERATIVA:");

        texto_abreviatura.setText("ABREVIATURA DE COOPERATIVA:");

        boton_agregar.setText("AGREGAR");
        boton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(texto_abreviatura)
                            .addComponent(texto_razon)
                            .addComponent(input_abreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(input_razon, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(boton_agregar)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(texto_razon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(input_razon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(texto_abreviatura)
                .addGap(9, 9, 9)
                .addComponent(input_abreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_agregar)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limitarAbreviatura(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitarAbreviatura
        if(this.input_abreviatura.getText().length() == 3)
            evt.consume();
    }//GEN-LAST:event_limitarAbreviatura

    private void hacerMayuscula(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hacerMayuscula
        this.input_razon.setText(this.input_razon.getText().toUpperCase());
        this.input_abreviatura.setText(this.input_abreviatura.getText().toUpperCase());
    }//GEN-LAST:event_hacerMayuscula

    private void agregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar
        administracion.agregarCompany(this.input_razon.getText(),this.input_abreviatura.getText());
        this.dispose();
    }//GEN-LAST:event_agregar


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_agregar;
    private javax.swing.JTextField input_abreviatura;
    private javax.swing.JTextField input_razon;
    private javax.swing.JLabel texto_abreviatura;
    private javax.swing.JLabel texto_razon;
    // End of variables declaration//GEN-END:variables
}
