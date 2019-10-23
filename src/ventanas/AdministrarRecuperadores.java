package ventanas;

import Clases.Cooperativa;
import Clases.Trabajador;
import Conectores.*;
import Metodos.Hacer;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AdministrarRecuperadores extends javax.swing.JFrame {

    dbProcedencia coProcedencia;
    dbTrabajador coTrabajadores;
    List<Cooperativa> cooperativas;
    List<Trabajador> trabajadores;
    List<JTextField> inputs = new ArrayList();
    List<JComboBox> select = new ArrayList();

    public AdministrarRecuperadores() {
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

        p_info = new javax.swing.JTabbedPane();
        p_personal = new javax.swing.JPanel();
        d_nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        d_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        d_cuil = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        d_apellido = new javax.swing.JTextField();
        d_nacimiento = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        d_documento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        d_telefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        b_per_sig = new javax.swing.JButton();
        d_genero = new javax.swing.JComboBox<>();
        limpiador = new javax.swing.JButton();
        p_pagos = new javax.swing.JPanel();
        d_cabal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        d_cbu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        b_pag_agregar = new javax.swing.JButton();
        p_trabajo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        t_procedencia = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        d_rur = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        b_trab_sig = new javax.swing.JButton();
        d_cooperativa = new javax.swing.JComboBox<>();
        d_procedencia = new javax.swing.JComboBox<>();
        d_division = new javax.swing.JComboBox<>();
        t_division = new javax.swing.JLabel();
        d_tipo = new javax.swing.JComboBox<>();
        d_funciones = new javax.swing.JComboBox<>();
        d_id = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        p_tabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buscador = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        d_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verificacionLetra(evt);
            }
        });

        jLabel1.setText("NOMBRE(*)");

        jLabel3.setText("EMAIL");

        jLabel5.setText("CUIL");

        d_cuil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verificacionNumero(evt);
            }
        });

        jLabel6.setText("GENERO");

        jLabel18.setText("APELLIDO(*)");

        d_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verificacionLetra(evt);
            }
        });

        jLabel16.setText("FECHA DE NACIMIENTO");

        d_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verificacionNumero(evt);
            }
        });

        jLabel17.setText("DOCUMENTO(*)");

        d_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verificacionNumero(evt);
            }
        });

        jLabel4.setText("TELEFONO");

        b_per_sig.setText("SIGUIENTE");
        b_per_sig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarPanel(evt);
            }
        });

        d_genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));

        limpiador.setText("limpiar");
        limpiador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p_personalLayout = new javax.swing.GroupLayout(p_personal);
        p_personal.setLayout(p_personalLayout);
        p_personalLayout.setHorizontalGroup(
            p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_personalLayout.createSequentialGroup()
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(d_nombre)
                            .addComponent(jLabel17)
                            .addComponent(d_documento)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(d_email)
                            .addComponent(d_genero, 0, 186, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_personalLayout.createSequentialGroup()
                        .addContainerGap(157, Short.MAX_VALUE)
                        .addComponent(limpiador, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_per_sig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addComponent(d_nacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(d_apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(d_cuil)
                    .addComponent(jLabel4)
                    .addComponent(d_telefono))
                .addGap(39, 39, 39))
        );
        p_personalLayout.setVerticalGroup(
            p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_personalLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(7, 7, 7)
                        .addComponent(d_nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(d_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(d_cuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(d_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_personalLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(p_personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_per_sig)
                    .addComponent(limpiador))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        p_info.addTab("DATOS PERSONALES", p_personal);

        jLabel15.setText("CUENTA CABAL");

        jLabel7.setText("CBU");

        b_pag_agregar.setText("AGREGAR");
        b_pag_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarPanel(evt);
            }
        });

        javax.swing.GroupLayout p_pagosLayout = new javax.swing.GroupLayout(p_pagos);
        p_pagos.setLayout(p_pagosLayout);
        p_pagosLayout.setHorizontalGroup(
            p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_pagosLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(d_cabal, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_pagosLayout.createSequentialGroup()
                        .addComponent(b_pag_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_pagosLayout.createSequentialGroup()
                        .addGroup(p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(d_cbu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))))
        );
        p_pagosLayout.setVerticalGroup(
            p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_pagosLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(p_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_pagosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(d_cbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_pagosLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_cabal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(b_pag_agregar)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        p_info.addTab("DATOS DE PAGOS", p_pagos);

        jLabel13.setText("COOPERATIVA");

        t_procedencia.setText("UBICACIÓN");

        jLabel12.setText("TIPO");

        jLabel10.setText("FUNCIÓN");

        jLabel14.setText("RUR");

        b_trab_sig.setText("SIGUIENTE");
        b_trab_sig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarPanel(evt);
            }
        });

        d_cooperativa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                traerProcedencia(evt);
            }
        });

        d_procedencia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                traerDivision(evt);
            }
        });

        t_division.setText("DIVISIÓN");

        d_id.setEditable(false);

        jLabel19.setText("ID RECUPERADOR");

        javax.swing.GroupLayout p_trabajoLayout = new javax.swing.GroupLayout(p_trabajo);
        p_trabajo.setLayout(p_trabajoLayout);
        p_trabajoLayout.setHorizontalGroup(
            p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_trabajoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(d_cooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_procedencia)
                    .addComponent(jLabel10)
                    .addComponent(d_procedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d_funciones, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(d_id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_trabajoLayout.createSequentialGroup()
                        .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(p_trabajoLayout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(b_trab_sig, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p_trabajoLayout.createSequentialGroup()
                                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t_division)
                                    .addComponent(jLabel12))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(d_division, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(d_rur, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(39, 39, 39))
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(d_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap())))
        );
        p_trabajoLayout.setVerticalGroup(
            p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_trabajoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel19)
                .addGap(6, 6, 6)
                .addComponent(d_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(d_cooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(26, 26, 26)))
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(t_procedencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_procedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_trabajoLayout.createSequentialGroup()
                        .addComponent(t_division)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addGap(6, 6, 6)
                .addGroup(p_trabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d_rur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d_funciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(b_trab_sig)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        p_info.addTab("DATOS DE TRABAJO", p_trabajo);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "TIPO", "COOPERATIVA", "FUNCIÓN", "UBICACIÓN", "DIVISIÓN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        }

        jButton1.setText("CREAR NUEVO");

        jButton2.setText("ACTUALIZAR");

        buscador.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorbuscar(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/search.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(p_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(p_info, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(logo))
                            .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_tabla)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cambiarPanel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarPanel
        int panel = (this.p_info.getSelectedIndex() + 1);
        System.out.println(panel);
        if (validar(panel)) {
            this.p_info.setSelectedIndex(panel);
            this.p_info.setEnabledAt(panel, true);
        } else {
            JOptionPane.showMessageDialog(null, "Hay datos por agregar...");
        }
    }//GEN-LAST:event_cambiarPanel

    private void verificacionNumero(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificacionNumero

        char caracter = evt.getKeyChar();
        if (caracter < '0' || caracter > '9') {
            evt.consume();
        }

    }//GEN-LAST:event_verificacionNumero

    private void verificacionLetra(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificacionLetra
        char c = evt.getKeyChar();
        evt.setKeyChar(("" + c).toUpperCase().charAt(0));
        char caracter = evt.getKeyChar();
        if (Character.isDigit(caracter)) {
            evt.consume();
        }
    }//GEN-LAST:event_verificacionLetra

    private void traerProcedencia(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_traerProcedencia
        traerProcedencia(-1);
    }//GEN-LAST:event_traerProcedencia

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

    private void limpiadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiadorActionPerformed
        this.p_personal.removeAll();
        this.p_trabajo.removeAll();
        this.p_pagos.removeAll();
    }//GEN-LAST:event_limpiadorActionPerformed

    private void traerDivision(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_traerDivision
        traerDivision(-1);
    }//GEN-LAST:event_traerDivision

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

    private void buscadorbuscar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorbuscar
        buscador.setText(buscador.getText().toUpperCase());
        new Hacer().filtro(buscador.getText().toUpperCase(), tabla);
    }//GEN-LAST:event_buscadorbuscar

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
    private javax.swing.JButton b_pag_agregar;
    private javax.swing.JButton b_per_sig;
    private javax.swing.JButton b_trab_sig;
    private javax.swing.JTextField buscador;
    private javax.swing.JTextField d_apellido;
    private javax.swing.JTextField d_cabal;
    private javax.swing.JTextField d_cbu;
    private javax.swing.JComboBox<String> d_cooperativa;
    private javax.swing.JTextField d_cuil;
    private javax.swing.JComboBox<String> d_division;
    private javax.swing.JTextField d_documento;
    private javax.swing.JTextField d_email;
    private javax.swing.JComboBox<String> d_funciones;
    private javax.swing.JComboBox<String> d_genero;
    private javax.swing.JTextField d_id;
    private com.toedter.calendar.JDateChooser d_nacimiento;
    private javax.swing.JTextField d_nombre;
    private javax.swing.JComboBox<String> d_procedencia;
    private javax.swing.JTextField d_rur;
    private javax.swing.JTextField d_telefono;
    private javax.swing.JComboBox<String> d_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton limpiador;
    private javax.swing.JLabel logo;
    private javax.swing.JTabbedPane p_info;
    private javax.swing.JPanel p_pagos;
    private javax.swing.JPanel p_personal;
    private javax.swing.JScrollPane p_tabla;
    private javax.swing.JPanel p_trabajo;
    private javax.swing.JLabel t_division;
    private javax.swing.JLabel t_procedencia;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
