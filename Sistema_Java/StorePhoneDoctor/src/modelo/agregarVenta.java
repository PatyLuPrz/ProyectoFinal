/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Norberto
 */
public class agregarVenta {

    String codigo; // COdigo Producto
    String folio_ven; //Folio de la venta
    String fecha_ven; //Fecha de la venta
    String nombre; //Producto
    String categoria; //Categoria de producto
    Integer cantidad; //Cantidad de productos en stock
    String usuario; //Usuario que realiza la venta
    String cliente; //Cliente o Publico general
    Double precio; // Precio original de la tabla productos
    Double importe; //importe total de venta
    Double importe_ven_p;

    public Double getImporte_ven_p() {
        return importe_ven_p;
    }

    public void setImporte_ven_p(Double importe_ven_p) {
        this.importe_ven_p = importe_ven_p;
    }

    public String getFolio_ven() {
        return folio_ven;
    }

    public void setFolio_ven(String folio_ven) {
        this.folio_ven = folio_ven;
    }

    public String getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(String fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
