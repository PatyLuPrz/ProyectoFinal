
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
public class SqlUsuarios extends Conexion {

    public boolean registrarUsers(usuarios usr) { //Metodo para registrar un usuario
        PreparedStatement ps = null; //variable de Statement 
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sqlPro = "INSERT INTO usuarios (USERNAME_US, EMAIL_US, CONTRASENA_US, NOMBRE_US, AP_US, AM_US, TIPO_US) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = (PreparedStatement) con.prepareStatement(sqlPro);
            ps.setString(1, usr.getUsername());
            ps.setString(2, usr.getEmail_user());
            ps.setString(3, usr.getContrasena_user());
            ps.setString(4, usr.getNombre_user());
            ps.setString(5, usr.getAp_user());
            ps.setString(6, usr.getAm_user());
            ps.setString(7, usr.getTipo_user());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }

    }

    public int existeUsuario(String usuario) { //Se valida si un Usuario existe para negar el registro
        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT count(USERNAME_US) FROM usuarios WHERE USERNAME_US = ?";
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usuario);
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

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        //Se indica con que caracteres debe iniciar el String o la Cadena
        Matcher mather = pattern.matcher(correo);

        return mather.find();//Regresa el valor final si con los parametros que se le indican

    }
}
