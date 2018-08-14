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
public class productos {
    private String codigo_prod;
    private String nombre_prod;
    private String categoria_prod;
    private String marca_prod;

    public String getMarca_prod() {
        return marca_prod;
    }

    public void setMarca_prod(String marca_prod) {
        this.marca_prod = marca_prod;
    }
    private Integer cantidad_prod;
    private Double precio_venta_prod;
    private Double precio_compra_prod;
    private String busquedaProd;
    private String foto_prod;

    public String getFoto_prod() {
        return foto_prod;
    }


    public String getBusquedaProd() {
        return busquedaProd;
    }

    public void setBusquedaProd(String busquedaProd) {
        this.busquedaProd = busquedaProd;
    }

    public String getCodigo_prod() {
        return codigo_prod;
    }

    public void setCodigo_prod(String codigo_prod) {
        this.codigo_prod = codigo_prod;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public String getCategoria_prod() {
        return categoria_prod;
    }

    public void setCategoria_prod(String categoria_prod) {
        this.categoria_prod = categoria_prod;
    }

    public Integer getCantidad_prod() {
        return cantidad_prod;
    }

    public void setCantidad_prod(Integer cantidad_prod) {
        this.cantidad_prod = cantidad_prod;
    }

    public Double getPrecio_venta_prod() {
        return precio_venta_prod;
    }

    public void setPrecio_venta_prod(Double precio_venta_prod) {
        this.precio_venta_prod = precio_venta_prod;
    }

    public Double getPrecio_compra_prod() {
        return precio_compra_prod;
    }

    public void setPrecio_compra_prod(Double precio_compra_prod) {
        this.precio_compra_prod = precio_compra_prod;
    }

    
    
    
}
