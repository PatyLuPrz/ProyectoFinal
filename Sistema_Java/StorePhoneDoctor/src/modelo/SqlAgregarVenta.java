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

/**
 *
 * @author Norberto
 */
public class SqlAgregarVenta extends Conexion {

    public boolean agregarProductoTabla(agregarVenta agr) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection sqlCon = (Connection) getConexion();

        String sqlCPAV = "SELECT NOMBRE_P, CATEGORIA_P, PRECIO_VENTA_P FROM productos WHERE CODIGO_P = ?";
        try {
            ps = (PreparedStatement) sqlCon.prepareStatement(sqlCPAV);
            ps.setString(1, agr.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                agr.setNombre(rs.getString(1));
                agr.setCategoria(rs.getString(2));
                agr.setPrecio(rs.getDouble(3));
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

}
