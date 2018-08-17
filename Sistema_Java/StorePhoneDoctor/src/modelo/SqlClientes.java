package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author beto
 */
public class SqlClientes extends Conexion {

    public boolean registrarClientes(clientes clien) { //Metodo para registrar un usuario
        PreparedStatement ps = null; //variable de Statement 
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sqlClien = "INSERT INTO clientes (USERNAME_CL, EMAIL_CL, CONTRASENA_CL, NOMBRE_CL, AP_CL, AM_CL, TEL_CL, MUN_CL) VALUES(?,?,?,?,?,?,?,?)";
        try {
            ps = (PreparedStatement) con.prepareStatement(sqlClien);
            ps.setString(1, clien.getUsername_Clien());
            ps.setString(2, clien.getEmail_Clien());
            ps.setString(3, clien.getContrasena_Clien());
            ps.setString(4, clien.getNombre_Clien());
            ps.setString(5, clien.getAp_Clien());
            ps.setString(6, clien.getAm_Clien());
            ps.setString(7, clien.getTel_Clien());
            ps.setString(8, clien.getMun_Clien());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }

    }

    public boolean login(usuarios usr) { //Se valida si un Usuario existe para negar el registro

        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT USERNAME_US, EMAIL_US, CONTRASENA_US, NOMBRE_US, TIPO_US FROM usuarios WHERE USERNAME_US = ?";
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usr.getUsername());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getContrasena_user().equals(rs.getString(3))) {
                    usr.setUsername(rs.getString(1));
                    usr.setNombre_user(rs.getString(4));
                    usr.setTipo_user(rs.getString(5));
                    return true;
                } else {
                    return false;
                }
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(SqlClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }

    }

    public int existeCliente(String clientes) { //Se valida si un Cliente existe para negar el registro
        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT count(USERNAME_CL) FROM clientes WHERE USERNAME_CL = ?";
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, clientes);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(SqlClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return 1;
        }

    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        //Se indica con que caracteres debe iniciar el String o la Cadena
        Matcher mather = pattern.matcher(correo);

        return mather.find();//Regresa el valor final si con los parametros que se le indican

    }

    public boolean actualizarClien(clientes cli) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "UPDATE clientes SET EMAIL_CL=?, NOMBRE_CL=?, AP_CL=?, AM_CL=?, TEL_CL=?, MUN_CL=? WHERE USERNAME_CL = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, cli.getEmail_Clien());
            ps.setString(2, cli.getNombre_Clien());
            ps.setString(3, cli.getAp_Clien());
            ps.setString(4, cli.getAm_Clien());
            ps.setString(5, cli.getTel_Clien());
            ps.setString(6, cli.getMun_Clien());
            ps.setString(7, cli.getUsername_Clien());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

    public boolean borrarClien(clientes clients) {
        PreparedStatement ps = null; //variable de Statement
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql = "DELETE FROM productos WHERE CODIGO_P = ?";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, clients.getUsername_Clien());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

}
