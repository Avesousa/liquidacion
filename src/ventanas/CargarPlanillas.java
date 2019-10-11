package ventanas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import liquidacion.Cargar;

public class CargarPlanillas extends javax.swing.JFrame {
    public int user;
    private List<String> lista = new ArrayList();
    public CargarPlanillas(int user) {
        initComponents();
        this.barra.setVisible(false);
        this.setLocationRelativeTo(null);
        this.user = user;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cargar = new javax.swing.JButton();
        ruta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaDeRespuesta = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_subir = new javax.swing.JButton();
        barra = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btn_cargar.setText("CARGAR");
        btn_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traerArchivos(evt);
            }
        });

        areaDeRespuesta.setColumns(20);
        areaDeRespuesta.setRows(5);
        jScrollPane1.setViewportView(areaDeRespuesta);

        jLabel1.setText("Archivos:");

        jLabel2.setText("Transacciones:");

        btn_subir.setText("SUBIR ARCHIVOS");
        btn_subir.setEnabled(false);
        btn_subir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchivos(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btn_subir)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                            .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cargar)
                    .addComponent(ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_subir)
                .addGap(73, 73, 73)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void traerArchivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traerArchivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de EXCEL","xlsx");
        JFileChooser box = new JFileChooser();
        box.setFileFilter(filtro);
        box.setMultiSelectionEnabled(true);
        int option = box.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            this.ruta.setText("Archivos seleccionados ("+box.getSelectedFiles().length + ")");
            for(File archivo : box.getSelectedFiles()){
                lista.add(archivo.getPath());
            }
            this.btn_subir.setEnabled(true);
        }
    }//GEN-LAST:event_traerArchivos

    private void subirArchivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchivos
        if(lista.size() > 0){
            Cargar carga = new Cargar(lista, this);
            carga.start();
            carga = null;
        } else {
            System.out.println("No hay lista");
        }
    }//GEN-LAST:event_subirArchivos

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea areaDeRespuesta;
    public javax.swing.JProgressBar barra;
    private javax.swing.JButton btn_cargar;
    private javax.swing.JButton btn_subir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField ruta;
    // End of variables declaration//GEN-END:variables
}
