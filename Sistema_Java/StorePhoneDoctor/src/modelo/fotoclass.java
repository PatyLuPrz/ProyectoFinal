/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author Norberto
 */
public class fotoclass extends modelo.Conexion {

    Conexion con = new Conexion();
    Connection conex = con.getConexion();
    private Image data;

    /*

    //metodo que dada una cadena de bytes la convierte en una imagen con extension jpeg
    private Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

    //METODO PARA RECUPERAR LA IMAGEN DE LA BASE DE DATOS
    public Image recuperarfotos(String usuario) throws SQLException {
        JFProductos imgprod = new JFProductos();
        ListSelectionEvent e = new ListSelectionEvent(usuario, 6, 6, true);
        try {
            PreparedStatement pstm = con.getConexion().prepareStatement("SELECT FOTO_P FROM productos WHERE CODIGO_P = ? ");
            pstm.setString(1, usuario);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                // se lee la cadena de bytes de la base de datos
                byte[] b = res.getBytes("foto");
                //esta cadena de bytes sera convertida en una imagen
                data = ConvertirImagen(b);
                i++;
            }
            res.close();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(modelo.fotoclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }
    public static ImageIcon getFoto(String codigo) {
        String sql = "SELECT FOTO_P FROM productos WHERE CODIGO_P = ?";
        ImageIcon ii = null;
        InputStream is = null;

        try {
            st = conex.createStatement();
            rs = st.executeQuery(sql);
            
            if(rs.next()){
                is = rs.getBinaryStream(1);
                BufferedImage bi = ImageIO.read(is);
                ii = new ImageIcon(bi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            {
                return ii;
            }*/
        }
