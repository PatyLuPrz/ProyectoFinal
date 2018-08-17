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
public class SqlGarantias extends Conexion {

    public boolean descontarProductoG(garantias descoGar) { //Se valida si un Usuario existe para negar el registro

        PreparedStatement ps = null; //variable de Statement
        ResultSet rs = null;
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase

        String sql = "SELECT * FROM productos WHERE CODIGO_P = ?";
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, descoGar.getCdoigo_gar_p());
            rs = ps.executeQuery();
            System.out.println(rs);

            if (rs.next()) {

                String menos = rs.getString(5);
                Integer resta = Integer.parseInt(menos);
                Integer resultResta = (resta - descoGar.getCanditdad_gar_p());
                String sqlPro = "UPDATE productos SET CANTIDAD_P=? WHERE CODIGO_P = ?";
                try {
                    ps = (PreparedStatement) con.prepareStatement(sqlPro);
                    ps.setInt(1, resultResta);
                    ps.setString(2, descoGar.getCdoigo_gar_p());
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

    public boolean registrarGarProducto(garantias agreGar) {
        PreparedStatement ps = null; //variable de Statement 
        Connection con = (Connection) getConexion(); //Se obtiene la conexion para la clase
        String sql2 = "INSERT INTO garantias (FOLIO_G, CODIGO_P, FECHA_G, CANTIDAD_PG, UNSERNAME_US, DESCRIPCION_G) VALUES(?,?,?,?,?)";
        try {

            ps = (PreparedStatement) con.prepareStatement(sql2);
            ps.setString(1, agreGar.getCdoigo_gar_p());
            ps.setString(2, agreGar.getFecha_gar());
            ps.setInt(3, agreGar.getCanditdad_gar_p());
            ps.setString(4, agreGar.getUsername_gar_u());
            ps.setString(5, agreGar.getDescrip_g());
            ps.executeUpdate();
            System.out.println(ps);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error" + ex);
            return false;
        }
    }

}
