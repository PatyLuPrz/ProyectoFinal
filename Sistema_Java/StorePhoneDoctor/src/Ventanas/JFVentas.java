/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import static Ventanas.JFMenuPrincipal.TIPOUSUARIOMENU;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
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
import modelo.SqlAgregarVenta;
import modelo.agregarVenta;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Norberto
 */
public class JFVentas extends javax.swing.JFrame {

    /*
    
    void detalleboleta(){
        for(int i=0;i<tbdetbol.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO detalleboleta(num_bol,cod_pro,des_pro,cant_pro,pre_unit,pre_venta) VALUES (?,?,?,?,?,?)";
        String numbol=txtnumbol.getText();
        String codpro=tbdetbol.getValueAt(i, 0).toString();
        String despro=tbdetbol.getValueAt(i, 1).toString();
        String cantpro=tbdetbol.getValueAt(i, 3).toString();
        String preunit=tbdetbol.getValueAt(i, 2).toString();
        String importe=tbdetbol.getValueAt(i, 4).toString();
    
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codpro);
            pst.setString(3,despro);
            pst.setString(4,cantpro);
            pst.setString(5,preunit);
            pst.setString(6,importe);
          
             pst.executeUpdate();
          
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
    
     */
    public void limpiarCampos() {
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
                i -= 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void tablaConsultaClientes() {
        try {//Tabla que se encargara de buscar los clientes
            DefaultTableModel modelo = new DefaultTableModel();
            jTableClientes.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null; //Variable de ResultSet
            Conexion conn = new Conexion(); //Conexion con la BD
            Connection con = conn.getConexion();//Se realiza la conexion a la BD

            String sql = "SELECT USERNAME_CL FROM clientes";

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
    }

    public void tablaConsultaProductos() {
        try {//Este metodo consulta los productos para su insercion en la venta
            DefaultTableModel modelo = new DefaultTableModel();
            jTableInventarioVenta.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT CODIGO_P, NOMBRE_P, CATEGORIA_P, MARCA_P, CANTIDAD_P, PRECIO_VENTA_P  FROM productos";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Categoria");
            modelo.addColumn("Marca");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio");

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

    public String fecha() {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }

    public JFVentas() {
        initComponents();
        tablaConsultaClientes();
        tablaConsultaProductos();

        String x = jDateChooser1.getDateFormatString();
        System.out.println(x);

        setIconImage(new ImageIcon(getClass().getResource("../Images/blanco-logo.png")).getImage());

        jLabelClienteVenta.setText("Publico General");
        /*Fondo de Menu Secundario*/
        ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = imagenLogo.getImage().getScaledInstance(jLabelLogoMenu.getWidth(), jLabelLogoMenu.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoMenu.setIcon(iconoEscaladoLogo);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        /*Esta funcion permite centrar el JFrame*/

        jButtonRealizarVenta.setEnabled(false);
        jButtonRealizarCalculo.setEnabled(false);
        jButtonCancelarVenta.setEnabled(false);
        jButtonQuitarProd.setEnabled(false);
        jButtonAgregarProd.setEnabled(false);
        jSpinner1.setEnabled(false);
        jTextFieldDescuentoPCantidad.setEnabled(false);

        for (Component component : jPanelTablaClientes.getComponents()) {
            jTableClientes.setEnabled(false);
            component.setEnabled(false);
        }

        for (Component component : jPanelTablaProductos.getComponents()) {
            jTableInventarioVenta.setEnabled(false);
            component.setEnabled(false);
        }

        //Tabla de ventas
        this.jTableProductosVender.setModel(model);
        this.model.addColumn("Codigo");
        this.model.addColumn("Nombre");
        this.model.addColumn("Categoria");
        this.model.addColumn("Cantidad");
        this.model.addColumn("Usuario");
        this.model.addColumn("Cliente");
        this.model.addColumn("Precio");
        this.model.addColumn("Importe");

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

        jPanelMenuSecundario = new javax.swing.JPanel();
        jLabelLogoMenu = new javax.swing.JLabel();
        jButtonVentas = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jButtonServicios = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        TIPODEUSUARIOPRODUCTOS = new javax.swing.JLabel();
        USUARIOPRODUCTOS = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanelInterfazVentas = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPaneVentas = new javax.swing.JLayeredPane();
        jPanelTablaVentas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductosVender = new javax.swing.JTable();
        jPanelTablaVenta = new javax.swing.JPanel();
        jLabelFolioV = new javax.swing.JLabel();
        jLabelFechaV = new javax.swing.JLabel();
        jLabelFechaVenta = new javax.swing.JLabel();
        jTextFieldFolioVenta = new javax.swing.JTextField();
        jLabelProductoV = new javax.swing.JLabel();
        jLabelProductoAVender = new javax.swing.JLabel();
        jLabelClienteV = new javax.swing.JLabel();
        jLabelClienteVenta = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabelProductoV1 = new javax.swing.JLabel();
        jButtonIniciarVenta = new javax.swing.JButton();
        jLabelDescuento = new javax.swing.JLabel();
        jComboBoxDescuento = new javax.swing.JComboBox<>();
        jLabelDescuentoPCantidad = new javax.swing.JLabel();
        jTextFieldDescuentoPCantidad = new javax.swing.JTextField();
        jLabelProductoV2 = new javax.swing.JLabel();
        jLabelProductoPrecio = new javax.swing.JLabel();
        jPanelTablaVenta2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelTotalImport = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelCambioCliente = new javax.swing.JLabel();
        jTextFieldPagado = new javax.swing.JTextField();
        jPanelTablaClientes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonBuscarClienteV = new javax.swing.JButton();
        jComboBoxTipoClienteV = new javax.swing.JComboBox<>();
        jTextFieldBuscarClienteV = new javax.swing.JTextField();
        jButtonRealizarVenta = new javax.swing.JButton();
        jButtonRealizarCalculo = new javax.swing.JButton();
        jButtonCancelarVenta = new javax.swing.JButton();
        jPanelTablaProductos = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableInventarioVenta = new javax.swing.JTable();
        JButtonBuscarProd = new javax.swing.JButton();
        jComboBoxTipoBusqProd = new javax.swing.JComboBox<>();
        jTextFieldBuscarProd = new javax.swing.JTextField();
        jButtonQuitarProd = new javax.swing.JButton();
        jButtonAgregarProd = new javax.swing.JButton();
        jLayeredPaneCajas = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jToggleButton6 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jToggleButton4 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jToggleButton5 = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jToggleButton3 = new javax.swing.JToggleButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextFieldFechaReporte = new javax.swing.JTextField();
        jLayeredPaneDevoluciones = new javax.swing.JLayeredPane();
        jPanelDevoluciones = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableHistorialDevolucion = new javax.swing.JTable();
        jButtonBuscarHistorialDevoluciones = new javax.swing.JButton();
        jComboBoxSeleccionaDevolucion = new javax.swing.JComboBox<>();
        jTextFieldBuscarHistorialDevolucion = new javax.swing.JTextField();
        jButtonBuscarHistorialDevoluciones1 = new javax.swing.JButton();
        jLayeredPaneHistorialV = new javax.swing.JLayeredPane();
        jPanelHisotiralVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHistorialVentas = new javax.swing.JTable();
        jButtonBuscarHistorialVentas = new javax.swing.JButton();
        jComboBoxSeleccionaHistorial = new javax.swing.JComboBox<>();
        jTextFieldBuscarHistorial = new javax.swing.JTextField();
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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setOpaque(false);

        TIPODEUSUARIOPRODUCTOS.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        TIPODEUSUARIOPRODUCTOS.setForeground(new java.awt.Color(255, 255, 255));

        USUARIOPRODUCTOS.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        USUARIOPRODUCTOS.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username:");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Categoria:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(USUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TIPODEUSUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(USUARIOPRODUCTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TIPODEUSUARIOPRODUCTOS, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenuSecundarioLayout = new javax.swing.GroupLayout(jPanelMenuSecundario);
        jPanelMenuSecundario.setLayout(jPanelMenuSecundarioLayout);
        jPanelMenuSecundarioLayout.setHorizontalGroup(
            jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonServicios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jButtonServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelInterfazVentas.setBackground(new java.awt.Color(0, 0, 0));

        jTabbedPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPane1PropertyChange(evt);
            }
        });

        jPanelTablaVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Lista de Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaVentas.setOpaque(false);
        jPanelTablaVentas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelTablaVentasPropertyChange(evt);
            }
        });

        jTableProductosVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Categoria", "Cantidad", "Usuario", "Cliente", "Precio", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProductosVender);

        javax.swing.GroupLayout jPanelTablaVentasLayout = new javax.swing.GroupLayout(jPanelTablaVentas);
        jPanelTablaVentas.setLayout(jPanelTablaVentasLayout);
        jPanelTablaVentasLayout.setHorizontalGroup(
            jPanelTablaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTablaVentasLayout.setVerticalGroup(
            jPanelTablaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelTablaVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaVenta.setOpaque(false);

        jLabelFolioV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelFolioV.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFolioV.setText("Folio venta:");

        jLabelFechaV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelFechaV.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaV.setText("Fecha:");

        jLabelFechaVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelFechaVenta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelFechaVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelFechaVenta.setPreferredSize(new java.awt.Dimension(14, 24));

        jLabelProductoV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelProductoV.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductoV.setText("Producto:");

        jLabelProductoAVender.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelProductoAVender.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductoAVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelProductoAVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelProductoAVender.setPreferredSize(new java.awt.Dimension(14, 24));

        jLabelClienteV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelClienteV.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClienteV.setText("Cliente:");

        jLabelClienteVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelClienteVenta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClienteVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelClienteVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelClienteVenta.setPreferredSize(new java.awt.Dimension(14, 24));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jSpinner1KeyTyped(evt);
            }
        });

        jLabelProductoV1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelProductoV1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductoV1.setText("Cantidad:");

        jButtonIniciarVenta.setText("Empezar a vender");
        jButtonIniciarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarVentaActionPerformed(evt);
            }
        });

        jLabelDescuento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDescuento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescuento.setText("Descuento:");

        jComboBoxDescuento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin Descuento", "Descuento por cantidad", "%5", "%10", "%15", "%20", "%30", "%40", "%50", "%60", "%70" }));
        jComboBoxDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDescuentoActionPerformed(evt);
            }
        });

        jLabelDescuentoPCantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDescuentoPCantidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescuentoPCantidad.setText("D.p. cantidad:");

        jLabelProductoV2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelProductoV2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductoV2.setText("Precio:");

        jLabelProductoPrecio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelProductoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductoPrecio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelProductoPrecio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelProductoPrecio.setPreferredSize(new java.awt.Dimension(14, 24));

        javax.swing.GroupLayout jPanelTablaVentaLayout = new javax.swing.GroupLayout(jPanelTablaVenta);
        jPanelTablaVenta.setLayout(jPanelTablaVentaLayout);
        jPanelTablaVentaLayout.setHorizontalGroup(
            jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTablaVentaLayout.createSequentialGroup()
                        .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFolioV)
                            .addComponent(jLabelFechaV, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProductoV, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProductoV1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelProductoAVender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFechaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldFolioVenta)))
                    .addGroup(jPanelTablaVentaLayout.createSequentialGroup()
                        .addComponent(jLabelClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonIniciarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelTablaVentaLayout.createSequentialGroup()
                        .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDescuentoPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDescuentoPCantidad)
                            .addComponent(jComboBoxDescuento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaVentaLayout.createSequentialGroup()
                        .addComponent(jLabelProductoV2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelProductoPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTablaVentaLayout.setVerticalGroup(
            jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFolioV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFolioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProductoV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProductoAVender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProductoV1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProductoV2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProductoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTablaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescuentoPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescuentoPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIniciarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelTablaVenta2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaVenta2.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TOTAL:      $");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Pagado:    $");

        jLabelTotalImport.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTotalImport.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTotalImport.setForeground(new java.awt.Color(0, 204, 0));
        jLabelTotalImport.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelTotalImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelTotalImport.setOpaque(true);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Cambio:    $");

        jLabelCambioCliente.setBackground(new java.awt.Color(0, 0, 0));
        jLabelCambioCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelCambioCliente.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCambioCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelCambioCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelCambioCliente.setOpaque(true);

        jTextFieldPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPagadoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelTablaVenta2Layout = new javax.swing.GroupLayout(jPanelTablaVenta2);
        jPanelTablaVenta2.setLayout(jPanelTablaVenta2Layout);
        jPanelTablaVenta2Layout.setHorizontalGroup(
            jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVenta2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCambioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTotalImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPagado))
                .addContainerGap())
        );
        jPanelTablaVenta2Layout.setVerticalGroup(
            jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaVenta2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabelTotalImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jTextFieldPagado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTablaVenta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabelCambioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        jPanelTablaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Tabla Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaClientes.setOpaque(false);

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
        jScrollPane2.setViewportView(jTableClientes);

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
        jComboBoxTipoClienteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoClienteVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTablaClientesLayout = new javax.swing.GroupLayout(jPanelTablaClientes);
        jPanelTablaClientes.setLayout(jPanelTablaClientesLayout);
        jPanelTablaClientesLayout.setHorizontalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                        .addGroup(jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldBuscarClienteV)
                            .addComponent(jComboBoxTipoClienteV, javax.swing.GroupLayout.Alignment.LEADING, 0, 134, Short.MAX_VALUE)
                            .addComponent(jButtonBuscarClienteV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        jPanelTablaClientesLayout.setVerticalGroup(
            jPanelTablaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaClientesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTipoClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonRealizarVenta.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonRealizarVenta.setText("Realizar Venta");
        jButtonRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRealizarVentaActionPerformed(evt);
            }
        });

        jButtonRealizarCalculo.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButtonRealizarCalculo.setText("Realizar Calculo");
        jButtonRealizarCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRealizarCalculoActionPerformed(evt);
            }
        });

        jButtonCancelarVenta.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButtonCancelarVenta.setText("Cancelar Venta");
        jButtonCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarVentaActionPerformed(evt);
            }
        });

        jPanelTablaProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTablaProductos.setOpaque(false);

        jTableInventarioVenta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTableInventarioVenta.setModel(new javax.swing.table.DefaultTableModel(
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
                "Codigo", "Nombre", "Categoria", "Marca", "Cantidad", "Precio Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableInventarioVenta);

        JButtonBuscarProd.setText("Buscar");
        JButtonBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonBuscarProdActionPerformed(evt);
            }
        });

        jComboBoxTipoBusqProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Codigo", "Nombre", "Categoria", "Marca", "Cantidad", "Precio Venta" }));

        javax.swing.GroupLayout jPanelTablaProductosLayout = new javax.swing.GroupLayout(jPanelTablaProductos);
        jPanelTablaProductos.setLayout(jPanelTablaProductosLayout);
        jPanelTablaProductosLayout.setHorizontalGroup(
            jPanelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addGroup(jPanelTablaProductosLayout.createSequentialGroup()
                        .addComponent(JButtonBuscarProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTipoBusqProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTablaProductosLayout.setVerticalGroup(
            jPanelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaProductosLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JButtonBuscarProd)
                    .addComponent(jComboBoxTipoBusqProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscarProd))
                .addGap(14, 14, 14))
        );

        jButtonQuitarProd.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButtonQuitarProd.setText("Quitar Producto");
        jButtonQuitarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarProdActionPerformed(evt);
            }
        });

        jButtonAgregarProd.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButtonAgregarProd.setText("Agregar producto");
        jButtonAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarProdActionPerformed(evt);
            }
        });

        jLayeredPaneVentas.setLayer(jPanelTablaVentas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jPanelTablaVenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jPanelTablaVenta2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jPanelTablaClientes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jButtonRealizarVenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jButtonRealizarCalculo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jButtonCancelarVenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jPanelTablaProductos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jButtonQuitarProd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneVentas.setLayer(jButtonAgregarProd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneVentasLayout = new javax.swing.GroupLayout(jLayeredPaneVentas);
        jLayeredPaneVentas.setLayout(jLayeredPaneVentasLayout);
        jLayeredPaneVentasLayout.setHorizontalGroup(
            jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelTablaVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                        .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTablaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                            .addComponent(jButtonQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonAgregarProd))
                        .addComponent(jButtonRealizarVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelTablaVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelTablaVenta2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                        .addComponent(jButtonRealizarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPaneVentasLayout.setVerticalGroup(
            jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTablaVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneVentasLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanelTablaVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRealizarVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAgregarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRealizarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ventas", jLayeredPaneVentas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), " Caja 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel1.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/VentaNormal.png"))); // NOI18N

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField1.setOpaque(false);

        jToggleButton6.setText("Modificar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jToggleButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton6)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Caja 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel2.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Memoria.png"))); // NOI18N

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField3.setOpaque(false);

        jToggleButton4.setText("Modificar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jToggleButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton4)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Caja 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel4.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MicaDeVidrioTemplado.png"))); // NOI18N

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField2.setOpaque(false);

        jToggleButton5.setText("Modificar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jToggleButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton5)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Caja 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel3.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Servicios.png"))); // NOI18N

        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField4.setOpaque(false);

        jToggleButton1.setText("Modificar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), " Caja 5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel5.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel5.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/FundaTables.png"))); // NOI18N

        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField5.setOpaque(false);

        jToggleButton2.setText("Modificar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jToggleButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton2)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Caja 6", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel6.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel6.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(280, 180));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Inversion.png"))); // NOI18N

        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Cantidad en caja"));
        jTextField6.setOpaque(false);

        jToggleButton3.setText("Modificar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jTextField6))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jToggleButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jToggleButton3)
                .addContainerGap())
        );

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jTextFieldFechaReporte.setText("jTextField7");

        jLayeredPaneCajas.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jTextFieldFechaReporte, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneCajasLayout = new javax.swing.GroupLayout(jLayeredPaneCajas);
        jLayeredPaneCajas.setLayout(jLayeredPaneCajasLayout);
        jLayeredPaneCajasLayout.setHorizontalGroup(
            jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFechaReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jLayeredPaneCajasLayout.setVerticalGroup(
            jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFechaReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cajas", jLayeredPaneCajas);

        jPanelDevoluciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Historial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanelDevoluciones.setOpaque(false);

        jTableHistorialDevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Folio", "Codigo", "Fecha de Garantia", "Cantidad", "Usuario"
            }
        ));
        jScrollPane4.setViewportView(jTableHistorialDevolucion);

        jButtonBuscarHistorialDevoluciones.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarHistorialDevoluciones.setText("Buscar");
        jButtonBuscarHistorialDevoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarHistorialDevolucionesActionPerformed(evt);
            }
        });

        jComboBoxSeleccionaDevolucion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Folio", "Producto", "Cliente", "Fecha" }));

        jButtonBuscarHistorialDevoluciones1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarHistorialDevoluciones1.setText("Devolver Producto");
        jButtonBuscarHistorialDevoluciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarHistorialDevoluciones1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDevolucionesLayout = new javax.swing.GroupLayout(jPanelDevoluciones);
        jPanelDevoluciones.setLayout(jPanelDevolucionesLayout);
        jPanelDevolucionesLayout.setHorizontalGroup(
            jPanelDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDevolucionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                    .addGroup(jPanelDevolucionesLayout.createSequentialGroup()
                        .addComponent(jButtonBuscarHistorialDevoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxSeleccionaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscarHistorialDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBuscarHistorialDevoluciones1)))
                .addContainerGap())
        );
        jPanelDevolucionesLayout.setVerticalGroup(
            jPanelDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDevolucionesLayout.createSequentialGroup()
                .addGroup(jPanelDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscarHistorialDevoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSeleccionaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscarHistorialDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarHistorialDevoluciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPaneDevoluciones.setLayer(jPanelDevoluciones, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneDevolucionesLayout = new javax.swing.GroupLayout(jLayeredPaneDevoluciones);
        jLayeredPaneDevoluciones.setLayout(jLayeredPaneDevolucionesLayout);
        jLayeredPaneDevolucionesLayout.setHorizontalGroup(
            jLayeredPaneDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneDevolucionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDevoluciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPaneDevolucionesLayout.setVerticalGroup(
            jLayeredPaneDevolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneDevolucionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDevoluciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Devoluciones", jLayeredPaneDevoluciones);

        jPanelHisotiralVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Historial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanelHisotiralVentas.setOpaque(false);

        jTableHistorialVentas.setModel(new javax.swing.table.DefaultTableModel(
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
                "Folio", "Producto Vendido", "Cantidad", "Precio Venta", "Usuario", "Cliente", "Fecha"
            }
        ));
        jScrollPane3.setViewportView(jTableHistorialVentas);

        jButtonBuscarHistorialVentas.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarHistorialVentas.setText("Buscar");
        jButtonBuscarHistorialVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarHistorialVentasActionPerformed(evt);
            }
        });

        jComboBoxSeleccionaHistorial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Username", "Email", "Nombre", "Telefono", "Municipio" }));

        javax.swing.GroupLayout jPanelHisotiralVentasLayout = new javax.swing.GroupLayout(jPanelHisotiralVentas);
        jPanelHisotiralVentas.setLayout(jPanelHisotiralVentasLayout);
        jPanelHisotiralVentasLayout.setHorizontalGroup(
            jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHisotiralVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelHisotiralVentasLayout.createSequentialGroup()
                        .addComponent(jButtonBuscarHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxSeleccionaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 315, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelHisotiralVentasLayout.setVerticalGroup(
            jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHisotiralVentasLayout.createSequentialGroup()
                .addGroup(jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscarHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSeleccionaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPaneHistorialV.setLayer(jPanelHisotiralVentas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneHistorialVLayout = new javax.swing.GroupLayout(jLayeredPaneHistorialV);
        jLayeredPaneHistorialV.setLayout(jLayeredPaneHistorialVLayout);
        jLayeredPaneHistorialVLayout.setHorizontalGroup(
            jLayeredPaneHistorialVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneHistorialVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHisotiralVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPaneHistorialVLayout.setVerticalGroup(
            jLayeredPaneHistorialVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneHistorialVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHisotiralVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historial de ventas", jLayeredPaneHistorialV);

        javax.swing.GroupLayout jPanelInterfazVentasLayout = new javax.swing.GroupLayout(jPanelInterfazVentas);
        jPanelInterfazVentas.setLayout(jPanelInterfazVentasLayout);
        jPanelInterfazVentasLayout.setHorizontalGroup(
            jPanelInterfazVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterfazVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanelInterfazVentasLayout.setVerticalGroup(
            jPanelInterfazVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterfazVentasLayout.createSequentialGroup()
                .addComponent(jTabbedPane1)
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
        jMenuInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuInicioActionPerformed(evt);
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
                .addComponent(jPanelInterfazVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInterfazVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    DefaultTableModel model = new DefaultTableModel();
    private void jPanelMenuSecundarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelMenuSecundarioPropertyChange
        ImagenMenuSecundario ImagenMenuSec = new ImagenMenuSecundario();
        jPanelMenuSecundario.add(ImagenMenuSec);
        jPanelMenuSecundario.repaint();
    }//GEN-LAST:event_jPanelMenuSecundarioPropertyChange

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed

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

    private void jButtonServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonServiciosActionPerformed
        JFServicios servicios = new JFServicios();
        servicios.setVisible(true);
        servicios.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
        servicios.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonServiciosActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        JFClientes clientes = new JFClientes();
        clientes.setVisible(true);
        clientes.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
        clientes.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jPanelTablaVentasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelTablaVentasPropertyChange
        Imagen Imagen = new Imagen();
        jPanelInterfazVentas.add(Imagen);
        jPanelInterfazVentas.repaint();
    }//GEN-LAST:event_jPanelTablaVentasPropertyChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFMenuPrincipal.JFventas = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonBuscarHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarHistorialVentasActionPerformed

    }//GEN-LAST:event_jButtonBuscarHistorialVentasActionPerformed

    private void jButtonBuscarHistorialDevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarHistorialDevolucionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarHistorialDevolucionesActionPerformed

    private void jButtonBuscarHistorialDevoluciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarHistorialDevoluciones1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarHistorialDevoluciones1ActionPerformed

    private void jButtonAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarProdActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea agregar un producto al carrito?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            SqlAgregarVenta sqlVenA = new SqlAgregarVenta();
            agregarVenta agreV = new agregarVenta();

            try {
                agreV.setCodigo(jLabelProductoAVender.getText());

                if (sqlVenA.agregarProductoTabla(agreV)) {

                    String cod = jLabelProductoAVender.getText();
                    String nom = agreV.getNombre();
                    String cat = agreV.getCategoria();
                    Integer can = (Integer) (jSpinner1.getValue());
                    String usu = USUARIOPRODUCTOS.getText();
                    String cli = jLabelClienteVenta.getText();
                    Double pre = Double.parseDouble(jLabelProductoPrecio.getText());

                    Double imp = 0.0;
                    String s = null;
                    int s1 = 0;
                    int a = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {//ciclo que busca el codigo del producto y lo iguala para sumar solo la cantidad
                        if (model.getValueAt(i, 0).toString().equals(cod)) {
                            a += 1;
                            s = model.getValueAt(i, 3).toString();
                            int canP = Integer.parseInt(s);
                            s1 = canP += can; //Variable de obtencion de cantidad de productos que se repiten
                            imp = (pre * s1); //Variable de importe total
                            model.setValueAt(s1, i, 3); //Modificacion de la celda de cantidad
                            model.setValueAt(imp, i, 7);//Modificacion de la celda de importe

                        }
                    }
                    if (a > 0) {
                        System.out.println("Se encontró el número " + a + " veces");

                    } else {
                        System.out.println("No se encontró el número");
                        DefaultTableModel model = (DefaultTableModel) jTableProductosVender.getModel();
                        double impo1 = (pre * can); //Importe incial
                        model.addRow(new Object[]{cod, nom, cat, can, usu, cli, pre, impo1});
                    }

                    /*
                    for (int i = 0; i < jTableProductosVender.getRowCount(); i++) {
                        String valor = jTableProductosVender.getValueAt(i, 0).toString().trim();
                        System.out.println(valor);

                    }*/
                }

            } catch (Exception e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se agrego al carrito el producto");
        }
    }//GEN-LAST:event_jButtonAgregarProdActionPerformed

    private void jButtonRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRealizarVentaActionPerformed
        try {
            double importeACobrar = Double.parseDouble(jLabelTotalImport.getText());
            double cantidadPagada = Double.parseDouble(jTextFieldPagado.getText());

            if (jTextFieldPagado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el pago");
            } else if (importeACobrar > cantidadPagada || cantidadPagada <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida");
            } else {

                JOptionPane.showMessageDialog(this, "Servido vato");
                double cambioCliente = (cantidadPagada - importeACobrar);
                jLabelCambioCliente.setText(String.valueOf(cambioCliente));
            }
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese el pago");
        }

    }//GEN-LAST:event_jButtonRealizarVentaActionPerformed

    private void JButtonBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonBuscarProdActionPerformed

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableInventarioVenta.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String buscarProducto = this.jTextFieldBuscarProd.getText();
            String tipobusq = null;

            int tipo = jComboBoxTipoBusqProd.getSelectedIndex();

            switch (tipo) {//Se encarga de evaluar el combobox
                case 1:
                    tipobusq = "CODIGO_P";
                    break;
                case 2:
                    tipobusq = "NOMBRE_P";
                    break;
                case 3:
                    tipobusq = "CATEGORIA_P";
                    break;
                case 4:
                    tipobusq = "MARCA_P";
                    break;
                case 5:
                    tipobusq = "CANTIDAD_P";
                    break;
                case 6:
                    tipobusq = "PRECIO_COMPRA_P";
                    break;
            }

            String sql = "SELECT CODIGO_P, NOMBRE_P, CATEGORIA_P, MARCA_P, CANTIDAD_P, PRECIO_VENTA_P FROM productos WHERE " + tipobusq + " LIKE '%" + buscarProducto + "%'";
            System.out.println(rs);

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Categoria");
            modelo.addColumn("Marca");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio Venta");

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

    }//GEN-LAST:event_JButtonBuscarProdActionPerformed

    private void jButtonCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarVentaActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Quieres cancelar la venta?", "Cancelar Venta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            limpiarCampos();
            for (Component component : jPanelTablaClientes.getComponents()) {
                jTableClientes.setEnabled(false);
                component.setEnabled(false);
            }

            for (Component component : jPanelTablaProductos.getComponents()) {
                jTableInventarioVenta.setEnabled(false);
                component.setEnabled(false);
            }

            jButtonRealizarVenta.setEnabled(false);
            jButtonRealizarCalculo.setEnabled(false);
            jButtonCancelarVenta.setEnabled(false);
            jTabbedPane1.setEnabled(true);
            jPanelTablaProductos.setEnabled(false);
            jPanelTablaClientes.setEnabled(false);
            jButtonQuitarProd.setEnabled(false);
            jButtonIniciarVenta.setEnabled(true);
            jButtonAgregarProd.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(null, "No se agrego al carrito el producto");
        }
    }//GEN-LAST:event_jButtonCancelarVentaActionPerformed

    private void jMenuInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuInicioActionPerformed

    private void jMenuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuInicioMouseClicked
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
        menu.USUARIOMENU.setText(USUARIOPRODUCTOS.getText());
        menu.TIPOUSUARIOMENU.setText(TIPODEUSUARIOPRODUCTOS.getText());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuInicioMouseClicked

    private void jButtonQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarProdActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTableProductosVender.getModel(); //TableProducto es el nombre de mi tabla ;) 
        try {
            if (jTableProductosVender.getSelectedRow() != 1) {
                dtm.removeRow(jTableProductosVender.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de Lista Venta");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione el producto\n para eliminar de la venta");
        }
    }//GEN-LAST:event_jButtonQuitarProdActionPerformed

    private void jComboBoxTipoClienteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoClienteVActionPerformed
        Object venderCliente = jComboBoxTipoClienteV.getSelectedItem();

        if (venderCliente.equals("Publico General")) {
            jLabelClienteVenta.setText(venderCliente.toString());
        } else {
            jTableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                //Se obtiene la seleccion de una lista y se imprime en los jTextField
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    try {

                        if (jTableClientes.getSelectedRow() != -1) {
                            int fila = jTableClientes.getSelectedRow();

                            jLabelClienteVenta.setText(jTableClientes.getValueAt(fila, 0).toString());

                        }
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Error:\nSelecciona un registro");
                    }
                }
            });
        }
    }//GEN-LAST:event_jComboBoxTipoClienteVActionPerformed

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

    private void jButtonIniciarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarVentaActionPerformed
        for (Component component : jPanelTablaClientes.getComponents()) {
            jTableClientes.setEnabled(true);
            component.setEnabled(true);
        }

        for (Component component : jPanelTablaProductos.getComponents()) {
            jTableInventarioVenta.setEnabled(true);
            jTableInventarioVenta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                //Se obtiene la seleccion de una lista y se imprime en los jTextField
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    try {

                        if (jTableInventarioVenta.getSelectedRow() != -1) {
                            int fila = jTableInventarioVenta.getSelectedRow();

                            jLabelProductoAVender.setText(jTableInventarioVenta.getValueAt(fila, 0).toString());
                            jTextFieldDescuentoPCantidad.setText(jTableInventarioVenta.getValueAt(fila, 5).toString());

                        }
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Error:\nSelecciona un registro");
                    }
                }
            });
            component.setEnabled(true);
        }

        jButtonRealizarCalculo.setEnabled(true);
        jButtonCancelarVenta.setEnabled(true);
        jTabbedPane1.setEnabled(false);
        jPanelTablaProductos.setEnabled(true);
        jPanelTablaClientes.setEnabled(true);
        jButtonQuitarProd.setEnabled(true);
        jButtonAgregarProd.setEnabled(true);
        jButtonIniciarVenta.setEnabled(false);
        jSpinner1.setEnabled(true);

    }//GEN-LAST:event_jButtonIniciarVentaActionPerformed

    private void jButtonRealizarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRealizarCalculoActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Calcular importe?", "Importe de Venta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            jButtonRealizarVenta.setEnabled(true);
            int filas = model.getRowCount();
            double totalE = 0.0;

            for (int i = 0; i < filas; i++) {//Ciclo para obtener el importe de los productos
                totalE = totalE + Double.parseDouble(model.getValueAt(i, 7).toString());
            }
            jLabelTotalImport.setText(String.valueOf(totalE));
            //El label de Totalimport cambia su contenido por el resultado del importe de la venta
        } else {
            JOptionPane.showMessageDialog(null, "No se agrego al carrito el producto");
        }

    }//GEN-LAST:event_jButtonRealizarCalculoActionPerformed

    private void jSpinner1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner1KeyTyped

    }//GEN-LAST:event_jSpinner1KeyTyped

    private void jTextFieldPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPagadoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && jTextFieldPagado.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPagadoKeyTyped

    private void jTabbedPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1PropertyChange

    private void jComboBoxDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDescuentoActionPerformed
        int descuentoP = jComboBoxDescuento.getSelectedIndex();
        double descuentoPCantidad = Double.parseDouble(jTextFieldDescuentoPCantidad.getText().toString());
        String precioFinal = null;
        double descuento = 0.0;
        switch (descuentoP) {
            case 0:
                jLabelProductoPrecio.setText(jTextFieldDescuentoPCantidad.getText());
                break;
            case 1:
                jTextFieldDescuentoPCantidad.setEnabled(true);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .05));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);

                break;
            case 2:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .05));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 3:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .10));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 4:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .15));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 5:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .20));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 6:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .30));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 7:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .40));
                precioFinal = Double.toString(descuento);
                break;
            case 8:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .50));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 9:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .60));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 10:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .70));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
        }
    }//GEN-LAST:event_jComboBoxDescuentoActionPerformed

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
            java.util.logging.Logger.getLogger(JFVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton JButtonBuscarProd;
    public javax.swing.JLabel TIPODEUSUARIOPRODUCTOS;
    public javax.swing.JLabel USUARIOPRODUCTOS;
    private javax.swing.JButton jButtonAgregarProd;
    private javax.swing.JButton jButtonBuscarClienteV;
    private javax.swing.JButton jButtonBuscarHistorialDevoluciones;
    private javax.swing.JButton jButtonBuscarHistorialDevoluciones1;
    private javax.swing.JButton jButtonBuscarHistorialVentas;
    private javax.swing.JButton jButtonCancelarVenta;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonIniciarVenta;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonQuitarProd;
    private javax.swing.JButton jButtonRealizarCalculo;
    private javax.swing.JButton jButtonRealizarVenta;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JComboBox<String> jComboBoxDescuento;
    private javax.swing.JComboBox<String> jComboBoxSeleccionaDevolucion;
    private javax.swing.JComboBox<String> jComboBoxSeleccionaHistorial;
    private javax.swing.JComboBox<String> jComboBoxTipoBusqProd;
    private javax.swing.JComboBox<String> jComboBoxTipoClienteV;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCambioCliente;
    private javax.swing.JLabel jLabelClienteV;
    public javax.swing.JLabel jLabelClienteVenta;
    private javax.swing.JLabel jLabelDescuento;
    private javax.swing.JLabel jLabelDescuentoPCantidad;
    private javax.swing.JLabel jLabelFechaV;
    private javax.swing.JLabel jLabelFechaVenta;
    private javax.swing.JLabel jLabelFolioV;
    private javax.swing.JLabel jLabelLogoMenu;
    public javax.swing.JLabel jLabelProductoAVender;
    public javax.swing.JLabel jLabelProductoPrecio;
    private javax.swing.JLabel jLabelProductoV;
    private javax.swing.JLabel jLabelProductoV1;
    private javax.swing.JLabel jLabelProductoV2;
    private javax.swing.JLabel jLabelTotalImport;
    private javax.swing.JLayeredPane jLayeredPaneCajas;
    private javax.swing.JLayeredPane jLayeredPaneDevoluciones;
    private javax.swing.JLayeredPane jLayeredPaneHistorialV;
    private javax.swing.JLayeredPane jLayeredPaneVentas;
    private javax.swing.JMenu jMenuAcercaDe;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelDevoluciones;
    private javax.swing.JPanel jPanelHisotiralVentas;
    private javax.swing.JPanel jPanelInterfazVentas;
    private javax.swing.JPanel jPanelMenuSecundario;
    private javax.swing.JPanel jPanelTablaClientes;
    private javax.swing.JPanel jPanelTablaProductos;
    private javax.swing.JPanel jPanelTablaVenta;
    private javax.swing.JPanel jPanelTablaVenta2;
    private javax.swing.JPanel jPanelTablaVentas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableHistorialDevolucion;
    private javax.swing.JTable jTableHistorialVentas;
    public javax.swing.JTable jTableInventarioVenta;
    private javax.swing.JTable jTableProductosVender;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextFieldBuscarClienteV;
    private javax.swing.JTextField jTextFieldBuscarHistorial;
    private javax.swing.JTextField jTextFieldBuscarHistorialDevolucion;
    private javax.swing.JTextField jTextFieldBuscarProd;
    private javax.swing.JTextField jTextFieldDescuentoPCantidad;
    private javax.swing.JTextField jTextFieldFechaReporte;
    private javax.swing.JTextField jTextFieldFolioVenta;
    private javax.swing.JTextField jTextFieldPagado;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    // End of variables declaration//GEN-END:variables

    private void setBorder(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFocusPainted(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
