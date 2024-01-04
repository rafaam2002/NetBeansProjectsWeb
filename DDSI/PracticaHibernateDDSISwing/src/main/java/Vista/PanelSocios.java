/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

/**
 *
 * @author rafaa
 */
public class PanelSocios extends javax.swing.JPanel {

    /**
     * Creates new form PanelSocios
     */
    public PanelSocios() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSocios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonNuevoSocio = new javax.swing.JButton();
        jButtonActualizarSocio = new javax.swing.JButton();
        jButtonBajaSocio = new javax.swing.JButton();
        jButtonSocioAltaActividad = new javax.swing.JButton();
        jButtonSocioBajaActividad = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableSocios);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("Gestión de Socios");

        jButtonNuevoSocio.setText("Alta");
        jButtonNuevoSocio.setActionCommand("NuevoSocio");

        jButtonActualizarSocio.setText("Actualización");
        jButtonActualizarSocio.setActionCommand("ActualizarSocio");

        jButtonBajaSocio.setText("Baja");
        jButtonBajaSocio.setActionCommand("BajaSocio");
        jButtonBajaSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBajaSocioActionPerformed(evt);
            }
        });

        jButtonSocioAltaActividad.setText("Dar de alta en actividad");
        jButtonSocioAltaActividad.setActionCommand("SocioAltaActividad");

        jButtonSocioBajaActividad.setText("Dar de baja en actividad");
        jButtonSocioBajaActividad.setActionCommand("SocioBajaActividad");

        jTextFieldBuscar.setActionCommand("BuscarSocio");
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSocioAltaActividad)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonNuevoSocio)
                                .addGap(36, 36, 36)
                                .addComponent(jButtonBajaSocio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSocioBajaActividad)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 139, Short.MAX_VALUE)
                                .addComponent(jButtonActualizarSocio)
                                .addGap(30, 30, 30))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonActualizarSocio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonBajaSocio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNuevoSocio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSocioAltaActividad)
                    .addComponent(jButtonSocioBajaActividad))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBajaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBajaSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBajaSocioActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonActualizarSocio;
    public javax.swing.JButton jButtonBajaSocio;
    public javax.swing.JButton jButtonNuevoSocio;
    public javax.swing.JButton jButtonSocioAltaActividad;
    public javax.swing.JButton jButtonSocioBajaActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableSocios;
    public javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
