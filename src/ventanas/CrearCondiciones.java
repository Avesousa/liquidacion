package ventanas;

import Clases.Cooperativa;
import Clases.Trabajador;
import Conectores.*;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CrearCondiciones extends javax.swing.JFrame {

    dbProcedencia coProcedencia;
    dbTrabajador coTrabajadores;
    List<Cooperativa> cooperativas;
    List<Trabajador> trabajadores;
    List<JTextField> inputs = new ArrayList();
    List<JComboBox> select = new ArrayList();

    public CrearCondiciones() {
        initComponents();
        coTrabajadores = new dbTrabajador();
        traerTrabajadores();
        p_info.setEnabledAt(1, false);
        p_info.setEnabledAt(2, false);
        t_division.setVisible(false);
        d_division.setVisible(false);
        t_procedencia.setVisible(false);
        d_procedencia.setVisible(false);
        armarInputs();
        this.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_tabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "TIPO", "COOPERATIVA", "FUNCIÓN", "UBICACIÓN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colocarDatos(evt);
            }
        });
        p_tabla.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setMinWidth(150);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(1).setMaxWidth(200);
            tabla.getColumnModel().getColumn(2).setMinWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setMaxWidth(200);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setMinWidth(120);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(6).setMaxWidth(300);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(120);
        }

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1)
                    .addComponent(p_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE))
                .addGap(0, 571, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void traerProcedencia(int num) {
        if (d_cooperativa.getSelectedIndex() > 0) {
            int coop = cooperativas.get((this.d_cooperativa.getSelectedIndex() - 1)).id;
            coProcedencia = new dbProcedencia();
            coProcedencia.traerProcedencias(this.d_procedencia, coop);
        }
        this.d_procedencia.setVisible(d_procedencia.getItemCount() > 1 && d_cooperativa.getSelectedIndex() > 0);
        this.t_procedencia.setVisible(d_procedencia.getItemCount() > 1 && d_cooperativa.getSelectedIndex() > 0);
        this.d_division.setVisible(false);
        this.t_division.setVisible(false);
        if(num >= 0){
            trabajadores.get(num).colocarProcedencia();
            traerDivision(num);
        }
    }

    public void traerDivision(int num) {
        if (d_procedencia.getSelectedIndex() > 0) {
            String ubicacion = (String) d_procedencia.getSelectedItem();
            coProcedencia.traerDivision(d_division, ubicacion);
        }
        d_division.setVisible(d_division.getItemCount() > 1 && d_procedencia.getSelectedIndex() > 0);
        t_division.setVisible(d_division.getItemCount() > 1 && d_procedencia.getSelectedIndex() > 0);
        if(num >= 0)
           trabajadores.get(num).colocarDivision();
    }

    private void colocarDatos(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colocarDatos
        //DefaultTableModel grilla = (DefaultTableModel) this.tabla.getModel();
        int filaSeleccionada = tabla.getSelectedRow();
        traerTodo();
        trabajadores.get(filaSeleccionada).colocarInput(inputs, select);
        traerProcedencia(filaSeleccionada);
        this.d_nacimiento.setDate(trabajadores.get(filaSeleccionada).fecha());
        this.p_info.setEnabledAt(1, true);
        this.p_info.setEnabledAt(2, true);
    }//GEN-LAST:event_colocarDatos

    private void traerTrabajadores() {
        DefaultTableModel tabla = (DefaultTableModel) this.tabla.getModel();
        coTrabajadores.traerRecuperadores(tabla);
        trabajadores = coTrabajadores.darListaTrabajadores();
    }

    private void traerFuncion() {
        new dbFuncion().traerFuncion(this.d_funciones);
    }

    private boolean validar(int panel) {
        switch (panel) {
            case 1:
                traerTodo();
                return !(("").equals(this.d_apellido.getText()) && ("").equals(this.d_nombre.getText()));
            case 2:
                return this.d_cooperativa.getSelectedIndex() != 0;

            default:
                return false;
        }
    }

    private void traerTipo() {
        coTrabajadores.traerTipos(d_tipo);
    }

    private void traerCooperativa() {
        dbCooperativa co = new dbCooperativa();
        co.llamarCooperativas(d_cooperativa);
        cooperativas = co.coops;
    }

    private void traerTodo() {
        traerCooperativa();
        traerTipo();
        traerFuncion();
    }

    private void armarInputs() {

        inputs.add(this.d_apellido);
        inputs.add(this.d_cabal);
        inputs.add(this.d_cbu);
        inputs.add(this.d_cuil);
        inputs.add(this.d_documento);
        inputs.add(this.d_email);
        inputs.add(this.d_nombre);
        inputs.add(this.d_rur);
        inputs.add(this.d_telefono);
        inputs.add(this.d_id);
        select.add(this.d_cooperativa);
        select.add(this.d_division);
        select.add(this.d_funciones);
        select.add(this.d_genero);
        select.add(this.d_procedencia);
        select.add(this.d_tipo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane p_tabla;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
