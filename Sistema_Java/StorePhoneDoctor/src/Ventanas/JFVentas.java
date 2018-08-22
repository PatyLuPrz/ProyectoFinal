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
import java.sql.Statement;
import java.text.ParseException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.GenerarCodigos;

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
            codigosVentas();
            jLabelFechaVenta.setText(fecha());
            jButtonRealizarVenta.setEnabled(false);
            jButtonRealizarCalculo.setEnabled(false);
            jButtonCancelarVenta.setEnabled(false);
            jButtonQuitarProd.setEnabled(false);
            jButtonAgregarProd.setEnabled(false);
            jSpinner1.setEnabled(false);
            jTextFieldDescuentoPCantidad.setEnabled(false);
            jComboBoxDescuento.setEnabled(false);
            jTabbedPane1.setEnabled(true);
            jButtonIniciarVenta.setEnabled(true);

            for (Component component : jPanelTablaClientes.getComponents()) {
                jTableClientes.setEnabled(false);
                component.setEnabled(false);
            }

            for (Component component : jPanelTablaProductos.getComponents()) {
                jTableInventarioVenta.setEnabled(false);
                component.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "");
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
        jLabelFechaVenta.setText(fecha());

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
        jComboBoxDescuento.setEnabled(false);

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

        codigosVentas();

    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Images/IconoPrograma.png"));
        return retValue;/*Se genero una clase donde se manda llamar un icono para JFrame*/
    }

    void codigosVentas() {

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
                jTextFieldFolioVenta.setText("VSP0000000001");
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
                jTextFieldFolioVenta.setText("VSP" + gen.serie());

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
        jLayeredPaneHistorialV = new javax.swing.JLayeredPane();
        jPanelHisotiralVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHistorialVentasProd = new javax.swing.JTable();
        jButtonBuscarHistorialVentas = new javax.swing.JButton();
        jLayeredPaneCajas = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableHistorialVentasProdF = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabelFechaInicial = new javax.swing.JLabel();
        jLabelFechaFinal = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldVentaTotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBoxTipoFecha = new javax.swing.JComboBox<>();
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
        USUARIOPRODUCTOS.setText("NorbertoPaloma1998");

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
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTablaVentasLayout.setVerticalGroup(
            jPanelTablaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

        jTextFieldFolioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFolioVentaKeyTyped(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jButtonIniciarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelTablaVenta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneVentasLayout.createSequentialGroup()
                            .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonRealizarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonCancelarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAgregarProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jButtonRealizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPaneVentasLayout.setVerticalGroup(
            jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTablaVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTablaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneVentasLayout.createSequentialGroup()
                        .addComponent(jPanelTablaVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRealizarVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAgregarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPaneVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRealizarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ventas", jLayeredPaneVentas);

        jPanelHisotiralVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Historial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanelHisotiralVentas.setOpaque(false);

        jTableHistorialVentasProd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableHistorialVentasProd);

        jButtonBuscarHistorialVentas.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBuscarHistorialVentas.setText("Buscar");
        jButtonBuscarHistorialVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarHistorialVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHisotiralVentasLayout = new javax.swing.GroupLayout(jPanelHisotiralVentas);
        jPanelHisotiralVentas.setLayout(jPanelHisotiralVentasLayout);
        jPanelHisotiralVentasLayout.setHorizontalGroup(
            jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHisotiralVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelHisotiralVentasLayout.createSequentialGroup()
                        .addComponent(jButtonBuscarHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelHisotiralVentasLayout.setVerticalGroup(
            jPanelHisotiralVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHisotiralVentasLayout.createSequentialGroup()
                .addComponent(jButtonBuscarHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanelHisotiralVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historial de ventas", jLayeredPaneHistorialV);

        jTableHistorialVentasProdF.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID V. Producto", "Codigo", "Nombre", "Cantidad", "Precio", "Subtotal", "Categoria", "Fecha"
            }
        ));
        jScrollPane4.setViewportView(jTableHistorialVentasProdF);

        jLabelFechaInicial.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabelFechaInicial.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaInicial.setText("Fecha Inicial:");

        jLabelFechaFinal.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabelFechaFinal.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaFinal.setText("Fecha Final:");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Venta Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14))); // NOI18N
        jPanel8.setMaximumSize(new java.awt.Dimension(250, 150));
        jPanel8.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(280, 180));

        jTextFieldVentaTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255))));
        jTextFieldVentaTotal.setOpaque(false);

        jButton2.setText("Calcular");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldVentaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldVentaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxTipoFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Venta Normal", "Mica de Vidrio ", "Funda", "Memoria", "Inversion" }));

        jLayeredPaneCajas.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jDateChooser2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jLabelFechaInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jLabelFechaFinal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jPanel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneCajas.setLayer(jComboBoxTipoFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneCajasLayout = new javax.swing.GroupLayout(jLayeredPaneCajas);
        jLayeredPaneCajas.setLayout(jLayeredPaneCajasLayout);
        jLayeredPaneCajasLayout.setHorizontalGroup(
            jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneCajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelFechaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTipoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPaneCajasLayout.setVerticalGroup(
            jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPaneCajasLayout.createSequentialGroup()
                                .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelFechaInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneCajasLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)))
                        .addGroup(jLayeredPaneCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Detalles Ventas", jLayeredPaneCajas);

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
                .addComponent(jPanelInterfazVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInterfazVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFMenuPrincipal.JFventas = null;
    }//GEN-LAST:event_formWindowClosing

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

    private void jTabbedPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1PropertyChange

    private void jButtonBuscarHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarHistorialVentasActionPerformed

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableHistorialVentasProd.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT * FROM historial_ventas_p";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Folio Venta");
            modelo.addColumn("Codigo Producto");
            modelo.addColumn("Precio Producto");
            modelo.addColumn("Subtotal");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Usuario");
            modelo.addColumn("Cliente");
            modelo.addColumn("Fecha");
            modelo.addColumn("Nombre");
            modelo.addColumn("Marca");
            modelo.addColumn("Categoria");

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
    }//GEN-LAST:event_jButtonBuscarHistorialVentasActionPerformed

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
                    Integer canti = can;
                    String usu = USUARIOPRODUCTOS.getText();
                    String cli = jLabelClienteVenta.getText();
                    Double pre = Double.parseDouble(jLabelProductoPrecio.getText());
                    Integer canDescontar = agreV.getCantidad();

                    Double imp = 0.0;
                    String s = null;
                    int s1 = 0;
                    int a = 0;
                    int s2 = 0;
                    int canP = 0;
                    if (!(canti > canDescontar)) {
                        for (int i = 0; i < model.getRowCount(); i++) {//ciclo que busca el codigo del producto y lo iguala para sumar solo la cantidad
                            if (model.getValueAt(i, 0).toString().equals(cod)) {
                                a += 1;
                                s = model.getValueAt(i, 3).toString();
                                canP = Integer.parseInt(s);
                                s1 = canP += can; //Variable de obtencion de cantidad de productos que se repiten
                                imp = (pre * s1); //Variable de importe total
                                model.setValueAt(s1, i, 3); //Modificacion de la celda de cantidad
                                model.setValueAt(imp, i, 7);//Modificacion de la celda de importe
                            }
                        }
                        if (a > 0) {
                            System.out.println("Se encontró el número " + a + " veces");
                            System.out.println(canP);

                        } else {
                            System.out.println("No se encontró el número");
                            DefaultTableModel model = (DefaultTableModel) jTableProductosVender.getModel();
                            double impo1 = (pre * can); //Importe incial
                            model.addRow(new Object[]{cod, nom, cat, can, usu, cli, pre, impo1});

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes tantos productos para esta venta");
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
                    tipobusq = "PRECIO_VENTA_P";
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
            for (Component component : jTabbedPane1.getComponents()) {
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

    private void jButtonRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRealizarVentaActionPerformed

        try {
            double importeACobrar = 0.0;
            double cantidadPagada = 0.0;
            double cambioCliente = 0.0;
            cantidadPagada = Double.parseDouble(jTextFieldPagado.getText());
            importeACobrar = Double.parseDouble(jLabelTotalImport.getText());

            if (jTextFieldPagado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el pago");
            } else if (importeACobrar > cantidadPagada || cantidadPagada <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida");
            } else {
                SqlAgregarVenta sqlVenta = new SqlAgregarVenta();
                agregarVenta venta = new agregarVenta();
                cambioCliente = (cantidadPagada - importeACobrar);

                jLabelCambioCliente.setText(String.valueOf(cambioCliente));

                venta.setFolio_ven(jTextFieldFolioVenta.getText());
                venta.setUsuario(USUARIOPRODUCTOS.getText());
                venta.setCliente(jLabelClienteVenta.getText());
                venta.setFecha_ven(jLabelFechaVenta.getText());
                venta.setImporte(Double.parseDouble(jLabelTotalImport.getText()));
                if (sqlVenta.registrarVenta(venta)) {
                    for (int i = 0; i < jTableProductosVender.getRowCount(); i++) {
                        System.out.println(i);
                        venta.setFolio_ven(jTextFieldFolioVenta.getText());
                        venta.setCodigo(jTableProductosVender.getValueAt(i, 0).toString());
                        venta.setCantidad(Integer.parseInt(jTableProductosVender.getValueAt(i, 3).toString()));
                        venta.setPrecio(Double.parseDouble(jTableProductosVender.getValueAt(i, 6).toString()));
                        venta.setImporte_ven_p(Double.parseDouble(jTableProductosVender.getValueAt(i, 7).toString()));
                        if (sqlVenta.registrarVentaProducto(venta)) {
                            venta.setCodigo(jTableProductosVender.getValueAt(i, 0).toString());
                            venta.setCantidad(Integer.parseInt(jTableProductosVender.getValueAt(i, 3).toString()));
                            if (sqlVenta.descontarProducto(venta)) {

                            } else {
                                System.out.println("Error de descuento stock");
                            }
                        } else {
                            System.out.println("Error de agregar venta producto");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Venta exitosa");
                } else {
                    System.out.println("Error de agregar venta");
                }
                JOptionPane.showMessageDialog(null, "Venta Realizada");
                limpiarCampos();
                codigosVentas();
            }
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese el pago" + ex);
        }
    }//GEN-LAST:event_jButtonRealizarVentaActionPerformed

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

    private void jTextFieldPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPagadoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && jTextFieldPagado.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPagadoKeyTyped

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
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .05));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 2:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .10));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 3:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .15));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 4:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .20));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 5:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .30));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 6:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .40));
                precioFinal = Double.toString(descuento);
                break;
            case 7:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .50));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 8:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .60));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
            case 9:
                jTextFieldDescuentoPCantidad.setEnabled(false);
                descuento = (descuentoPCantidad - (descuentoPCantidad * .70));
                precioFinal = Double.toString(descuento);
                jLabelProductoPrecio.setText(precioFinal);
                break;
        }
    }//GEN-LAST:event_jComboBoxDescuentoActionPerformed

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
                            jLabelProductoPrecio.setText(jTableInventarioVenta.getValueAt(fila, 5).toString());
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
        jComboBoxDescuento.setEnabled(true);
    }//GEN-LAST:event_jButtonIniciarVentaActionPerformed

    private void jSpinner1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner1KeyTyped

    }//GEN-LAST:event_jSpinner1KeyTyped

    private void jTextFieldFolioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFolioVentaKeyTyped
        String Caracteres = jTextFieldFolioVenta.getText();
        if (Caracteres.length() > 12) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldFolioVentaKeyTyped

    private void jPanelTablaVentasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelTablaVentasPropertyChange
        Imagen Imagen = new Imagen();
        jPanelInterfazVentas.add(Imagen);
        jPanelInterfazVentas.repaint();
    }//GEN-LAST:event_jPanelTablaVentasPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tipo = jComboBoxTipoFecha.getSelectedItem().toString();
        Date dateInicio = jDateChooser2.getDate();//Fecha de inicio para la buqueda
        long fechaInicio = dateInicio.getTime();
        java.sql.Date primeroF = new java.sql.Date(fechaInicio);

        Date dateFinal = jDateChooser1.getDate();//Fecha Final de busqueda
        long fechaFinal = dateFinal.getTime();
        java.sql.Date ultimoF = new java.sql.Date(fechaFinal);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");//Convertir de BD a fecha de Jcalendar
        Date fechaTxt;

        try {
            fechaTxt = formatoFecha.parse(jLabelFechaVenta.getText());

        } catch (ParseException ex) {
            Logger.getLogger(JFVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableHistorialVentasProdF.setModel(modelo);
            String sqlVentaNormal = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (tipo.equals("Todo")) {
                sqlVentaNormal = "SELECT\n"
                        + "ventas_producto.ID_VP,\n"
                        + "ventas_producto.CODIGO_P,\n"
                        + "productos.NOMBRE_P,\n"
                        + "ventas_producto.CANTIDAD_VP,\n"
                        + "ventas_producto.PRECIO_VP,\n"
                        + "ventas_producto.SUBTOTAL_VP,\n"
                        + "productos.CATEGORIA_P,\n"
                        + "ventas.FECHA_V\n"
                        + "FROM\n"
                        + "ventas_producto\n"
                        + "INNER JOIN productos ON ventas_producto.CODIGO_P = productos.CODIGO_P\n"
                        + "INNER JOIN ventas ON ventas_producto.FOLIO_V = ventas.FOLIO_V "
                        + "WHERE ventas.FECHA_V BETWEEN '" + primeroF + "' AND '" + ultimoF + "';";
                ps = con.prepareStatement(sqlVentaNormal);
                rs = ps.executeQuery();
            } else {
                sqlVentaNormal = "SELECT\n"
                        + "ventas_producto.ID_VP,\n"
                        + "ventas_producto.CODIGO_P,\n"
                        + "productos.NOMBRE_P,\n"
                        + "ventas_producto.CANTIDAD_VP,\n"
                        + "ventas_producto.PRECIO_VP,\n"
                        + "ventas_producto.SUBTOTAL_VP,\n"
                        + "productos.CATEGORIA_P,\n"
                        + "ventas.FECHA_V\n"
                        + "FROM\n"
                        + "ventas_producto\n"
                        + "INNER JOIN productos ON ventas_producto.CODIGO_P = productos.CODIGO_P\n"
                        + "INNER JOIN ventas ON ventas_producto.FOLIO_V = ventas.FOLIO_V "
                        + "WHERE ventas.FECHA_V BETWEEN '" + primeroF + "' AND '" + ultimoF + "' AND productos.CATEGORIA_P = ?;";
                ps = con.prepareStatement(sqlVentaNormal);
                ps.setString(1, tipo);
                rs = ps.executeQuery();
            }

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID V. Producto");
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio");
            modelo.addColumn("Subtotal");
            modelo.addColumn("Categoria");
            modelo.addColumn("Fecha");
            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {

                    filas[i] = rs.getObject(i + 1);

                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {
            Logger.getLogger(JFVentas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        double ImporteMica = 0.0;
        double a = 0.0;
        double ImporteFunda = 0.0;
        double b = 0.0;
        double ImporteMemoria = 0.0;
        double c = 0.0;
        double ImporteVenta = 0.0;
        double d = 0.0;
        double ImporteInversion = 0.0;
        double e = 0.0;
        if (jTableHistorialVentasProdF.getRowCount() > 0) {
            for (int i = 0; i < jTableHistorialVentasProdF.getRowCount(); i++) {
                double Mica = Double.parseDouble(jTableHistorialVentasProdF.getValueAt(i, 5).toString());
                a += Mica;
            }
            jTextFieldVentaTotal.setText("El total es: " + a);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAgregarProd;
    private javax.swing.JButton jButtonBuscarClienteV;
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
    private javax.swing.JComboBox<String> jComboBoxTipoBusqProd;
    private javax.swing.JComboBox<String> jComboBoxTipoClienteV;
    private javax.swing.JComboBox<String> jComboBoxTipoFecha;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCambioCliente;
    private javax.swing.JLabel jLabelClienteV;
    public javax.swing.JLabel jLabelClienteVenta;
    private javax.swing.JLabel jLabelDescuento;
    private javax.swing.JLabel jLabelDescuentoPCantidad;
    private javax.swing.JLabel jLabelFechaFinal;
    private javax.swing.JLabel jLabelFechaInicial;
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
    private javax.swing.JLayeredPane jLayeredPaneHistorialV;
    private javax.swing.JLayeredPane jLayeredPaneVentas;
    private javax.swing.JMenu jMenuAcercaDe;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
    private javax.swing.JTable jTableHistorialVentasProd;
    private javax.swing.JTable jTableHistorialVentasProdF;
    public javax.swing.JTable jTableInventarioVenta;
    private javax.swing.JTable jTableProductosVender;
    private javax.swing.JTextField jTextFieldBuscarClienteV;
    private javax.swing.JTextField jTextFieldBuscarProd;
    private javax.swing.JTextField jTextFieldDescuentoPCantidad;
    private javax.swing.JTextField jTextFieldFolioVenta;
    private javax.swing.JTextField jTextFieldPagado;
    private javax.swing.JTextField jTextFieldVentaTotal;
    // End of variables declaration//GEN-END:variables

    private void setBorder(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFocusPainted(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
