package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norberto
 */
public class sqlServicios extends Conexion {

    public boolean registrarServicio(servicios ser) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "INSERT INTO servicios (FOLIO_S, USERNAME_US, USERNAME_CL, FECHALLEGADA_S, FECHASALIDA_S, NOMBRECLIENTE_S, TELEFONOCLIENTE_S, DESCRIPCION_S, PRECIO_S) VALUES(?,?,?,?,?,?,?,?,?)";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, ser.getFolio_serv());
            ps.setString(2, ser.getUsername_serv_users());
            ps.setString(3, ser.getUsername_serv_clien());
            ps.setString(4, ser.getFecha_llegada_serv());
            ps.setString(5, ser.getFecha_salida_serv());
            ps.setString(6, ser.getNombre_cliente_serv());
            ps.setString(7, ser.getTelefono_cliente_serv());
            ps.setString(8, ser.getDescripcion_serv());
            ps.setDouble(9, ser.getPrecio_serv());
            ps.executeUpdate();
            System.out.println(ps);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean actualizarServicio(servicios serv) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "UPDATE servicios SET USERNAME_US=?, USERNAME_CL=?, NOMBRECLIENTE_S=?, FECHASALIDA_S=?, TELEFONOCLIENTE_S=?, DESCRIPCION_S=?, PRECIO_S=? WHERE FOLIO_S = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, serv.getUsername_serv_users());
            ps.setString(2, serv.getUsername_serv_clien());
            ps.setString(3, serv.getNombre_cliente_serv());
            ps.setString(4, serv.getFecha_salida_serv());
            ps.setString(5, serv.getTelefono_cliente_serv());
            ps.setString(6, serv.getDescripcion_serv());
            ps.setDouble(7, serv.getPrecio_serv());
            ps.setString(8, serv.getFolio_serv());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean borrarServ(servicios sers) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "DELETE FROM servicios WHERE FOLIO_S = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, sers.getFolio_serv());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

}
