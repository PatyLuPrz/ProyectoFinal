package Ventanas;

import static Ventanas.JFMenuPrincipal.TIPOUSUARIOMENU;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Conexion;
import modelo.SqlProductos;
import modelo.fotoclass;
import modelo.productos;

/**
 *
 * @author Norberto
 */
public class JFProductos extends javax.swing.JFrame {

    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de imagen", "jpg");
    String rutaImagen;
    fotoclass f = new fotoclass(); //Objeto de la clase fotoclass

    public void tablaConsulta() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableInventario.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT CODIGO_P, NOMBRE_P, CATEGORIA_P, MARCA_P, CANTIDAD_P, PRECIO_COMPRA_P, PRECIO_VENTA_P FROM productos";
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
            modelo.addColumn("Precio Compra");
            modelo.addColumn("Precio Venta");
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

    public void recuperarTabla() {
        TableModel tableModel = jTableInventario.getModel();
        int cols = tableModel.getColumnCount();
        int rows = tableModel.getRowCount();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(tableModel.getValueAt(i, j));
            }
            System.out.println();
        }

    }

    public void limpiarCampos() {
        jTextFieldCodigoPr.setText("");
        jTextFieldNombrePr.setText("");
        jComboBoxCategoriasPr.setSelectedIndex(0);
        jTextFieldMarcaP.setText("");
        jTextFieldCantidadPr.setText("");
        jTextFieldPrecioCompraPr.setText("");
        jTextFieldPrecioVentaPr.setText("");

    }

    public JFProductos() {
        initComponents();
        tablaConsulta();
        rutaImagen = "";
        jButtonGuardarP.setEnabled(false);
        jButtonCancelarP.setEnabled(false);

        setIconImage(new ImageIcon(getClass().getResource("../Images/blanco-logo.png")).getImage());

        ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/Images/LogoPrincipal.png"));
        Image fondoLogo = imagenLogo.getImage().getScaledInstance(jLabelLogoMenu.getWidth(), jLabelLogoMenu.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscaladoLogo = new ImageIcon(fondoLogo);
        jLabelLogoMenu.setIcon(iconoEscaladoLogo);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        /*Esta funcion permite centrar el JFrame*/

        //Imprimir en los textField el elemento Seleccionado de la lista
        jTableInventario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //Se obtiene la seleccion de una lista y se imprime en los jTextField
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {

                    if (jTableInventario.getSelectedRow() != -1) {
                        int fila = jTableInventario.getSelectedRow();

                        jTextFieldCodigoPr.setText(jTableInventario.getValueAt(fila, 0).toString());
                        jTextFieldNombrePr.setText(jTableInventario.getValueAt(fila, 1).toString());
                        jComboBoxCategoriasPr.setSelectedItem(jTableInventario.getValueAt(fila, 2).toString());
                        jTextFieldMarcaP.setText(jTableInventario.getValueAt(fila, 3).toString());
                        jTextFieldCantidadPr.setText(jTableInventario.getValueAt(fila, 4).toString());
                        jTextFieldPrecioVentaPr.setText(jTableInventario.getValueAt(fila, 6).toString());
                        jTextFieldPrecioCompraPr.setText(jTableInventario.getValueAt(fila, 5).toString());
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

        jPanelInterfaceProductos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldCodigoPr = new javax.swing.JTextField();
        jLabelCodigoPr = new javax.swing.JLabel();
        jLabelNombrePr = new javax.swing.JLabel();
        jTextFieldNombrePr = new javax.swing.JTextField();
        jComboBoxCategoriasPr = new javax.swing.JComboBox<>();
        jLabelCategoriaPr = new javax.swing.JLabel();
        jLabelCantidadPr = new javax.swing.JLabel();
        jTextFieldCantidadPr = new javax.swing.JTextField();
        jTextFieldPrecioCompraPr = new javax.swing.JTextField();
        jLabelPrecioVentaPr = new javax.swing.JLabel();
        jTextFieldPrecioVentaPr = new javax.swing.JTextField();
        jLabelPrecioCompraPr = new javax.swing.JLabel();
        jTextFieldMarcaP = new javax.swing.JTextField();
        jLabelMarca = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInventario = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonCancelarP = new javax.swing.JButton();
        jButtonNuevoP = new javax.swing.JButton();
        jButtonGuardarP = new javax.swing.JButton();
        jButtonActualizarP = new javax.swing.JButton();
        jButtonBorrarP = new javax.swing.JButton();
        JButtonBuscarPr = new javax.swing.JButton();
        jComboBoxTipoBusqPr = new javax.swing.JComboBox<>();
        jTextFieldBuscarPr = new javax.swing.JTextField();
        jPanelMenuSecundario = new javax.swing.JPanel();
        jLabelLogoMenu = new javax.swing.JLabel();
        jButtonVentas = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jButtonServicios = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        TIPODEUSUARIOPRODUCTOS = new javax.swing.JLabel();
        USUARIOPRODUCTOS = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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

        jPanelInterfaceProductos.setBackground(new java.awt.Color(0, 0, 0));
        jPanelInterfaceProductos.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanelInterfaceProductos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelInterfaceProductosPropertyChange(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        jTextFieldCodigoPr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldCodigoPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoPrActionPerformed(evt);
            }
        });
        jTextFieldCodigoPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoPrKeyTyped(evt);
            }
        });

        jLabelCodigoPr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelCodigoPr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCodigoPr.setText("  Codigo:");

        jLabelNombrePr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombrePr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombrePr.setText("Nombre:");

        jTextFieldNombrePr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldNombrePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombrePrActionPerformed(evt);
            }
        });
        jTextFieldNombrePr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombrePrKeyTyped(evt);
            }
        });

        jComboBoxCategoriasPr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxCategoriasPr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Venta Normal", "Mica de Vidrio ", "Funda", "Memoria", "Inversion" }));
        jComboBoxCategoriasPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriasPrActionPerformed(evt);
            }
        });

        jLabelCategoriaPr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelCategoriaPr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategoriaPr.setText("Categoria:");

        jLabelCantidadPr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelCantidadPr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCantidadPr.setText("Cantidad:");

        jTextFieldCantidadPr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldCantidadPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadPrActionPerformed(evt);
            }
        });
        jTextFieldCantidadPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCantidadPrKeyTyped(evt);
            }
        });

        jTextFieldPrecioCompraPr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldPrecioCompraPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioCompraPrActionPerformed(evt);
            }
        });
        jTextFieldPrecioCompraPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioCompraPrKeyTyped(evt);
            }
        });

        jLabelPrecioVentaPr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelPrecioVentaPr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPrecioVentaPr.setText("Precio Venta:");

        jTextFieldPrecioVentaPr.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldPrecioVentaPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioVentaPrActionPerformed(evt);
            }
        });
        jTextFieldPrecioVentaPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioVentaPrKeyTyped(evt);
            }
        });

        jLabelPrecioCompraPr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelPrecioCompraPr.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPrecioCompraPr.setText("Precio Compra:");

        jTextFieldMarcaP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldMarcaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcaPActionPerformed(evt);
            }
        });
        jTextFieldMarcaP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMarcaPKeyTyped(evt);
            }
        });

        jLabelMarca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelMarca.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMarca.setText("Marca:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNombrePr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCategoriaPr)
                    .addComponent(jLabelCantidadPr, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrecioCompraPr)
                    .addComponent(jLabelPrecioVentaPr)
                    .addComponent(jLabelCodigoPr)
                    .addComponent(jLabelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMarcaP)
                    .addComponent(jTextFieldCantidadPr)
                    .addComponent(jTextFieldPrecioCompraPr)
                    .addComponent(jTextFieldNombrePr)
                    .addComponent(jComboBoxCategoriasPr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCodigoPr)
                    .addComponent(jTextFieldPrecioVentaPr, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCodigoPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelCodigoPr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombrePr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombrePr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategoriasPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCategoriaPr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMarcaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCantidadPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCantidadPr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrecioCompraPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrecioCompraPr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrecioVentaPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrecioVentaPr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Inventario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        jTableInventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTableInventario.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Categoria", "Marca", "Cantidad", "Precio Compra", "Precio Venta"
            }
        ));
        jScrollPane1.setViewportView(jTableInventario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Edicion Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jButtonCancelarP.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonCancelarP.setText("Cancelar");
        jButtonCancelarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarPActionPerformed(evt);
            }
        });

        jButtonNuevoP.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonNuevoP.setText("Nuevo");
        jButtonNuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoPActionPerformed(evt);
            }
        });

        jButtonGuardarP.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonGuardarP.setText("Guardar");
        jButtonGuardarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarPActionPerformed(evt);
            }
        });

        jButtonActualizarP.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonActualizarP.setText("Actualizar");
        jButtonActualizarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarPActionPerformed(evt);
            }
        });

        jButtonBorrarP.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jButtonBorrarP.setText("Borrar");
        jButtonBorrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevoP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGuardarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActualizarP, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jButtonBorrarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jButtonNuevoP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonActualizarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBorrarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JButtonBuscarPr.setText("Buscar");
        JButtonBuscarPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonBuscarPrActionPerformed(evt);
            }
        });

        jComboBoxTipoBusqPr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Codigo", "Nombre", "Categoria", "Marca", "Cantidad", "Precio Compra", "Precio Venta" }));

        javax.swing.GroupLayout jPanelInterfaceProductosLayout = new javax.swing.GroupLayout(jPanelInterfaceProductos);
        jPanelInterfaceProductos.setLayout(jPanelInterfaceProductosLayout);
        jPanelInterfaceProductosLayout.setHorizontalGroup(
            jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterfaceProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelInterfaceProductosLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JButtonBuscarPr, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBoxTipoBusqPr, javax.swing.GroupLayout.Alignment.TRAILING, 0, 166, Short.MAX_VALUE)
                                .addComponent(jTextFieldBuscarPr, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap())
        );
        jPanelInterfaceProductosLayout.setVerticalGroup(
            jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterfaceProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelInterfaceProductosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanelInterfaceProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInterfaceProductosLayout.createSequentialGroup()
                                .addComponent(JButtonBuscarPr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxTipoBusqPr, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBuscarPr, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMenuSecundario.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuSecundario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMenuSecundario.setPreferredSize(new java.awt.Dimension(182, 554));
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
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonServicios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelMenuSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabelLogoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanelInterfaceProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuSecundario, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInterfaceProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCategoriasPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriasPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCategoriasPrActionPerformed

    private void jTextFieldPrecioCompraPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioCompraPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioCompraPrActionPerformed

    private void jTextFieldCantidadPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadPrActionPerformed

    private void jTextFieldNombrePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombrePrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombrePrActionPerformed

    private void jTextFieldCodigoPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoPrActionPerformed

    private void jPanelMenuSecundarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelMenuSecundarioPropertyChange
        ImagenMenuSecundario ImagenMenuSec = new ImagenMenuSecundario();
        jPanelMenuSecundario.add(ImagenMenuSec);
        jPanelMenuSecundario.repaint();
    }//GEN-LAST:event_jPanelMenuSecundarioPropertyChange

    private void jPanelInterfaceProductosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelInterfaceProductosPropertyChange
        Imagen Imagen = new Imagen();
        jPanelInterfaceProductos.add(Imagen);
        jPanelInterfaceProductos.repaint();
    }//GEN-LAST:event_jPanelInterfaceProductosPropertyChange

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        if (JFMenuPrincipal.JFventas == null) {
            JFVentas ventas = new JFVentas();
            ventas.setVisible(true);
            ventas.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
            ventas.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed

    }//GEN-LAST:event_jButtonProductosActionPerformed

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
        if (JFMenuPrincipal.JFclien == null) {
            JFClientes clien = new JFClientes();
            clien.setVisible(true);
            clien.USUARIOPRODUCTOS.setText(USUARIOPRODUCTOS.getText());
            clien.TIPODEUSUARIOPRODUCTOS.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jTextFieldPrecioVentaPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVentaPrActionPerformed
        // TODO add  your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioVentaPrActionPerformed

    private void jMenuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuInicioMouseClicked
        if (JFLogin.menu == null) {
            JFMenuPrincipal menu = new JFMenuPrincipal();
            menu.setVisible(true);
            menu.USUARIOMENU.setText(USUARIOPRODUCTOS.getText());
            menu.TIPOUSUARIOMENU.setText(TIPODEUSUARIOPRODUCTOS.getText());
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuInicioMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFMenuPrincipal.JFprod = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonGuardarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarPActionPerformed

        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea guardar este producto?", "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            if (jComboBoxCategoriasPr.getSelectedItem() == "Selecciona") {

                JOptionPane.showMessageDialog(null, "Selecciona la categoria del producto");
            } else {
                SqlProductos sqlPro = new SqlProductos();
                productos produ = new productos();
                try {

                    produ.setCodigo_prod(jTextFieldCodigoPr.getText());
                    produ.setNombre_prod(jTextFieldNombrePr.getText());
                    produ.setMarca_prod(jTextFieldMarcaP.getText());
                    produ.setCantidad_prod(Integer.parseInt(jTextFieldCantidadPr.getText()));
                    produ.setCategoria_prod(jComboBoxCategoriasPr.getSelectedItem().toString());
                    produ.setPrecio_venta_prod(Double.parseDouble(jTextFieldPrecioVentaPr.getText()));
                    produ.setPrecio_compra_prod(Double.parseDouble(jTextFieldPrecioCompraPr.getText()));
                    if (sqlPro.registrarProd(produ)) {
                        limpiarCampos();
                        tablaConsulta();

                        JOptionPane.showMessageDialog(null, "El registro fue guardado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error" + ex);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se guardo ningun producto");
        }

        /*
        
        } else {
            JOptionPane.showMessageDialog(null, "No se guardo ningun producto");
        }
        
         */
    }//GEN-LAST:event_jButtonGuardarPActionPerformed

    private void JButtonBuscarPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonBuscarPrActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTableInventario.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String buscarProducto = this.jTextFieldBuscarPr.getText();
            String tipobusq = null;

            int tipo = jComboBoxTipoBusqPr.getSelectedIndex();

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
                case 7:
                    tipobusq = "PRECIO_COMPRA_P";
                    break;
            }

            String sql = "SELECT CODIGO_P, NOMBRE_P, CATEGORIA_P, MARCA_P, CANTIDAD_P, PRECIO_COMPRA_P, PRECIO_VENTA_P FROM productos WHERE " + tipobusq + " LIKE '%" + buscarProducto + "%'";
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
            modelo.addColumn("Precio Compra");
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

        /*SqlProductos modSqlP = new SqlProductos();
        productos modP = new productos();
        try {
            //Para establecer el modelo al JTable
            String buscar = this.jTextFieldBuscarPr.getText();
            String tipobusq = null;

            int tipo = jComboBoxTipoBusqPr.getSelectedIndex();

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
                    tipobusq = "CANTIDAD_P";
                    break;
                case 5:
                    tipobusq = "CANTIDAD_P";
                    break;
                case 6:
                    tipobusq = "CANTIDAD_P";
                    break;
            }

            modP.setCategoria_prod(tipobusq);
            modP.setBusquedaProd(jTextFieldBuscarPr.getText());
            if (modSqlP.buscarProd(modP)) {
                
            } else {
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
         */
 /*DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Categoria");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio Venta");
            modelo.addColumn("Precio Compra");
            ArrayList<Object[]> datos = new ArrayList<Object[]>();
            datos = SqlProductos.buscarProd();
            for(int i=0;i<datos.size();i++){
                modelo.addRow(datos.get(i));
            }
            
            jTableInventario.setModel(modelo);*/
    }//GEN-LAST:event_JButtonBuscarPrActionPerformed

    private void jTextFieldPrecioCompraPrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioCompraPrKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && jTextFieldPrecioCompraPr.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPrecioCompraPrKeyTyped

    private void jTextFieldPrecioVentaPrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVentaPrKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && jTextFieldPrecioVentaPr.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPrecioVentaPrKeyTyped

    private void jTextFieldCantidadPrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCantidadPrKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        String Caracteres = jTextFieldCantidadPr.getText();
        if (Caracteres.length() > 10) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCantidadPrKeyTyped

    private void jButtonNuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoPActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "Agregar Nuevo Producto", "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            jButtonGuardarP.setEnabled(true);
            jButtonCancelarP.setEnabled(true);
            jButtonActualizarP.setEnabled(false);
            jButtonBorrarP.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonNuevoPActionPerformed

    private void jButtonCancelarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarPActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea cancelar el registro del producto?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            jButtonGuardarP.setEnabled(false);
            jButtonCancelarP.setEnabled(false);
            jButtonActualizarP.setEnabled(true);
            jButtonBorrarP.setEnabled(true);// TODO add your handling code here:
            limpiarCampos();
        }
    }//GEN-LAST:event_jButtonCancelarPActionPerformed

    private void jTextFieldCodigoPrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPrKeyTyped
        String Caracteres = jTextFieldCodigoPr.getText();
        if (Caracteres.length() > 12) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCodigoPrKeyTyped

    private void jTextFieldNombrePrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombrePrKeyTyped
        String Caracteres = jTextFieldNombrePr.getText();
        if (Caracteres.length() > 49) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNombrePrKeyTyped

    private void jTextFieldMarcaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcaPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcaPActionPerformed

    private void jTextFieldMarcaPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMarcaPKeyTyped
        String Caracteres = jTextFieldMarcaP.getText();
        if (Caracteres.length() > 29) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldMarcaPKeyTyped

    private void jButtonActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarPActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea modificar este producto?", "Confirmar actualizacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {

            if (jComboBoxCategoriasPr.getSelectedItem() == "Selecciona") {

                JOptionPane.showMessageDialog(null, "Selecciona la categoria del producto");
            } else {
                SqlProductos sqlPro = new SqlProductos();
                productos produ = new productos();
                try {

                    produ.setCodigo_prod(jTextFieldCodigoPr.getText());
                    produ.setNombre_prod(jTextFieldNombrePr.getText());
                    produ.setCategoria_prod(jComboBoxCategoriasPr.getSelectedItem().toString());
                    produ.setMarca_prod(jTextFieldMarcaP.getText());
                    produ.setCantidad_prod(Integer.parseInt(jTextFieldCantidadPr.getText()));
                    produ.setPrecio_venta_prod(Double.parseDouble(jTextFieldPrecioVentaPr.getText()));
                    produ.setPrecio_compra_prod(Double.parseDouble(jTextFieldPrecioCompraPr.getText()));
                    if (sqlPro.actualizarProd(produ)) {
                        tablaConsulta();
                        JOptionPane.showMessageDialog(null, "El producto fue actualizado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error" + ex);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "No se actualizo ningun producto");
        }
    }//GEN-LAST:event_jButtonActualizarPActionPerformed

    private void jButtonBorrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarPActionPerformed
        int cancelar = JOptionPane.showConfirmDialog(null, "¿Desea borrar este producto?", "Eliminar producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cancelar == 0) {
            SqlProductos sqlPro = new SqlProductos();
            productos produ = new productos();
            try {

                produ.setCodigo_prod(jTextFieldCodigoPr.getText());

                if (sqlPro.borrarProd(produ)) {
                    tablaConsulta();
                    JOptionPane.showMessageDialog(null, "El producto fue borrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al borrar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
            }

        } else {
        }
    }//GEN-LAST:event_jButtonBorrarPActionPerformed

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
            java.util.logging.Logger.getLogger(JFProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton JButtonBuscarPr;
    public javax.swing.JLabel TIPODEUSUARIOPRODUCTOS;
    public javax.swing.JLabel USUARIOPRODUCTOS;
    private javax.swing.JButton jButtonActualizarP;
    private javax.swing.JButton jButtonBorrarP;
    private javax.swing.JButton jButtonCancelarP;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonGuardarP;
    private javax.swing.JButton jButtonNuevoP;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonServicios;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JComboBox<String> jComboBoxCategoriasPr;
    private javax.swing.JComboBox<String> jComboBoxTipoBusqPr;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCantidadPr;
    private javax.swing.JLabel jLabelCategoriaPr;
    private javax.swing.JLabel jLabelCodigoPr;
    private javax.swing.JLabel jLabelLogoMenu;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelNombrePr;
    private javax.swing.JLabel jLabelPrecioCompraPr;
    private javax.swing.JLabel jLabelPrecioVentaPr;
    private javax.swing.JMenu jMenuAcercaDe;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelInterfaceProductos;
    private javax.swing.JPanel jPanelMenuSecundario;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableInventario;
    private javax.swing.JTextField jTextFieldBuscarPr;
    private javax.swing.JTextField jTextFieldCantidadPr;
    public javax.swing.JTextField jTextFieldCodigoPr;
    private javax.swing.JTextField jTextFieldMarcaP;
    private javax.swing.JTextField jTextFieldNombrePr;
    private javax.swing.JTextField jTextFieldPrecioCompraPr;
    private javax.swing.JTextField jTextFieldPrecioVentaPr;
    // End of variables declaration//GEN-END:variables

    private void setBorder(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFocusPainted(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
