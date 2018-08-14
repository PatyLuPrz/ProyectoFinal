/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Norberto
 */
public class servicios {

    private String folio_serv;
    private String username_serv_users;
    private String username_serv_clien;
    private String fecha_llegada_serv;

    public String getFecha_llegada_serv() {
        return fecha_llegada_serv;
    }

    public void setFecha_llegada_serv(String fecha_llegada_serv) {
        this.fecha_llegada_serv = fecha_llegada_serv;
    }

    public String getFecha_salida_serv() {
        return fecha_salida_serv;
    }

    public void setFecha_salida_serv(String fecha_salida_serv) {
        this.fecha_salida_serv = fecha_salida_serv;
    }
    private String fecha_salida_serv;
    private String nombre_cliente_serv;
    private String telefono_cliente_serv;
    private String descripcion_serv;
    private Double precio_serv;

    public Double getPrecio_serv() {
        return precio_serv;
    }

    public void setPrecio_serv(Double precio_serv) {
        this.precio_serv = precio_serv;
    }

    public String getFolio_serv() {
        return folio_serv;
    }

    public void setFolio_serv(String folio_serv) {
        this.folio_serv = folio_serv;
    }

    public String getUsername_serv_users() {
        return username_serv_users;
    }

    public void setUsername_serv_users(String username_serv_users) {
        this.username_serv_users = username_serv_users;
    }

    public String getUsername_serv_clien() {
        return username_serv_clien;
    }

    public void setUsername_serv_clien(String username_serv_clien) {
        this.username_serv_clien = username_serv_clien;
    }

    public String getNombre_cliente_serv() {
        return nombre_cliente_serv;
    }

    public void setNombre_cliente_serv(String nombre_cliente_serv) {
        this.nombre_cliente_serv = nombre_cliente_serv;
    }

    public String getTelefono_cliente_serv() {
        return telefono_cliente_serv;
    }

    public void setTelefono_cliente_serv(String telefono_cliente_serv) {
        this.telefono_cliente_serv = telefono_cliente_serv;
    }

    public String getDescripcion_serv() {
        return descripcion_serv;
    }

    public void setDescripcion_serv(String descripcion_serv) {
        this.descripcion_serv = descripcion_serv;
    }

    
}
