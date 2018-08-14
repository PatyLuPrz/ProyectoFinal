/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import modelo.SqlUsuarios;
import modelo.usuarios;

public class JFMenuPrincipal extends javax.swing.JFrame {

    public static JFClientes JFclien;
    public static JFProductos JFprod;
    public static JFServicios JFserv;
    public static JFVentas JFventas;

    /**
     * Creates new form JFMenuPrincipal
     */
    public void user() {
        SqlUsuarios modSql = new SqlUsuarios();
        usuarios mod = new usuarios();

        System.out.println(mod.getTipo_user());
        System.out.println(mod.getUsername());
    }

    public JFMenuPrincipal() {
        user();

        //Insertar imagenes en un JLabel y redimensionarlas al tamaño del Jlabel 
        initComponents();
        ImageIcon imagenUno = new ImageIcon(getClass().getResource("/Images/ProductosMenu.png"));
        Image fondoUno = imagenUno.getImage().getScaledInstance(jLabelLogoProductos.getWidth(), jLabelLogoProductos.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(fondoUno);
        jLabelLogoProductos.setIcon(iconoEscalado);

        ImageIcon imagenDos = new ImageIcon(getClass().getResource("/Images/VentasMenu.png"));
        Image fondoDos = imagenDos.getImage().getScaledInstance(jLabelLogoVentas.getWidth(), jLabelLogoVentas.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoDos = new ImageIcon(fondoDos);
        jLabelLogoVentas.setIcon(iconoEscaladoDos);

        ImageIcon imagenTres = new ImageIcon(getClass().getResource("/Images/ServiciosMenu.png"));
        Image fondoTres = imagenTres.getImage().getScaledInstance(jLabelLogoServicios.getWidth(), jLabelLogoServicios.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoTres = new ImageIcon(fondoTres);
        jLabelLogoServicios.setIcon(iconoEscaladoTres);

        ImageIcon imagenCuatro = new ImageIcon(getClass().getResource("/Images/ClientesMenu.png"));
        Image fondoCuatro = imagenCuatro.getImage().getScaledInstance(jLabelLogoClientes.getWidth(), jLabelLogoClientes.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoCuatro = new ImageIcon(fondoCuatro);
        jLabelLogoClientes.setIcon(iconoEscaladoCuatro);
        this.setLocationRelativeTo(null);

        ImageIcon logoPrincipal = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = logoPrincipal.getImage().getScaledInstance(jLabelLogoPrincipal.getWidth(), jLabelLogoPrincipal.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoPrincipal.setIcon(iconoEscaladoLogo);
        //Fin de la insercion de las imagenes en un Jlabel
        this.setLocationRelativeTo(null);

        /*Esta funcion permite centrar el JFrame*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenuPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        TIPOUSUARIOMENU = new javax.swing.JLabel();
        USUARIOMENU = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonProductos = new javax.swing.JButton();
        jButtonVentas = new javax.swing.JButton();
        jLabelLogoPrincipal = new javax.swing.JLabel();
        jButtonServicios = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jLabelLogoProductos = new javax.swing.JLabel();
        jLabelLogoServicios = new javax.swing.JLabel();
        jLabelLogoVentas = new javax.swing.JLabel();
        jLabelLogoClientes = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuUsuarios = new javax.swing.JMenu();
        jMenuCerrarSesionUser = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU");
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelMenuPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        jPanelMenuPrincipal.setOpaque(false);
        jPanelMenuPrincipal.setRequestFocusEnabled(false);
        jPanelMenuPrincipal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelMenuPrincipalPropertyChange(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        TIPOUSUARIOMENU.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        TIPOUSUARIOMENU.setForeground(new java.awt.Color(255, 255, 255));

        USUARIOMENU.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        USUARIOMENU.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Categoria:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(USUARIOMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TIPOUSUARIOMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(USUARIOMENU, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TIPOUSUARIOMENU, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jButtonProductos.setBackground(new java.awt.Color(255, 255, 255));
        jButtonProductos.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonProductos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProductos.setText("Productos");
        jButtonProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jButtonProductos.setContentAreaFilled(false);
        jButtonProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonProductos.setDoubleBuffered(true);
        jButtonProductos.setFocusCycleRoot(true);
        jButtonProductos.setFocusTraversalPolicyProvider(true);
        jButtonProductos.setHideActionText(true);
        jButtonProductos.setSelected(true);
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });

        jButtonVentas.setBackground(new java.awt.Color(255, 255, 255));
        jButtonVentas.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonVentas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVentas.setText("Ventas");
        jButtonVentas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jButtonVentas.setContentAreaFilled(false);
        jButtonVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVentas.setDoubleBuffered(true);
        jButtonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasActionPerformed(evt);
            }
        });

        jButtonServicios.setBackground(new java.awt.Color(255, 255, 255));
        jButtonServicios.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonServicios.setForeground(new java.awt.Color(255, 255, 255));
        jButtonServicios.setText("Servicios");
        jButtonServicios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jButtonServicios.setContentAreaFilled(false);
        jButtonServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonServicios.setDoubleBuffered(true);
        jButtonServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonServiciosActionPerformed(evt);
            }
        });

        jButtonClientes.setBackground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonClientes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setText("Clientes");
        jButtonClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jButtonClientes.setContentAreaFilled(false);
        jButtonClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClientes.setDoubleBuffered(true);
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jLabelLogoProductos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelLogoProductosPropertyChange(evt);
            }
        });

        jLabelLogoServicios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelLogoServiciosPropertyChange(evt);
            }
        });

        jLabelLogoVentas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelLogoVentasPropertyChange(evt);
            }
        });

        jLabelLogoClientes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelLogoClientesPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuPrincipalLayout = new javax.swing.GroupLayout(jPanelMenuPrincipal);
        jPanelMenuPrincipal.setLayout(jPanelMenuPrincipalLayout);
        jPanelMenuPrincipalLayout.setHorizontalGroup(
            jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelLogoVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelLogoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLogoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLogoServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonServicios, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabelLogoClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanelMenuPrincipalLayout.setVerticalGroup(
            jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabelLogoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelLogoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonProductos))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelLogoServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonServicios)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelLogoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonClientes))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelLogoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonVentas)))
                        .addGap(50, 50, 50))))
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Acerca de");
        jMenuBar1.add(jMenu3);

        jMenuUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jMenuUsuarios.setText("Usuarios");

        jMenuCerrarSesionUser.setText("Cerrar Sesion");
        jMenuCerrarSesionUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCerrarSesionUserMouseClicked(evt);
            }
        });
        jMenuCerrarSesionUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCerrarSesionUserActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuCerrarSesionUser);

        jMenuBar1.add(jMenuUsuarios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        if (JFMenuPrincipal.JFventas == null) {
            JFVentas ventas = new JFVentas();
            ventas.setVisible(true);
            ventas.USUARIOPRODUCTOS.setText(USUARIOMENU.getText());
            ventas.TIPODEUSUARIOPRODUCTOS.setText(TIPOUSUARIOMENU.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
        if (JFMenuPrincipal.JFprod == null) {
            if (TIPOUSUARIOMENU.getText().equals("Administrador")) {
                JFProductos proF = new JFProductos();
                proF.setVisible(true);
                proF.USUARIOPRODUCTOS.setText(USUARIOMENU.getText());
                proF.TIPODEUSUARIOPRODUCTOS.setText(TIPOUSUARIOMENU.getText());
                this.dispose();
            } else {
                JFProductosUsuario proF = new JFProductosUsuario();
                proF.setVisible(true);
                proF.USUARIOPRODUCTOS.setText(USUARIOMENU.getText());
                proF.TIPODEUSUARIOPRODUCTOS.setText(TIPOUSUARIOMENU.getText());
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButtonProductosActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        if (JFMenuPrincipal.JFclien == null) {
            JFClientes clien = new JFClientes();
            clien.setVisible(true);
            clien.USUARIOPRODUCTOS.setText(USUARIOMENU.getText());
            clien.TIPODEUSUARIOPRODUCTOS.setText(TIPOUSUARIOMENU.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jButtonServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonServiciosActionPerformed
        if (JFMenuPrincipal.JFserv == null) {
            JFServicios serv = new JFServicios();
            serv.setVisible(true);
            serv.USUARIOPRODUCTOS.setText(USUARIOMENU.getText());
            serv.TIPODEUSUARIOPRODUCTOS.setText(TIPOUSUARIOMENU.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonServiciosActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

    private void jPanelMenuPrincipalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelMenuPrincipalPropertyChange
        ImagenMenu imagenMenu = new ImagenMenu();
        jPanelMenuPrincipal.add(imagenMenu);
        jPanelMenuPrincipal.repaint();
    }//GEN-LAST:event_jPanelMenuPrincipalPropertyChange

    private void jLabelLogoProductosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelLogoProductosPropertyChange

    }//GEN-LAST:event_jLabelLogoProductosPropertyChange

    private void jLabelLogoServiciosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelLogoServiciosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoServiciosPropertyChange

    private void jLabelLogoVentasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelLogoVentasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoVentasPropertyChange

    private void jLabelLogoClientesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelLogoClientesPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoClientesPropertyChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFLogin.menu = null;
    }//GEN-LAST:event_formWindowClosing

    private void jMenuCerrarSesionUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCerrarSesionUserActionPerformed

    }//GEN-LAST:event_jMenuCerrarSesionUserActionPerformed

    private void jMenuCerrarSesionUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCerrarSesionUserMouseClicked
        if (JFLogin.log == null) {
            JFLogin log = new JFLogin();
            log.setVisible(true);

            JFLogin.menu = null;
            this.dispose();
        }
    }//GEN-LAST:event_jMenuCerrarSesionUserMouseClicked

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
    public static javax.swing.JLabel TIPOUSUARIOMENU;
    public javax.swing.JLabel USUARIOMENU;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelLogoClientes;
    private javax.swing.JLabel jLabelLogoPrincipal;
    private javax.swing.JLabel jLabelLogoProductos;
    private javax.swing.JLabel jLabelLogoServicios;
    private javax.swing.JLabel jLabelLogoVentas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCerrarSesionUser;
    private javax.swing.JMenu jMenuUsuarios;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
