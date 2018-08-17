/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norberto
 */
public class SqlAgregarVenta extends Conexion {

    public boolean agregarProductoTabla(agregarVenta agr) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection sqlCon = (Connection) getConexion();

        String sqlCPAV = "SELECT NOMBRE_P, CATEGORIA_P, PRECIO_VENTA_P, CANTIDAD_P FROM productos WHERE CODIGO_P = ?";
        try {
            ps = (PreparedStatement) sqlCon.prepareStatement(sqlCPAV);
            ps.setString(1, agr.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                agr.setNombre(rs.getString(1));
                agr.setCategoria(rs.getString(2));
                agr.setPrecio(rs.getDouble(3));
                agr.setCantidad(rs.getInt(4));
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

    public boolean registrarVenta(agregarVenta agVenta) {
        PreparedStatement ps = null; //variable de Statement 
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "INSERT INTO ventas (FOLIO_V, USERNAME_US, USERNAME_CL, FECHA_V, IMPORTE_V) VALUES (?,?,?,?,?)";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, agVenta.getFolio_ven());
            ps.setString(2, agVenta.getUsuario());
            ps.setString(3, agVenta.getCliente());
            ps.setString(4, agVenta.getFecha_ven());
            ps.setDouble(5, agVenta.getImporte());
            ps.executeUpdate();
            System.out.println(ps);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean registrarVentaProducto(agregarVenta agVentaP) {
        PreparedStatement ps = null; //variable de Statement 
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql2 = "INSERT INTO ventas_producto (FOLIO_V, CODIGO_P, PRECIO_VP, SUBTOTAL_VP, CANTIDAD_VP) VALUES(?,?,?,?,?)";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql2);
            ps.setString(1, agVentaP.getFolio_ven());
            ps.setString(2, agVentaP.getCodigo());
            ps.setDouble(3, agVentaP.getPrecio());
            ps.setDouble(4, agVentaP.getImporte_ven_p());
            ps.setInt(5, agVentaP.getCantidad());
            ps.executeUpdate();
            System.out.println(ps);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean descontarProducto(agregarVenta desconProd) { //Se valida si un Usuario existe para negar el registro

        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT * FROM productos WHERE CODIGO_P = ?";
        try {
           ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, desconProd.getCodigo());
            rs = ps.executeQuery();
            System.out.println(rs);

            if (rs.next()) {

                String menos = rs.getString(5);
                Integer resta = Integer.parseInt(menos);
                Integer resultResta = (resta - desconProd.getCantidad());
                String sqlPro = "UPDATE productos SET CANTIDAD_P=? WHERE CODIGO_P = ?";
                try {
                    ps = (PreparedStatement) con.prepareStatement(sqlPro);
                    ps.setInt(1, resultResta);
                    ps.setString(2, desconProd.getCodigo());
                    ps.executeUpdate();
                     System.out.println(ps);
                     System.out.println(resultResta);
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error" + ex);
                    return false;
                }
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }

    }

}
