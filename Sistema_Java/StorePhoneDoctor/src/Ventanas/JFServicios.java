/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import static Ventanas.JFMenuPrincipal.TIPOUSUARIOMENU;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.FechaConsulta;
import modelo.GenerarCodigos;
import modelo.servicios;
import modelo.sqlServicios;

/**
 *
 * @author Norberto
 */
public class JFServicios extends javax.swing.JFrame {

    modelo.FechaConsulta fecha = new modelo.FechaConsulta();

    public void tablaConsulta() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableServicios.setModel(modelo);
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
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }
    }

    public void limpiarCampos() {
        jTextFieldFolioS.setText("");
        jTextFieldUsernameUs.setText(USUARIOPRODUCTOS.getText());
        jTextFieldUsernameClS.setText("Publico General");
        jTextFieldFechaEntradaS.setText(fecha.fechaactual());
        jTextFieldFechaSalidaS.setText("En proceso");
        jTextFieldNombreClienteS.setText("");
        jTextFieldTelefonoClienteS.setText("");
        jTextFieldPrecioS.setText("");
        jTextAreaDescripcionS.setText("");
    }

    public JFServicios() {
        initComponents();

        tablaConsulta();

        //Fecha para BD
        modelo.FechaConsulta fecha = new modelo.FechaConsulta();
        jTextFieldFechaEntradaS.setText(fecha.fechaactual());

        //Desactivar componentes 
        for (Component component : jPanelTablaClientes1.getComponents()) {
            jTableClientes.setEnabled(false);
            component.setEnabled(false);
        }

        jButtonFinalizarServicio.setEnabled(false);
        jButtonGuardarS.setEnabled(false);
        jButtonCancelarS.setEnabled(false);

        setIconImage(new ImageIcon(getClass().getResource("../Images/blanco-logo.png")).getImage());

        ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = imagenLogo.getImage().getScaledInstance(jLabelLogoMenu.getWidth(), jLabelLogoMenu.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoMenu.setIcon(iconoEscaladoLogo);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        /*Esta funcion permite centrar el JFrame*/

        //Imprimir en los textField el elemento Seleccionado de la lista
        jTableServicios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //Se obtiene la seleccion de una lista y se imprime en los jTextField
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {

                    if (jTableServicios.getSelectedRow() != -1) {
                        int fila = jTableServicios.getSelectedRow();

                        jTextFieldFolioS.setText(jTableServicios.getValueAt(fila, 0).toString());
                        jTextFieldUsernameUs.setText(jTableServicios.getValueAt(fila, 1).toString());
                        jTextFieldUsernameClS.setText(jTableServicios.getValueAt(fila, 2).toString());
                        jTextFieldFechaEntradaS.setText(jTableServicios.getValueAt(fila, 3).toString());
                        jTextFieldFechaSalidaS.setText(jTableServicios.getValueAt(fila, 4).toString());
                        jTextFieldNombreClienteS.setText(jTableServicios.getValueAt(fila, 5).toString());
                        jTextFieldTelefonoClienteS.setText(jTableServicios.getValueAt(fila, 6).toString());
                        jTextAreaDescripcionS.setText(jTableServicios.getValueAt(fila, 7).toString());
                        jTextFieldPrecioS.setText(jTableServicios.getValueAt(fila, 8).toString());

                        if (!jTableServicios.getValueAt(fila, 4).toString().equals("En proceso")) {
                            jButtonFinalizarServicio.setEnabled(false);
                        } else {
                            jButtonFinalizarServicio.setEnabled(true);
                        }

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

    void codigosServicios() {

        int j;
        int cont = 1;
        String num = "";
        String c = "";

        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID"; 
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        String SQL = "SELECT MAX(FOLIO_V) FROM ventas";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                jTextFieldFolioS.setText("SSP0000000001");
            } else {
                char r1 = c.charAt(3);
                char r2 = c.charAt(4);
                char r3 = c.charAt(5);
                char r4 = c.charAt(6);
                char r5 = c.charAt(7);
                char r6 = c.charAt(8);
                char r7 = c.charAt(9);
                char r8 = c.charAt(10);
                char r9 = c.charAt(11);
                char r10 = c.charAt(12);
                String r = "";
                r = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10;
                j = Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                gen.generar(j);
                jTextFieldFolioS.setText("SSP" + gen.serie());

            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
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
        jPanelEdicionServicios = new javax.swing.JPanel();
        jButtonCancelarS = new javax.swing.JButton();
        jButtonNuevoS = new javax.swing.JButton();
        jButtonGuardarS = new javax.swing.JButton();
        jButtonActualizarS = new javax.swing.JButton();
        jButtonBorrarS = new javax.swing.JButton();
        jPanelTablaClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicios = new javax.swing.JTable();
        jButtonBuscarServicio = new javax.swing.JButton();
        jComboBoxBuscarServicio = new javax.swing.JComboBox<>();
        jTextFieldBuscarServicio = new javax.swing.JTextField();
        jButtonFinalizarServicio = new javax.swing.JButton();
        jPanelTablaClientes1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonBuscarClienteV = new javax.swing.JButton();
        jComboBoxTipoClienteV = new javax.swing.JComboBox<>();
        jTextFieldBuscarClienteV = new javax.swing.JTextField();
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
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
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
        jTextFieldFolioS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFolioSKeyTyped(evt);
            }
        });

        jTextFieldUsernameUs.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldUsernameUs.setEnabled(false);

        jLabelNombreUsuarioS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreUsuarioS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreUsuarioS.setText("Usuario:");

        jTextFieldUsernameClS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldUsernameClS.setEnabled(false);

        jLabelEmailClienteS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelEmailClienteS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmailClienteS.setText("Cliente:");

        jTextFieldNombreClienteS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldNombreClienteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreClienteSActionPerformed(evt);
            }
        });
        jTextFieldNombreClienteS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombreClienteSKeyTyped(evt);
            }
        });

        jLabelNombreClienteS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreClienteS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreClienteS.setText("Nombre Cliente:");

        jLabelFechaSalidaS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFechaSalidaS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaSalidaS.setText("Fecha de Salida:");

        jTextFieldFechaSalidaS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldFechaSalidaS.setText("En proceso");
        jTextFieldFechaSalidaS.setEnabled(false);

        jLabelTelefonoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTelefonoCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTelefonoCliente.setText("Telefono Cliente:");

        jTextFieldTelefonoClienteS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldTelefonoClienteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelefonoClienteSActionPerformed(evt);
            }
        });
        jTextFieldTelefonoClienteS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoClienteSKeyTyped(evt);
            }
        });

        jTextAreaDescripcionS.setColumns(20);
        jTextAreaDescripcionS.setRows(5);
        jTextAreaDescripcionS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaDescripcionSKeyTyped(evt);
            }
        });
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
        jTextFieldPrecioS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioSKeyTyped(evt);
            }
        });

        jLabelFechaSalidaS1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFechaSalidaS1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaSalidaS1.setText("Fecha de entrada:");

        jTextFieldFechaEntradaS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldFechaEntradaS.setEnabled(false);
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
        jTextFieldFechaEntradaS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFechaEntradaSKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTelefonoCliente)
                            .addComponent(jLabelNombreClienteS)
                            .addComponent(jLabelPrecioS))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPrecioS, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldTelefonoClienteS, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNombreClienteS)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFechaSalidaS)
                            .addComponent(jLabelFechaSalidaS1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFechaEntradaS)
                            .addComponent(jTextFieldFechaSalidaS)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreUsuarioS)
                            .addComponent(jLabelEmailClienteS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFolioS))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsernameUs)
                            .addComponent(jTextFieldUsernameClS)
                            .addComponent(jTextFieldFolioS)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTelefonoCliente1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabelTelefonoCliente1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelEdicionServicios.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Edicion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelEdicionServicios.setOpaque(false);

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

        javax.swing.GroupLayout jPanelEdicionServiciosLayout = new javax.swing.GroupLayout(jPanelEdicionServicios);
        jPanelEdicionServicios.setLayout(jPanelEdicionServiciosLayout);
        jPanelEdicionServiciosLayout.setHorizontalGroup(
            jPanelEdicionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEdicionServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEdicionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevoS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGuardarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActualizarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBorrarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelEdicionServiciosLayout.setVerticalGroup(
            jPanelEdicionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEdicionServiciosLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButtonNuevoS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonGuardarS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBorrarS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelTablaClientes.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTablaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Lista de Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaClientes.setOpaque(false);

        jTableServicios.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "E-mail", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "Municipio"
            }
        ));
        jScrollPane1.setViewportView(jTableServicios);

        jButtonBuscarServicio.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButtonBuscarServicio.setText("Buscar");

        jComboBoxBuscarServicio.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBoxBuscarServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Email", "Nombre", "Telefono", "Municipio", "Descripcion", " " }));

        jTextFieldBuscarServicio.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButtonFinalizarServicio.setText("Finalizar Servicio");
        jButtonFinalizarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTablaClientesLayout = new javax.swing.GroupLayout(jPanelTablaClientes);
        jPanelTablaClientes.setLayout(jPanelTablaClientesLayout);
        jPanelTablaClientesLayout.setHorizontalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                        .addComponent(jButtonBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButtonFinalizarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))))
        );
        jPanelTablaClientesLayout.setVerticalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFinalizarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelTablaClientes1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Tabla Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaClientes1.setOpaque(false);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableClientes);

        jButtonBuscarClienteV.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBuscarClienteV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonBuscarClienteV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IconSearch.png"))); // NOI18N
        jButtonBuscarClienteV.setText("Buscar Cliente");
        jButtonBuscarClienteV.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonBuscarClienteV.setContentAreaFilled(false);
        jButtonBuscarClienteV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBuscarClienteV.setDisabledIcon(null);
        jButtonBuscarClienteV.setDisabledSelectedIcon(null);
        jButtonBuscarClienteV.setOpaque(true);
        jButtonBuscarClienteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClienteVActionPerformed(evt);
            }
        });

        jComboBoxTipoClienteV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBoxTipoClienteV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Publico General", "Cliente Frecuente" }));
        jComboBoxTipoClienteV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxTipoClienteVMouseClicked(evt);
            }
        });
        jComboBoxTipoClienteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoClienteVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTablaClientes1Layout = new javax.swing.GroupLayout(jPanelTablaClientes1);
        jPanelTablaClientes1.setLayout(jPanelTablaClientes1Layout);
        jPanelTablaClientes1Layout.setHorizontalGroup(
            jPanelTablaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientes1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelTablaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoClienteV, javax.swing.GroupLayout.Alignment.TRAILING, 0, 191, Short.MAX_VALUE)
                    .addComponent(jTextFieldBuscarClienteV)
                    .addComponent(jButtonBuscarClienteV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelTablaClientes1Layout.setVerticalGroup(
            jPanelTablaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientes1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldBuscarClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTipoClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelServiciosLayout = new javax.swing.GroupLayout(jPanelServicios);
        jPanelServicios.setLayout(jPanelServiciosLayout);
        jPanelServiciosLayout.setHorizontalGroup(
            jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiciosLayout.createSequentialGroup()
                .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelServiciosLayout.createSequentialGroup()
                        .addComponent(jPanelEdicionServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTablaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelServiciosLayout.setVerticalGroup(
            jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelServiciosLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelEdicionServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelTablaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        int cancelar = JOptionPane.showConfirmDialog(null, "Realizar Nuevo Servicio", "Nuevo Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            for (Component component : jPanelTablaClientes1.getComponents()) {
                jTableClientes.setEnabled(true);
                component.setEnabled(true);
            }
            jTextFieldUsernameClS.setText("Publico General");
            jTextFieldUsernameUs.setText(USUARIOPRODUCTOS.getText());
            jButtonGuardarS.setEnabled(true);

            jButtonCancelarS.setEnabled(true);
            jButtonBorrarS.setEnabled(false);
            jButtonActualizarS.setEnabled(false);
            jButtonBuscarServicio.setEnabled(false);

            jTextFieldFolioS.requestFocus();
            codigosServicios();

        } else {
            JOptionPane.showMessageDialog(null, "Nuevo servicio cancelado");
        }
    }//GEN-LAST:event_jButtonNuevoSActionPerformed

    private void jButtonCancelarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarSActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "Realizar Nuevo Servicio", "Nuevo Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            for (Component component : jPanelTablaClientes1.getComponents()) {
                jTableClientes.setEnabled(false);
                component.setEnabled(false);
            }
            jTextFieldUsernameClS.setText("Publico General");
            jTextFieldUsernameUs.setText(USUARIOPRODUCTOS.getText());
            jButtonGuardarS.setEnabled(false);

            jButtonCancelarS.setEnabled(false);
            jButtonBorrarS.setEnabled(true);
            jButtonActualizarS.setEnabled(true);
            jButtonBuscarServicio.setEnabled(true);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Nuevo servicio cancelado");
        }
    }//GEN-LAST:event_jButtonCancelarSActionPerformed

    private void jButtonGuardarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarSActionPerformed

        int cancelar = JOptionPane.showConfirmDialog(null, "¿Almacenar nuevo servicio?", "Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            sqlServicios sqlServ = new sqlServicios();
            servicios servi = new servicios();

            try {

                servi.setFolio_serv(jTextFieldFolioS.getText());
                servi.setUsername_serv_users(jTextFieldUsernameUs.getText());
                servi.setUsername_serv_clien(jTextFieldUsernameClS.getText());
                servi.setFecha_llegada_serv(jTextFieldFechaEntradaS.getText());
                servi.setFecha_salida_serv(jTextFieldFechaSalidaS.getText());
                servi.setNombre_cliente_serv(jTextFieldNombreClienteS.getText());
                servi.setTelefono_cliente_serv(jTextFieldTelefonoClienteS.getText());
                servi.setDescripcion_serv(jTextAreaDescripcionS.getText());
                servi.setPrecio_serv(Double.parseDouble(jTextFieldPrecioS.getText()));
                if (sqlServ.registrarServicio(servi)) {
                    limpiarCampos();
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
                    limpiarCampos();
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

    private void jButtonBuscarClienteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClienteVActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableClientes.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String buscarCliente = this.jTextFieldBuscarClienteV.getText();

            String sql = "SELECT  USERNAME_CL FROM clientes WHERE USERNAME_CL LIKE '%" + buscarCliente + "%'";
            System.out.println(rs);

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Username");

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
    }//GEN-LAST:event_jButtonBuscarClienteVActionPerformed

    private void jComboBoxTipoClienteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoClienteVActionPerformed
        Object venderCliente = jComboBoxTipoClienteV.getSelectedItem();

        if (venderCliente.equals("Publico General")) {
            jTextFieldUsernameClS.setText(venderCliente.toString());
        } else {
            jTableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                //Se obtiene la seleccion de una lista y se imprime en los jTextField
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    try {

                        if (jTableClientes.getSelectedRow() != -1) {
                            int fila = jTableClientes.getSelectedRow();

                            jTextFieldUsernameClS.setText(jTableClientes.getValueAt(fila, 0).toString());

                        }
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Error:\nSelecciona un registro");
                    }
                }
            });
        }
    }//GEN-LAST:event_jComboBoxTipoClienteVActionPerformed

    private void jComboBoxTipoClienteVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxTipoClienteVMouseClicked

    }//GEN-LAST:event_jComboBoxTipoClienteVMouseClicked

    private void jTextFieldFolioSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFolioSKeyTyped
        String Caracteres = jTextFieldFolioS.getText();
        if (Caracteres.length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldFolioSKeyTyped

    private void jTextFieldFechaEntradaSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFechaEntradaSKeyTyped
        String Caracteres = jTextFieldFolioS.getText();
        if (Caracteres.length() > 10) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldFechaEntradaSKeyTyped

    private void jTextFieldTelefonoClienteSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoClienteSKeyTyped
        String Caracteres = jTextFieldTelefonoClienteS.getText();
        if (Caracteres.length() > 19) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoClienteSKeyTyped

    private void jTextFieldPrecioSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioSKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && jTextFieldPrecioS.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPrecioSKeyTyped

    private void jTextFieldNombreClienteSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreClienteSKeyTyped
        String Caracteres = jTextFieldNombreClienteS.getText();
        if (Caracteres.length() > 59) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNombreClienteSKeyTyped

    private void jTextAreaDescripcionSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionSKeyTyped
        String Caracteres = jTextAreaDescripcionS.getText();
        if (Caracteres.length() > 249) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextAreaDescripcionSKeyTyped

    private void jButtonFinalizarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonFinalizarServicioActionPerformed

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
    private javax.swing.JButton jButtonBuscarClienteV;
    private javax.swing.JButton jButtonBuscarServicio;
    private javax.swing.JButton jButtonCancelarS;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonFinalizarServicio;
    private javax.swing.JButton jButtonGuardarS;
    private javax.swing.JButton jButtonNuevoS;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JComboBox<String> jComboBoxBuscarServicio;
    private javax.swing.JComboBox<String> jComboBoxTipoClienteV;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelEdicionServicios;
    private javax.swing.JPanel jPanelMenuSecundario;
    private javax.swing.JPanel jPanelServicios;
    private javax.swing.JPanel jPanelTablaClientes;
    private javax.swing.JPanel jPanelTablaClientes1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableServicios;
    private javax.swing.JTextArea jTextAreaDescripcionS;
    private javax.swing.JTextField jTextFieldBuscarClienteV;
    private javax.swing.JTextField jTextFieldBuscarServicio;
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
