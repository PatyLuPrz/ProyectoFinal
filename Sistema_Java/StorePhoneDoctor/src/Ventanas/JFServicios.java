/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import static Ventanas.JFMenuPrincipal.TIPOUSUARIOMENU;
import java.awt.Toolkit;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.FechaConsulta;
import modelo.servicios;
import modelo.sqlServicios;

/**
 *
 * @author Norberto
 */
public class JFServicios extends javax.swing.JFrame {

    public void tablaConsulta() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableClientes.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            /*FOLIO_S, USERNAME_US, USERNAME_CL, FECHALLEGADA_S, FECHASALIDA_S, NOMBRECLIENTE_S, TELEFONOCLIENTE_S, DESCRIPCION_S*/
            String sql = "SELECT FOLIO_S, USERNAME_US, USERNAME_CL, FECHALLEGADA_S, FECHASALIDA_S, NOMBRECLIENTE_S, TELEFONOCLIENTE_S, DESCRIPCION_S, PRECIO_S FROM servicios";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Folio");
            modelo.addColumn("Usuario");
            modelo.addColumn("Cliente");
            modelo.addColumn("Llegada");
            modelo.addColumn("Salida");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Telefono");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Precio");
            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {

                    filas[i] = rs.getObject(i + 1);
                }

                modelo.addRow(filas);
                System.out.println(filas);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }
    }

    public JFServicios() {
        initComponents();

        tablaConsulta();

        jButtonGuardarS.setEnabled(false);
        jButtonCancelarS.setEnabled(false);

        ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = imagenLogo.getImage().getScaledInstance(jLabelLogoMenu.getWidth(), jLabelLogoMenu.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoMenu.setIcon(iconoEscaladoLogo);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        /*Esta funcion permite centrar el JFrame*/

        //Imprimir en los textField el elemento Seleccionado de la lista
        jTableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //Se obtiene la seleccion de una lista y se imprime en los jTextField
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {

                    if (jTableClientes.getSelectedRow() != -1) {
                        int fila = jTableClientes.getSelectedRow();

                        jTextFieldFolioS.setText(jTableClientes.getValueAt(fila, 0).toString());
                        jTextFieldUsernameUs.setText(jTableClientes.getValueAt(fila, 1).toString());
                        jTextFieldUsernameClS.setText(jTableClientes.getValueAt(fila, 2).toString());
                        jTextFieldFechaEntradaS.setText(jTableClientes.getValueAt(fila, 3).toString());

                        jTextFieldNombreClienteS.setText(jTableClientes.getValueAt(fila, 5).toString());
                        jTextFieldTelefonoClienteS.setText(jTableClientes.getValueAt(fila, 6).toString());
                        jTextAreaDescripcionS.setText(jTableClientes.getValueAt(fila, 7).toString());
                        jTextFieldPrecioS.setText(jTableClientes.getValueAt(fila, 8).toString());

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
        jButtonClientes = new javax.swing.JButton();
        jButtonServicios = new javax.swing.JButton();
        jButtonVentas = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        TIPODEUSUARIOPRODUCTOS = new javax.swing.JLabel();
        USUARIOPRODUCTOS = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelServicios = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelFolioS = new javax.swing.JLabel();
        jTextFieldFolioS = new javax.swing.JTextField();
        jTextFieldUsernameUs = new javax.swing.JTextField();
        jLabelNombreUsuarioS = new javax.swing.JLabel();
        jTextFieldUsernameClS = new javax.swing.JTextField();
        jLabelEmailClienteS = new javax.swing.JLabel();
        jTextFieldNombreClienteS = new javax.swing.JTextField();
        jLabelNombreClienteS = new javax.swing.JLabel();
        jLabelFechaSalidaS = new javax.swing.JLabel();
        jTextFieldFechaSalidaS = new javax.swing.JTextField();
        jLabelTelefonoCliente = new javax.swing.JLabel();
        jTextFieldTelefonoClienteS = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescripcionS = new javax.swing.JTextArea();
        jLabelTelefonoCliente1 = new javax.swing.JLabel();
        jLabelPrecioS = new javax.swing.JLabel();
        jTextFieldPrecioS = new javax.swing.JTextField();
        jLabelFechaSalidaS1 = new javax.swing.JLabel();
        jTextFieldFechaEntradaS = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButtonCancelarS = new javax.swing.JButton();
        jButtonNuevoS = new javax.swing.JButton();
        jButtonGuardarS = new javax.swing.JButton();
        jButtonActualizarS = new javax.swing.JButton();
        jButtonBorrarS = new javax.swing.JButton();
        jPanelTablaClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonBuscarServicio = new javax.swing.JButton();
        jComboBoxBuscarServicio = new javax.swing.JComboBox<>();
        jTextFieldBuscarCliente = new javax.swing.JTextField();
        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuInicio = new javax.swing.JMenu();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuAcercaDe = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StorePhone Doctor");
        setIconImage(getIconImage());
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
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonServicios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelServicios.setBackground(new java.awt.Color(0, 0, 0));
        jPanelServicios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelServiciosPropertyChange(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Datos del Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);
        jPanel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel2PropertyChange(evt);
            }
        });

        jLabelFolioS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFolioS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFolioS.setText("Folio:");

        jTextFieldFolioS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTextFieldUsernameUs.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelNombreUsuarioS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreUsuarioS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreUsuarioS.setText("Username Usuario:");

        jTextFieldUsernameClS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelEmailClienteS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelEmailClienteS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmailClienteS.setText("Username Cliente:");

        jTextFieldNombreClienteS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldNombreClienteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreClienteSActionPerformed(evt);
            }
        });

        jLabelNombreClienteS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreClienteS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreClienteS.setText("Nombre Cliente:");

        jLabelFechaSalidaS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFechaSalidaS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaSalidaS.setText("Fecha de Salida:");

        jTextFieldFechaSalidaS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelTelefonoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTelefonoCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTelefonoCliente.setText("Telefono Cliente:");

        jTextFieldTelefonoClienteS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldTelefonoClienteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelefonoClienteSActionPerformed(evt);
            }
        });

        jTextAreaDescripcionS.setColumns(20);
        jTextAreaDescripcionS.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescripcionS);

        jLabelTelefonoCliente1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTelefonoCliente1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTelefonoCliente1.setText("Descripción:");

        jLabelPrecioS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelPrecioS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPrecioS.setText("Precio:");

        jTextFieldPrecioS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldPrecioS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioSActionPerformed(evt);
            }
        });

        jLabelFechaSalidaS1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFechaSalidaS1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaSalidaS1.setText("Fecha de entrada:");

        jTextFieldFechaEntradaS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldFechaEntradaS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldFechaEntradaSMouseClicked(evt);
            }
        });
        jTextFieldFechaEntradaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaEntradaSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreUsuarioS)
                            .addComponent(jLabelEmailClienteS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsernameUs)
                            .addComponent(jTextFieldUsernameClS)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelFolioS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jTextFieldFolioS, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTelefonoCliente)
                            .addComponent(jLabelTelefonoCliente1)
                            .addComponent(jLabelNombreClienteS)
                            .addComponent(jLabelPrecioS))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPrecioS, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldTelefonoClienteS, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNombreClienteS)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFechaSalidaS)
                            .addComponent(jLabelFechaSalidaS1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFechaEntradaS)
                            .addComponent(jTextFieldFechaSalidaS))))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFolioS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFolioS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreUsuarioS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsernameUs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsernameClS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmailClienteS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaSalidaS1)
                    .addComponent(jTextFieldFechaEntradaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaSalidaS)
                    .addComponent(jTextFieldFechaSalidaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombreClienteS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreClienteS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoCliente)
                    .addComponent(jTextFieldTelefonoClienteS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecioS)
                    .addComponent(jTextFieldPrecioS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTelefonoCliente1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Edicion Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jButtonCancelarS.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonCancelarS.setText("Cancelar");
        jButtonCancelarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarSActionPerformed(evt);
            }
        });

        jButtonNuevoS.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonNuevoS.setText("Nuevo");
        jButtonNuevoS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoSActionPerformed(evt);
            }
        });

        jButtonGuardarS.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonGuardarS.setText("Guardar");
        jButtonGuardarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarSActionPerformed(evt);
            }
        });

        jButtonActualizarS.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonActualizarS.setText("Actualizar");
        jButtonActualizarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarSActionPerformed(evt);
            }
        });

        jButtonBorrarS.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBorrarS.setText("Borrar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevoS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGuardarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActualizarS, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jButtonBorrarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNuevoS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardarS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonActualizarS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBorrarS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelTablaClientes.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTablaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Lista de Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaClientes.setOpaque(false);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "E-mail", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "Municipio"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonBuscarServicio.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarServicio.setText("Buscar");

        jComboBoxBuscarServicio.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jComboBoxBuscarServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Email", "Nombre", "Telefono", "Municipio", "Descripcion", " " }));

        javax.swing.GroupLayout jPanelServiciosLayout = new javax.swing.GroupLayout(jPanelServicios);
        jPanelServicios.setLayout(jPanelServiciosLayout);
        jPanelServiciosLayout.setHorizontalGroup(
            jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelServiciosLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxBuscarServicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarServicio)))
                    .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelServiciosLayout.setVerticalGroup(
            jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiciosLayout.createSequentialGroup()
                .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelServiciosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelServiciosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButtonBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jMenuAcercaDe.setText("Acerca de");
        jMenuBarPrincipal.add(jMenuAcercaDe);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        if (JFMenuPrincipal.JFclien == null) {
            JFClientes clien = new JFClientes();
            clien.setVisible(true);
            clien.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
            clien.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jButtonServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonServiciosActionPerformed

    }//GEN-LAST:event_jButtonServiciosActionPerformed

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        JFVentas ventas = new JFVentas();
        ventas.setVisible(true);
        ventas.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
        ventas.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
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
    }//GEN-LAST:event_jButtonProductosActionPerformed

    private void jPanelMenuSecundarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelMenuSecundarioPropertyChange
        ImagenMenuSecundario ImagenMenuSec = new ImagenMenuSecundario();
        jPanelMenuSecundario.add(ImagenMenuSec);
        jPanelMenuSecundario.repaint();
    }//GEN-LAST:event_jPanelMenuSecundarioPropertyChange

    private void jPanelServiciosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelServiciosPropertyChange
        Imagen Imagen = new Imagen();
        jPanelServicios.add(Imagen);
        jPanelServicios.repaint();
    }//GEN-LAST:event_jPanelServiciosPropertyChange

    private void jPanel2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel2PropertyChange

    }//GEN-LAST:event_jPanel2PropertyChange

    private void jTextFieldNombreClienteSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreClienteSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreClienteSActionPerformed

    private void jTextFieldTelefonoClienteSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoClienteSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTelefonoClienteSActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFMenuPrincipal.JFserv = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonNuevoSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoSActionPerformed
        jButtonGuardarS.setEnabled(true);
        jButtonCancelarS.setEnabled(true);
        jButtonBorrarS.setEnabled(false);
        jButtonActualizarS.setEnabled(false);
    }//GEN-LAST:event_jButtonNuevoSActionPerformed

    private void jButtonCancelarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarSActionPerformed
        jButtonGuardarS.setEnabled(false);
        jButtonCancelarS.setEnabled(false);
        jButtonBorrarS.setEnabled(true);
        jButtonActualizarS.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarSActionPerformed

    private void jButtonGuardarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarSActionPerformed

        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea guardar este producto?", "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            sqlServicios sqlServ = new sqlServicios();
            servicios servi = new servicios();

            try {

                servi.setFolio_serv(jTextFieldFolioS.getText());
                servi.setUsername_serv_users(jTextFieldUsernameUs.getText());
                servi.setUsername_serv_clien(jTextFieldUsernameClS.getText());
                servi.setFecha_llegada_serv(jTextFieldFechaEntradaS.getText());
                servi.setNombre_cliente_serv(jTextFieldNombreClienteS.getText());
                servi.setDescripcion_serv(jTextAreaDescripcionS.getText());
                servi.setPrecio_serv(Double.parseDouble(jTextFieldPrecioS.getText()));
                if (sqlServ.registrarServicio(servi)) {
                    tablaConsulta();
                    JOptionPane.showMessageDialog(null, "El registro fue guardado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se guardo ningun producto");
        }
    }//GEN-LAST:event_jButtonGuardarSActionPerformed

    private void jButtonActualizarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarSActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el servicio?", "Modificar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            sqlServicios sqlServ = new sqlServicios();
            servicios servi = new servicios();
            FechaConsulta fecha = new FechaConsulta();
            jTextFieldFechaEntradaS.setText(fecha.fechaactual());
            try {

                servi.setFolio_serv(jTextFieldFolioS.getText());
                servi.setUsername_serv_users(jTextFieldUsernameUs.getText());
                servi.setUsername_serv_clien(jTextFieldUsernameClS.getText());
                servi.setTelefono_cliente_serv(jTextFieldFechaSalidaS.getText());
                servi.setNombre_cliente_serv(jTextFieldNombreClienteS.getText());
                servi.setTelefono_cliente_serv(jTextFieldTelefonoClienteS.getText());
                servi.setDescripcion_serv(jTextAreaDescripcionS.getText());
                servi.setPrecio_serv(Double.parseDouble(jTextFieldPrecioS.getText()));
                if (sqlServ.actualizarServicio(servi)) {
                    tablaConsulta();
                    JOptionPane.showMessageDialog(null, "El servicio fue actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se actualizo ningun producto");
        }
    }//GEN-LAST:event_jButtonActualizarSActionPerformed

    private void jTextFieldPrecioSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioSActionPerformed

    private void jTextFieldFechaEntradaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaEntradaSActionPerformed

    }//GEN-LAST:event_jTextFieldFechaEntradaSActionPerformed

    private void jTextFieldFechaEntradaSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldFechaEntradaSMouseClicked

    }//GEN-LAST:event_jTextFieldFechaEntradaSMouseClicked

    private void jMenuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuInicioMouseClicked
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
        menu.USUARIOMENU.setText(USUARIOPRODUCTOS.getText());
        menu.TIPOUSUARIOMENU.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuInicioMouseClicked

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
            java.util.logging.Logger.getLogger(JFServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFServicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TIPODEUSUARIOPRODUCTOS;
    public javax.swing.JLabel USUARIOPRODUCTOS;
    private javax.swing.JButton jButtonActualizarS;
    private javax.swing.JButton jButtonBorrarS;
    private javax.swing.JButton jButtonBuscarServicio;
    private javax.swing.JButton jButtonCancelarS;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonGuardarS;
    private javax.swing.JButton jButtonNuevoS;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JComboBox<String> jComboBoxBuscarServicio;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelEmailClienteS;
    private javax.swing.JLabel jLabelFechaSalidaS;
    private javax.swing.JLabel jLabelFechaSalidaS1;
    private javax.swing.JLabel jLabelFolioS;
    private javax.swing.JLabel jLabelLogoMenu;
    private javax.swing.JLabel jLabelNombreClienteS;
    private javax.swing.JLabel jLabelNombreUsuarioS;
    private javax.swing.JLabel jLabelPrecioS;
    private javax.swing.JLabel jLabelTelefonoCliente;
    private javax.swing.JLabel jLabelTelefonoCliente1;
    private javax.swing.JMenu jMenuAcercaDe;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelMenuSecundario;
    private javax.swing.JPanel jPanelServicios;
    private javax.swing.JPanel jPanelTablaClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextArea jTextAreaDescripcionS;
    private javax.swing.JTextField jTextFieldBuscarCliente;
    private javax.swing.JTextField jTextFieldFechaEntradaS;
    private javax.swing.JTextField jTextFieldFechaSalidaS;
    private javax.swing.JTextField jTextFieldFolioS;
    private javax.swing.JTextField jTextFieldNombreClienteS;
    private javax.swing.JTextField jTextFieldPrecioS;
    private javax.swing.JTextField jTextFieldTelefonoClienteS;
    private javax.swing.JTextField jTextFieldUsernameClS;
    private javax.swing.JTextField jTextFieldUsernameUs;
    // End of variables declaration//GEN-END:variables

    private void setBorder(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFocusPainted(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
