package ventanas;

import Conectores.dbFeriados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CrearFeriado extends javax.swing.JFrame {
    DefaultTableModel tabla;
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    dbFeriados co;
    public CrearFeriado() {
        initComponents();
        this.id_feriado.setVisible(false);
        ((JTextField)this.fecha_feriado.getDateEditor()).setEditable(false);
        traerFeriados();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descripcion_feriado = new javax.swing.JTextField();
        texto_motivo = new javax.swing.JLabel();
        fecha_feriado = new com.toedter.calendar.JDateChooser();
        texto_fecha = new javax.swing.JLabel();
        teditar_feriado = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_feriado = new javax.swing.JTable();
        eliminar_feriado = new javax.swing.JButton();
        id_feriado = new javax.swing.JTextField();
        agregar_feriado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        descripcion_feriado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valoresVacios(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valoresVacios(evt);
            }
        });

        texto_motivo.setText("MOTIVO:");

        fecha_feriado.setForeground(new java.awt.Color(255, 255, 255));
        fecha_feriado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valoresVacios(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valoresVacios(evt);
            }
        });

        texto_fecha.setText("FECHA:");

        teditar_feriado.setText("Editar Feriado");
        teditar_feriado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarFeriado(evt);
            }
        });

        tabla_feriado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "MOTIVO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_feriado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarFeriado(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_feriado);

        eliminar_feriado.setText("ELIMINAR");
        eliminar_feriado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarFeriado(evt);
            }
        });

        agregar_feriado.setText("Agregar Feriado");
        agregar_feriado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearFeriado(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminar_feriado)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(texto_fecha)
                            .addComponent(texto_motivo)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(descripcion_feriado)
                        .addComponent(fecha_feriado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(agregar_feriado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teditar_feriado))
                    .addComponent(id_feriado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(id_feriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(texto_motivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcion_feriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(texto_fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha_feriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar_feriado)
                    .addComponent(teditar_feriado))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eliminar_feriado)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void traerFeriados(){
        tabla = (DefaultTableModel) this.tabla_feriado.getModel();
        co = new dbFeriados();
        co.traerFeriados(tabla);
        this.teditar_feriado.setVisible(false);
        this.agregar_feriado.setVisible(true);
        this.fecha_feriado.setEnabled(true);
    }
    
    private void crearFeriado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearFeriado
        String motivo = this.descripcion_feriado.getText();
        Date fecha = this.fecha_feriado.getDate();
        co.crearFeriados(tabla,fecha,motivo);
        limpiarDatos();
    }//GEN-LAST:event_crearFeriado

    private void seleccionarFeriado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarFeriado
        try {
            int fila = this.tabla_feriado.getSelectedRow();
            Date fechaSel = formato.parse((String)tabla.getValueAt(fila, 1));
            String motivo = (String)tabla.getValueAt(fila,2);
            String id = String.valueOf(tabla.getValueAt(fila, 0));
            this.fecha_feriado.setDate(fechaSel);
            this.descripcion_feriado.setText(motivo);
            this.id_feriado.setText(id);
            this.agregar_feriado.setVisible(false);
            this.teditar_feriado.setVisible(true);
            this.fecha_feriado.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_seleccionarFeriado

    private void valoresVacios(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valoresVacios
        if(this.descripcion_feriado.getText().equals("")){
            limpiarDatos();
        }
    }//GEN-LAST:event_valoresVacios

    private void editarFeriado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarFeriado
        String motivo = this.descripcion_feriado.getText();
        Date fecha = this.fecha_feriado.getDate();
        int id = Integer.parseInt(this.id_feriado.getText());
        co.editarFeriados(fecha, id, motivo);
        limpiarDatos();
    }//GEN-LAST:event_editarFeriado

    private void eliminarFeriado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarFeriado
        if(!("").equals(this.id_feriado.getText())) {
            int id = Integer.parseInt(this.id_feriado.getText());
            co.eliminarFeriado(id);
            limpiarDatos();
        }else{
            JOptionPane.showMessageDialog(null, "Al parecer hubo un error al eliminar");
        }
        
    }//GEN-LAST:event_eliminarFeriado
    
    private void limpiarDatos(){
        tabla.setRowCount(0);
        this.tabla_feriado.setModel(tabla);
        this.descripcion_feriado.setText("");
        this.fecha_feriado.setDate(null);
        this.id_feriado.setText("");
        traerFeriados();
        this.descripcion_feriado.requestFocus();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_feriado;
    private javax.swing.JTextField descripcion_feriado;
    private javax.swing.JButton eliminar_feriado;
    private com.toedter.calendar.JDateChooser fecha_feriado;
    private javax.swing.JTextField id_feriado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_feriado;
    private javax.swing.JButton teditar_feriado;
    private javax.swing.JLabel texto_fecha;
    private javax.swing.JLabel texto_motivo;
    // End of variables declaration//GEN-END:variables
}
