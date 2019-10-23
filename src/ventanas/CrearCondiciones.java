package ventanas;

import Clases.CerrarVentana;
import Clases.Cooperativa;
import Clases.Fecha;
import Clases.Trabajador;
import Conectores.*;
import Dialogos.CondicionMasiva;
import Metodos.Hacer;
import com.toedter.calendar.JDateChooser;
import hilos.TraerTrabajadores;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CrearCondiciones extends JFrame {

    public dbTrabajador coTrabajadores;
    public List<Trabajador> trabajadores;
    List<JTextField> inputs = new ArrayList();
    List<JComboBox> select = new ArrayList();
    public boolean esPrimeraVez = true;
    private Thread hilo = null;
    private JFrame casa;
    public DefaultTableModel modelo;

    public CrearCondiciones(JFrame casa) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.casa = casa;
        this.setVisible(true);
        this.casa.setVisible(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.texto_finalizar.setVisible(false);
        this.DateEnd.setDate(new java.util.Date());
        this.DateStart.setDate(new java.util.Date());
        coTrabajadores = new dbTrabajador();
        traerTrabajadores();
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        p_tabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        buscador = new javax.swing.JTextField();
        DateStart = new com.toedter.calendar.JDateChooser();
        DateEnd = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        texto_finalizar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "TIPO", "COOPERATIVA", "FUNCIÓN", "UBICACIÓN", "MÉTODO", "MONTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                actualizarDatos(evt);
            }
        });
        p_tabla.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
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
            tabla.getColumnModel().getColumn(8).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        buscador.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar(evt);
            }
        });

        DateStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hacerFiltro(evt);
            }
        });

        DateEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hacerFiltro(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("Información para agregar:");

        jLabel3.setText("- CABAL");

        jLabel4.setText("- MANUAL");

        jLabel5.setText("- RAI");

        jLabel6.setText("- CBU");

        jLabel7.setText("La información para agregar en método debe ser agregado de está manera");

        texto_finalizar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        texto_finalizar.setText("FINALIZÓ LA CARGA");

        jButton1.setText("CREAR CONDICIONES MASIVAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearMasivo(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/search.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(p_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel2)))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto_finalizar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buscador)
                        .addComponent(DateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(logo))
                    .addComponent(texto_finalizar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(p_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hacerFiltro(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_hacerFiltro
        this.buscador.setText("");
        if(!esPrimeraVez){
            if(hilo != null)
                JOptionPane.showMessageDialog(null, "Deberá esperar a que la aplicación termine de cargar los usuarios al sistema, para poder generar nueva búsqueda");
            this.traerTrabajadores();
        }
    }//GEN-LAST:event_hacerFiltro

    private void actualizarDatos(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actualizarDatos
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            actualizar("NO","NO");
        }
    }//GEN-LAST:event_actualizarDatos

    public void actualizar(String met, String mon){
        for(int fila : tabla.getSelectedRows()){
            String metodo = met.equals("NO") ? this.tabla.getValueAt(fila,8).toString() : met;
            String montoString = mon.equals("NO") ? this.tabla.getValueAt(fila,9).toString() : (Double.parseDouble(mon) > 0 ? mon : this.tabla.getValueAt(fila,9).toString());
            int id = Integer.parseInt(this.tabla.getValueAt(fila,0).toString());
            double monto = 0;
            ActualizarDatos datos = new ActualizarDatos();
            if(metodo.equals("RAI") || metodo.equals("CBU") || metodo.equals("CABAL") || metodo.equals("MANUAL") || metodo.equals("SIN")){
                try {
                    monto = Double.parseDouble(montoString);
                    this.tabla.setValueAt(monto,fila,9);
                    this.tabla.setValueAt(metodo,fila,8);
                    if(monto > 0 || !metodo.equals("SIN")){
                        datos.colocarDatos(id,monto,metodo,Fecha.convertir(DateStart.getDate()),Fecha.convertir(DateEnd.getDate()),this);
                        new Thread(datos).start();
                    }
                } catch (NumberFormatException e) {
                    this.tabla.setValueAt(0.0, this.tabla.getSelectedRow(),9);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El valor colocado es invalido");
                this.tabla.setValueAt("SIN",fila,9);
                
            }
        }
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(hilo != null)
            JOptionPane.showMessageDialog(null, "Deberá esperar a que la aplicación termine de cargar los usuarios al sistema, para poder cerrar");
        else{
            this.dispose();
            casa.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosing

    private void crearMasivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearMasivo
        if(hilo == null)
            if(tabla.getSelectedRowCount() > 0)
                new CondicionMasiva(this,true,this).setVisible(true);
            else
                JOptionPane.showMessageDialog(null,"No haz seleccionado ninguna fila");
        else{
            JOptionPane.showMessageDialog(null,"Deberás esperar a que la aplicación termine de cargar los usuarios, para poder hacer una carga masiva..");
        }
    }//GEN-LAST:event_crearMasivo

    private void buscar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar
        buscador.setText(buscador.getText().toUpperCase());
        new Hacer().filtro(buscador.getText().toUpperCase(), tabla);
    }//GEN-LAST:event_buscar

    
    public void traerTrabajadores() {
            trabajadores = null;
            modelo = (DefaultTableModel)this.tabla.getModel();
            modelo.setRowCount(0);
            Thread hilo = new Thread(new TraerTrabajadores(this));
            hilo.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JDateChooser DateEnd;
    public com.toedter.calendar.JDateChooser DateStart;
    private javax.swing.JTextField buscador;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel logo;
    private javax.swing.JScrollPane p_tabla;
    public javax.swing.JTable tabla;
    public javax.swing.JLabel texto_finalizar;
    // End of variables declaration//GEN-END:variables
}
