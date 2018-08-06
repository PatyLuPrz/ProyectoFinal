/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 *
 * @author Norberto
 */
public class JFMenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JFMenuPrincipal
     */
    public JFMenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null); /*Esta funcion permite centrar el JFrame*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenuSecundario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButtonClientes = new javax.swing.JButton();
        jLabelServicios = new javax.swing.JButton();
        jButtonVentas = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU");
        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(1046, 620));
        setMinimumSize(new java.awt.Dimension(710, 621));
        setPreferredSize(new java.awt.Dimension(710, 621));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelMenuSecundario.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuSecundario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMenuSecundario.setMaximumSize(new java.awt.Dimension(707, 620));
        jPanelMenuSecundario.setMinimumSize(new java.awt.Dimension(707, 620));
        jPanelMenuSecundario.setPreferredSize(new java.awt.Dimension(707, 620));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blanco-logo.png"))); // NOI18N

        jButtonClientes.setBackground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonClientes.setText("Clientes");
        jButtonClientes.setBorder(null);
        jButtonClientes.setContentAreaFilled(false);
        jButtonClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClientes.setOpaque(true);
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jLabelServicios.setBackground(new java.awt.Color(255, 255, 255));
        jLabelServicios.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelServicios.setText("Servicios");
        jLabelServicios.setBorder(null);
        jLabelServicios.setContentAreaFilled(false);
        jLabelServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelServicios.setOpaque(true);
        jLabelServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelServiciosActionPerformed(evt);
            }
        });

        jButtonVentas.setBackground(new java.awt.Color(255, 255, 255));
        jButtonVentas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonVentas.setText("Ventas");
        jButtonVentas.setBorder(null);
        jButtonVentas.setContentAreaFilled(false);
        jButtonVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVentas.setOpaque(true);
        jButtonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasActionPerformed(evt);
            }
        });

        jButtonProductos.setBackground(new java.awt.Color(255, 255, 255));
        jButtonProductos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonProductos.setText("Productos");
        jButtonProductos.setBorder(null);
        jButtonProductos.setContentAreaFilled(false);
        jButtonProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonProductos.setOpaque(true);
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuSecundarioLayout = new javax.swing.GroupLayout(jPanelMenuSecundario);
        jPanelMenuSecundario.setLayout(jPanelMenuSecundarioLayout);
        jPanelMenuSecundarioLayout.setHorizontalGroup(
            jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap(303, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(0, 293, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelServicios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuSecundarioLayout.setVerticalGroup(
            jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSecundarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButtonVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanelMenuSecundario);
        jPanelMenuSecundario.setBounds(0, 0, 707, 620);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Acerca de");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        JFClientes clientes = new JFClientes();
        clientes.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jLabelServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelServiciosActionPerformed
        JFServicios servicios = new JFServicios();
        servicios.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelServiciosActionPerformed

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        JFVentas ventas = new JFVentas();
        ventas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
        JFProductos productos = new JFProductos();
        productos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonProductosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jLabelServicios;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanelMenuSecundario;
    // End of variables declaration//GEN-END:variables
}
