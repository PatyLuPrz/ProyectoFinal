/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import static Ventanas.JFMenuPrincipal.TIPOUSUARIOMENU;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.SqlClientes;
import modelo.SqlProductos;
import modelo.clientes;
import modelo.hash;
import modelo.productos;

/**
 *
 * @author Norberto
 */
public class JFClientes extends javax.swing.JFrame {

    public void tablaConsulta() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableClientes.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT USERNAME_CL, EMAIL_CL, NOMBRE_CL, AP_CL, AM_CL, TEL_CL, MUN_CL FROM clientes";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Username");
            modelo.addColumn("Email");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido Paterno");
            modelo.addColumn("Apellido Materno");
            modelo.addColumn("Telefono");
            modelo.addColumn("Municipio");
            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {

                    filas[i] = rs.getObject(i + 1);
                }

                modelo.addRow(filas);

            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    }

    public void bloquearCYPC() {
        InputMap map1 = jTextFieldTelefonoC.getInputMap(jTextFieldTelefonoC.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null"); //Bloquea el ctrl + v y ctrl + c

    }

    public JFClientes() {
        initComponents();
        tablaConsulta();
        bloquearCYPC();
        jButtonGuardarC.setEnabled(false);
        jButtonCancelarC.setEnabled(false);

        setIconImage(new ImageIcon(getClass().getResource("../Images/blanco-logo.png")).getImage());

        ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = imagenLogo.getImage().getScaledInstance(jLabelLogoMenu.getWidth(), jLabelLogoMenu.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoMenu.setIcon(iconoEscaladoLogo);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        /*Esta funcion permite centrar el JFrame*/

        jTableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //Se obtiene la seleccion de una lista y se imprime en los jTextField
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {

                    if (jTableClientes.getSelectedRow() != -1) {
                        int fila = jTableClientes.getSelectedRow();

                        jTextFieldUsernameClien.setText(jTableClientes.getValueAt(fila, 0).toString());
                        jTextFieldEmailC.setText(jTableClientes.getValueAt(fila, 1).toString());
                        jTextFieldNombreC.setText(jTableClientes.getValueAt(fila, 2).toString());
                        jTextFieldApellidoPC.setText(jTableClientes.getValueAt(fila, 3).toString());
                        jTextFieldApellidoMC.setText(jTableClientes.getValueAt(fila, 4).toString());
                        jTextFieldTelefonoC.setText(jTableClientes.getValueAt(fila, 5).toString());
                        jTextFieldMunicipioC.setText(jTableClientes.getValueAt(fila, 6).toString());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Error:\nSelecciona un registro");
                }
            }
        });

    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Images/IconoPrograma.png"));
        return retValue;/*Se genero una clase donde se manda llamar un icono para JFrame*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanelMenuSecundario = new javax.swing.JPanel();
        jLabelLogoMenu = new javax.swing.JLabel();
        jButtonServicios = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jButtonVentas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        TIPODEUSUARIOPRODUCTOS = new javax.swing.JLabel();
        USUARIOPRODUCTOS = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelClientes = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelEmailCliente = new javax.swing.JLabel();
        jTextFieldEmailC = new javax.swing.JTextField();
        jTextFieldNombreC = new javax.swing.JTextField();
        jLabelNombreCliente = new javax.swing.JLabel();
        jTextFieldApellidoPC = new javax.swing.JTextField();
        jLabelApellidoPaCliente = new javax.swing.JLabel();
        jTextFieldApellidoMC = new javax.swing.JTextField();
        jLabelApellidoMatCliente = new javax.swing.JLabel();
        jTextFieldMunicipioC = new javax.swing.JTextField();
        jLabelDireccionCliente = new javax.swing.JLabel();
        jLabelTelefonoCliente = new javax.swing.JLabel();
        jTextFieldTelefonoC = new javax.swing.JTextField();
        jLabelEmailCliente1 = new javax.swing.JLabel();
        jTextFieldUsernameClien = new javax.swing.JTextField();
        jLabelEmailCliente2 = new javax.swing.JLabel();
        jPasswordFieldPasswdC = new javax.swing.JPasswordField();
        jPanelTablaClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonCancelarC = new javax.swing.JButton();
        jButtonNuevoC = new javax.swing.JButton();
        jButtonGuardarC = new javax.swing.JButton();
        jButtonActualizarC = new javax.swing.JButton();
        jButtonBorrarC = new javax.swing.JButton();
        jButtonBuscarClientes = new javax.swing.JButton();
        jTextFieldBuscarCliente = new javax.swing.JTextField();
        jComboBoxBuscarCliente = new javax.swing.JComboBox<>();
        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuInicio = new javax.swing.JMenu();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuAcercaDe1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StorePhone Doctor");
        setMaximumSize(new java.awt.Dimension(1121, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelMenuSecundario.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuSecundario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMenuSecundario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelMenuSecundarioPropertyChange(evt);
            }
        });

        jButtonServicios.setBackground(new java.awt.Color(255, 255, 255));
        jButtonServicios.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonServicios.setForeground(new java.awt.Color(255, 255, 255));
        jButtonServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IconoMenuServicios.png"))); // NOI18N
        jButtonServicios.setText(" Servicios");
        jButtonServicios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonServicios.setContentAreaFilled(false);
        jButtonServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonServiciosActionPerformed(evt);
            }
        });

        jButtonClientes.setBackground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonClientes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IconoMenuClientes.png"))); // NOI18N
        jButtonClientes.setText("Clientes");
        jButtonClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonClientes.setContentAreaFilled(false);
        jButtonClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jButtonProductos.setBackground(new java.awt.Color(255, 255, 255));
        jButtonProductos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonProductos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IconoMenuProductos.png"))); // NOI18N
        jButtonProductos.setText("Productos");
        jButtonProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonProductos.setContentAreaFilled(false);
        jButtonProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });

        jButtonVentas.setBackground(new java.awt.Color(255, 255, 255));
        jButtonVentas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonVentas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IconoMenuVentas.png"))); // NOI18N
        jButtonVentas.setText("    Ventas");
        jButtonVentas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVentas.setContentAreaFilled(false);
        jButtonVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setOpaque(false);

        TIPODEUSUARIOPRODUCTOS.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        TIPODEUSUARIOPRODUCTOS.setForeground(new java.awt.Color(255, 255, 255));

        USUARIOPRODUCTOS.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        USUARIOPRODUCTOS.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Categoria:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(USUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TIPODEUSUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(USUARIOPRODUCTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TIPODEUSUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenuSecundarioLayout = new javax.swing.GroupLayout(jPanelMenuSecundario);
        jPanelMenuSecundario.setLayout(jPanelMenuSecundarioLayout);
        jPanelMenuSecundarioLayout.setHorizontalGroup(
            jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanelMenuSecundarioLayout.setVerticalGroup(
            jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSecundarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButtonVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanelClientes.setBackground(new java.awt.Color(0, 0, 0));
        jPanelClientes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelClientesPropertyChange(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);
        jPanel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel2PropertyChange(evt);
            }
        });

        jLabelEmailCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelEmailCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmailCliente.setText("E-mail:");

        jTextFieldEmailC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldEmailC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEmailCKeyTyped(evt);
            }
        });

        jTextFieldNombreC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldNombreC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombreCKeyTyped(evt);
            }
        });

        jLabelNombreCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreCliente.setText("Nombre:");

        jTextFieldApellidoPC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldApellidoPC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldApellidoPCKeyTyped(evt);
            }
        });

        jLabelApellidoPaCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelApellidoPaCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelApellidoPaCliente.setText("Apellido Paterno:");

        jTextFieldApellidoMC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldApellidoMC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldApellidoMCKeyTyped(evt);
            }
        });

        jLabelApellidoMatCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelApellidoMatCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelApellidoMatCliente.setText("Apellido Materno:");

        jTextFieldMunicipioC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldMunicipioC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMunicipioCKeyTyped(evt);
            }
        });

        jLabelDireccionCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDireccionCliente.setText("Municipio:");

        jLabelTelefonoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTelefonoCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTelefonoCliente.setText("Telefono:");

        jTextFieldTelefonoC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldTelefonoC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoCKeyTyped(evt);
            }
        });

        jLabelEmailCliente1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelEmailCliente1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmailCliente1.setText("Username:");

        jTextFieldUsernameClien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldUsernameClien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldUsernameClienKeyTyped(evt);
            }
        });

        jLabelEmailCliente2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelEmailCliente2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmailCliente2.setText("Contraseña:");

        jPasswordFieldPasswdC.setEnabled(false);
        jPasswordFieldPasswdC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordFieldPasswdCKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEmailCliente)
                            .addComponent(jLabelEmailCliente1)
                            .addComponent(jLabelEmailCliente2))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEmailC)
                            .addComponent(jTextFieldUsernameClien)
                            .addComponent(jPasswordFieldPasswdC, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreCliente)
                            .addComponent(jLabelTelefonoCliente)
                            .addComponent(jLabelDireccionCliente)
                            .addComponent(jLabelApellidoMatCliente)
                            .addComponent(jLabelApellidoPaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApellidoPC)
                            .addComponent(jTextFieldTelefonoC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldApellidoMC)
                            .addComponent(jTextFieldMunicipioC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNombreC))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmailCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsernameClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldEmailC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmailCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldPasswdC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreCliente)
                    .addComponent(jTextFieldNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoPaCliente)
                    .addComponent(jTextFieldApellidoPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoMatCliente)
                    .addComponent(jTextFieldApellidoMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTelefonoC)
                    .addComponent(jLabelTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMunicipioC)
                    .addComponent(jLabelDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelTablaClientes.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTablaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Lista de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaClientes.setOpaque(false);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "E-mail", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "Municipio"
            }
        ));
        jScrollPane1.setViewportView(jTableClientes);

        javax.swing.GroupLayout jPanelTablaClientesLayout = new javax.swing.GroupLayout(jPanelTablaClientes);
        jPanelTablaClientes.setLayout(jPanelTablaClientesLayout);
        jPanelTablaClientesLayout.setHorizontalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelTablaClientesLayout.setVerticalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Edicion Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jButtonCancelarC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonCancelarC.setText("Cancelar");
        jButtonCancelarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarCActionPerformed(evt);
            }
        });

        jButtonNuevoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonNuevoC.setText("Nuevo");
        jButtonNuevoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoCActionPerformed(evt);
            }
        });

        jButtonGuardarC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonGuardarC.setText("Guardar");
        jButtonGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarCActionPerformed(evt);
            }
        });

        jButtonActualizarC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonActualizarC.setText("Actualizar");
        jButtonActualizarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarCActionPerformed(evt);
            }
        });

        jButtonBorrarC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBorrarC.setText("Borrar");
        jButtonBorrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevoC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGuardarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActualizarC, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jButtonBorrarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jButtonNuevoC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonActualizarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBorrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButtonBuscarClientes.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarClientes.setText("Buscar");
        jButtonBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClientesActionPerformed(evt);
            }
        });

        jComboBoxBuscarCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Username", "Email", "Nombre", "Telefono", "Municipio" }));

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarClientes))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuBarPrincipal.setBackground(new java.awt.Color(255, 0, 0));
        jMenuBarPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuArchivo.setBackground(new java.awt.Color(255, 255, 255));
        jMenuArchivo.setText("Archivo");
        jMenuBarPrincipal.add(jMenuArchivo);

        jMenuInicio.setText("Menu Principal");
        jMenuInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuInicioMouseClicked(evt);
            }
        });
        jMenuBarPrincipal.add(jMenuInicio);

        jMenuAyuda.setBackground(new java.awt.Color(255, 255, 255));
        jMenuAyuda.setText("Ayuda");
        jMenuBarPrincipal.add(jMenuAyuda);

        jMenuAcercaDe1.setText("Acerca de");
        jMenuBarPrincipal.add(jMenuAcercaDe1);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelClientesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelClientesPropertyChange
        Imagen Imagen = new Imagen();
        jPanelClientes.add(Imagen);
        jPanelClientes.repaint();
    }//GEN-LAST:event_jPanelClientesPropertyChange

    private void jPanelMenuSecundarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelMenuSecundarioPropertyChange
        ImagenMenuSecundario ImagenMenuSec = new ImagenMenuSecundario();
        jPanelMenuSecundario.add(ImagenMenuSec);
        jPanelMenuSecundario.repaint();
    }//GEN-LAST:event_jPanelMenuSecundarioPropertyChange

    private void jPanel2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel2PropertyChange

    }//GEN-LAST:event_jPanel2PropertyChange

    private void jButtonServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonServiciosActionPerformed
        if (JFMenuPrincipal.JFserv == null) {
            JFServicios serv = new JFServicios();
            serv.setVisible(true);
            serv.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
            serv.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonServiciosActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed

    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
        if (JFMenuPrincipal.JFprod == null) {
            if (TIPOUSUARIOMENU.getText().equals("Administrador")) {
                JFProductos proF = new JFProductos();
                proF.setVisible(true);
                proF.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
                proF.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
                this.setVisible(false);
                this.dispose();
            } else {
                JFProductosUsuario proF = new JFProductosUsuario();
                proF.setVisible(true);
                proF.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
                proF.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
                this.setVisible(false);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButtonProductosActionPerformed

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        if (JFMenuPrincipal.JFventas == null) {
            JFVentas ven = new JFVentas();
            ven.setVisible(true);
            ven.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
            ven.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFMenuPrincipal.JFclien = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarCActionPerformed

        int cancelar = JOptionPane.showConfirmDialog(null, "¿Vas a guardar un nuevo cliente?", "Guardar cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            SqlClientes modSql = new SqlClientes(); //Se creaun nuevo objeto de la clase SqlUsuarios llamadp modSql
            clientes mod = new clientes(); //Se crea un nuevo objeto de la clase usuarios llamado mod
            String pass = new String(jTextFieldUsernameClien.getText());
            String nuevoPass = hash.sha1(pass);

            try {
                if (jTextFieldUsernameClien.getText().equals("") || jTextFieldEmailC.getText().equals("") || jPasswordFieldPasswdC.getText().equals("") || jTextFieldNombreC.getText().equals("") || jTextFieldApellidoPC.getText().equals("") || jTextFieldApellidoMC.getText().equals("") || jTextFieldTelefonoC.getText().equals("") || jTextFieldMunicipioC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos\n¡No se permiten campos vacios!");
                } else {

                    if (modSql.existeCliente(jTextFieldUsernameClien.getText()) == 0) {
                        if (modSql.esEmail(jTextFieldEmailC.getText())) {

                            mod.setUsername_Clien(jTextFieldUsernameClien.getText());
                            mod.setEmail_Clien(jTextFieldEmailC.getText());
                            mod.setContrasena_Clien(nuevoPass);
                            mod.setNombre_Clien(jTextFieldNombreC.getText());
                            mod.setAp_Clien(jTextFieldApellidoPC.getText());
                            mod.setAm_Clien(jTextFieldApellidoMC.getText());
                            mod.setTel_Clien(jTextFieldTelefonoC.getText());
                            mod.setMun_Clien(jTextFieldMunicipioC.getText());

                            limpiar();

                            if (modSql.registrarClientes(mod)) {
                                JOptionPane.showMessageDialog(null, "El registro fue guardado");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al guardar");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "El correo no es valido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    }

                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar\n" + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se guardo ningun producto");
        }


    }//GEN-LAST:event_jButtonGuardarCActionPerformed

    private void jButtonBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClientesActionPerformed
        if (!jComboBoxBuscarCliente.getSelectedItem().equals("Selecciona")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                jTableClientes.setModel(modelo);
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String buscarCliente = this.jTextFieldBuscarCliente.getText();
                String tipobusq = null;

                int tipo = jComboBoxBuscarCliente.getSelectedIndex();

                switch (tipo) {//Se encarga de evaluar el combobox
                    case 1:
                        tipobusq = "USERNAME_CL";
                        break;
                    case 2:
                        tipobusq = "EMAIL_CL";
                        break;
                    case 3:
                        tipobusq = "NOMBRE_CL";
                        break;
                    case 4:
                        tipobusq = "TEL_CL";
                        break;
                    case 5:
                        tipobusq = "MUN_CL";
                        break;
                }

                String sql = "SELECT  USERNAME_CL, EMAIL_CL, NOMBRE_CL, AP_CL, AM_CL, TEL_CL, MUN_CL  FROM clientes WHERE " + tipobusq + " LIKE '%" + buscarCliente + "%'";
                System.out.println(rs);

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Username");
                modelo.addColumn("Email");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apellido Paterno");
                modelo.addColumn("Apellido Materno");
                modelo.addColumn("Telefono");
                modelo.addColumn("Municipio");

                while (rs.next()) {

                    Object[] filas = new Object[cantidadColumnas];

                    for (int i = 0; i < cantidadColumnas; i++) {

                        filas[i] = rs.getObject(i + 1);
                    }

                    modelo.addRow(filas);

                }
            } catch (SQLException ex) {
                System.out.println("Error" + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona el tipo de busqueda");
        }


    }//GEN-LAST:event_jButtonBuscarClientesActionPerformed

    private void jButtonNuevoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoCActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Quiere agregar un nuevo cliente frecuente'", "Cancelar registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            jButtonBuscarClientes.setEnabled(false);
            jTextFieldBuscarCliente.setEnabled(false);
            jComboBoxBuscarCliente.setEnabled(false);
            jButtonGuardarC.setEnabled(true);
            jButtonCancelarC.setEnabled(true);
            jButtonActualizarC.setEnabled(false);
            jButtonBorrarC.setEnabled(false);
            limpiar();
        } else {
        }
    }//GEN-LAST:event_jButtonNuevoCActionPerformed

    private void jButtonCancelarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarCActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Ya no quieres guardar el producto?", "Cancelar Ingreso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            jButtonActualizarC.setEnabled(true);
            jButtonBorrarC.setEnabled(true);
            jButtonCancelarC.setEnabled(false);
            jButtonGuardarC.setEnabled(false);
            jButtonBuscarClientes.setEnabled(true);
            jTextFieldBuscarCliente.setEnabled(true);
            jComboBoxBuscarCliente.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, "");
        }
    }//GEN-LAST:event_jButtonCancelarCActionPerformed

    private void jButtonActualizarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarCActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el cliente?", "Confirmar actualizacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            SqlClientes sqlClien = new SqlClientes();
            clientes clien = new clientes();
            try {
                if (jTextFieldUsernameClien.getText().equals("") || jTextFieldEmailC.getText().equals("") || jPasswordFieldPasswdC.getText().equals("") || jTextFieldNombreC.getText().equals("") || jTextFieldApellidoPC.getText().equals("") || jTextFieldApellidoMC.getText().equals("") || jTextFieldTelefonoC.getText().equals("") || jTextFieldMunicipioC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos\n¡No se permiten campos vacios!");
                } else {

                    if (sqlClien.existeCliente(jTextFieldUsernameClien.getText()) == 0) {
                        if (sqlClien.esEmail(jTextFieldEmailC.getText())) {

                            clien.setUsername_Clien(jTextFieldUsernameClien.getText());
                            clien.setEmail_Clien(jTextFieldEmailC.getText());
                            clien.setNombre_Clien(jTextFieldNombreC.getText());
                            clien.setAp_Clien(jTextFieldApellidoPC.getText());
                            clien.setAm_Clien(jTextFieldApellidoMC.getText());
                            clien.setTel_Clien(jTextFieldTelefonoC.getText());
                            clien.setMun_Clien(jTextFieldMunicipioC.getText());
                            if (sqlClien.actualizarClien(clien)) {
                                tablaConsulta();
                                JOptionPane.showMessageDialog(null, "El cliente fue actualizado");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al actualizar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Este correo ya esta registrado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El cliente ya existe en el registro");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se actualizo la informacion de ningun cliente");
        }
    }//GEN-LAST:event_jButtonActualizarCActionPerformed

    private void jButtonBorrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarCActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Borrar Cliente?", "Eliminar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            SqlClientes sqlClien = new SqlClientes();
            clientes clien = new clientes();
            try {

                clien.setUsername_Clien(jTextFieldUsernameClien.getText());

                if (sqlClien.borrarClien(clien)) {
                    tablaConsulta();
                    JOptionPane.showMessageDialog(null, "El cliente fue borrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al borrar");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No puedes borrar este cliente");
            }
        }
    }//GEN-LAST:event_jButtonBorrarCActionPerformed

    private void jMenuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuInicioMouseClicked
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
        menu.USUARIOMENU.setText(USUARIOPRODUCTOS.getText());
        menu.TIPOUSUARIOMENU.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuInicioMouseClicked

    private void jTextFieldUsernameClienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsernameClienKeyTyped
        String Caracteres = jTextFieldUsernameClien.getText();
        jPasswordFieldPasswdC.setText(jTextFieldUsernameClien.getText());
        if (Caracteres.length() > 19) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldUsernameClienKeyTyped

    private void jTextFieldEmailCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailCKeyTyped
        String Caracteres = jTextFieldEmailC.getText();
        if (Caracteres.length() > 49) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldEmailCKeyTyped

    private void jPasswordFieldPasswdCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswdCKeyTyped
        String Caracteres = jPasswordFieldPasswdC.getText();
        if (Caracteres.length() > 30) {
            evt.consume();
        }
    }//GEN-LAST:event_jPasswordFieldPasswdCKeyTyped

    private void jTextFieldNombreCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreCKeyTyped
        String Caracteres = jTextFieldNombreC.getText();
        if (Caracteres.length() > 29) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNombreCKeyTyped

    private void jTextFieldApellidoPCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldApellidoPCKeyTyped
        String Caracteres = jTextFieldApellidoPC.getText();
        if (Caracteres.length() > 29) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldApellidoPCKeyTyped

    private void jTextFieldApellidoMCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldApellidoMCKeyTyped
        String Caracteres = jTextFieldNombreC.getText();
        if (Caracteres.length() > 29) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldApellidoMCKeyTyped

    private void jTextFieldTelefonoCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoCKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        String Caracteres = jTextFieldTelefonoC.getText();
        if (Caracteres.length() > 19) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoCKeyTyped

    private void jTextFieldMunicipioCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMunicipioCKeyTyped
        String Caracteres = jTextFieldMunicipioC.getText();
        if (Caracteres.length() > 19) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldMunicipioCKeyTyped

    private void limpiar() {
        jTextFieldUsernameClien.setText("");
        jTextFieldEmailC.setText("");
        jPasswordFieldPasswdC.setText("");
        jTextFieldNombreC.setText("");
        jTextFieldApellidoMC.setText("");
        jTextFieldApellidoPC.setText("");
        jTextFieldTelefonoC.setText("");
        jTextFieldMunicipioC.setText("");
    }

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
            java.util.logging.Logger.getLogger(JFClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TIPODEUSUARIOPRODUCTOS;
    public javax.swing.JLabel USUARIOPRODUCTOS;
    private javax.swing.JButton jButtonActualizarC;
    private javax.swing.JButton jButtonBorrarC;
    private javax.swing.JButton jButtonBuscarClientes;
    private javax.swing.JButton jButtonCancelarC;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonGuardarC;
    private javax.swing.JButton jButtonNuevoC;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JComboBox<String> jComboBoxBuscarCliente;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelApellidoMatCliente;
    private javax.swing.JLabel jLabelApellidoPaCliente;
    private javax.swing.JLabel jLabelDireccionCliente;
    private javax.swing.JLabel jLabelEmailCliente;
    private javax.swing.JLabel jLabelEmailCliente1;
    private javax.swing.JLabel jLabelEmailCliente2;
    private javax.swing.JLabel jLabelLogoMenu;
    private javax.swing.JLabel jLabelNombreCliente;
    private javax.swing.JLabel jLabelTelefonoCliente;
    private javax.swing.JMenu jMenuAcercaDe1;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelMenuSecundario;
    private javax.swing.JPanel jPanelTablaClientes;
    private javax.swing.JPasswordField jPasswordFieldPasswdC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldApellidoMC;
    private javax.swing.JTextField jTextFieldApellidoPC;
    private javax.swing.JTextField jTextFieldBuscarCliente;
    private javax.swing.JTextField jTextFieldEmailC;
    private javax.swing.JTextField jTextFieldMunicipioC;
    private javax.swing.JTextField jTextFieldNombreC;
    private javax.swing.JTextField jTextFieldTelefonoC;
    private javax.swing.JTextField jTextFieldUsernameClien;
    // End of variables declaration//GEN-END:variables

    private void setBorder(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFocusPainted(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
