package ventanas;

import Conectores.dbCooperativa;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AdministrarCooperativas extends javax.swing.JFrame {
    DefaultTableModel tablaNo;
    DefaultTableModel tabla;
    dbCooperativa co;
    
    public AdministrarCooperativas() {
        initComponents();
        traerCooperativas();
    }
    
    private void traerCooperativas(){
        tablaNo = (DefaultTableModel)this.tabla_no.getModel();
        co = new dbCooperativa();
        co.traerCooperativas(tablaNo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_no = new javax.swing.JTable();
        boton_agregarCoop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addContainerGap(519, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_agregarCoop)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_agregarCoop)
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        tablaNo.setRowCount(0);
        traerCooperativas();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_agregarCoop;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_no;
    // End of variables declaration//GEN-END:variables
}
