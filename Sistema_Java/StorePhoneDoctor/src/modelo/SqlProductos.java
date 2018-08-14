package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norberto
 */
public class SqlProductos extends Conexion {

    public boolean registrarProd(productos pro) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "INSERT INTO productos (CODIGO_P, NOMBRE_P, CATEGORIA_P, MARCA_P, CANTIDAD_P, PRECIO_VENTA_P, PRECIO_COMPRA_P) VALUES(?,?,?,?,?,?,?)";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo_prod());
            ps.setString(2, pro.getNombre_prod());
            ps.setString(3, pro.getCategoria_prod());
            ps.setString(4, pro.getMarca_prod());
            ps.setInt(5, pro.getCantidad_prod());
            ps.setDouble(6, pro.getPrecio_venta_prod());
            ps.setDouble(7, pro.getPrecio_compra_prod());
            ps.executeUpdate();
            System.out.println(ps);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean actualizarProd(productos pro) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "UPDATE productos SET NOMBRE_P=?, CATEGORIA_P=?, MARCA_P=?, CANTIDAD_P=?, PRECIO_VENTA_P=?, PRECIO_COMPRA_P=? WHERE CODIGO_P = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, pro.getNombre_prod());
            ps.setString(2, pro.getCategoria_prod());
            ps.setString(3, pro.getMarca_prod());
            ps.setInt(4, pro.getCantidad_prod());
            ps.setDouble(5, pro.getPrecio_venta_prod());
            ps.setDouble(6, pro.getPrecio_compra_prod());
            ps.setString(7, pro.getCodigo_prod());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public int existeProducto(String prd) { //Se valida si un Usuario existe para negar el registro
        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT count(CODIGO_P) FROM productos WHERE CODIGO_P = ?";
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, prd);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return 1;
        }
    }

    public boolean borrarProd(productos produ) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "DELETE FROM productos WHERE CODIGO_P = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, produ.getCodigo_prod());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }
}
