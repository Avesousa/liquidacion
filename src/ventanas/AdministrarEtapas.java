package ventanas;

import Conectores.dbCooperativa;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*public class AdministrarEtapas extends javax.swing.JFrame {
    DefaultTableModel tablaEn;
    DefaultTableModel tablaNo;
    DefaultTableModel tabla;
    dbCooperativa co;
    
    public AdministrarEtapas() {
        initComponents();
        traerCooperativas();
    }
    
    private void traerCooperativas(){
        tablaEn = (DefaultTableModel)this.tabla_en.getModel();
        tablaNo = (DefaultTableModel)this.tabla_no.getModel();
        co = new dbCooperativa();
        co.traerCooperativas(tablaEn, tablaNo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_en = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_no = new javax.swing.JTable();
        boton_agregar = new javax.swing.JButton();
        boton_quitar = new javax.swing.JButton();
        boton_agregarCoop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla_en.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COOPERATIVA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_en);

        tabla_no.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COOPERATIVA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_no);

        boton_agregar.setText(">>");
        boton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar(evt);
            }
        });

        boton_quitar.setText("<<");
        boton_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitar(evt);
            }
        });

        boton_agregarCoop.setText("Agregar Cooperativa");
        boton_agregarCoop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCooperativa(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_agregarCoop)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton_agregar)
                            .addComponent(boton_quitar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(boton_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton_quitar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(boton_agregarCoop)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar
        actualizar(true,this.tabla_no);
    }//GEN-LAST:event_agregar

    private void quitar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitar
        actualizar(false,this.tabla_en);
    }//GEN-LAST:event_quitar

    private void agregarCooperativa(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCooperativa
        new AgregarCooperativa(this,true,this);
    }//GEN-LAST:event_agregarCooperativa

    public void agregarCompany(String nombre, String abreviatura){
        co.agregarCooperativa(nombre, abreviatura);
        limpiarDatos();
    }
    
    private void actualizar(boolean estado, JTable table){
        int [] filas = table.getSelectedRows();
        int fila = table.getSelectedRow();
        tabla = (DefaultTableModel)table.getModel();
        for(int i = fila; i < (filas.length + fila); i++)
            co.actualizarCooperativa((Integer)tabla.getValueAt(i,0),estado);
        limpiarDatos();
    }
    
    public void limpiarDatos(){
        tablaEn.setRowCount(0);
        tablaNo.setRowCount(0);
        traerCooperativas();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_agregar;
    private javax.swing.JButton boton_agregarCoop;
    private javax.swing.JButton boton_quitar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_en;
    private javax.swing.JTable tabla_no;
    // End of variables declaration//GEN-END:variables
}*/
