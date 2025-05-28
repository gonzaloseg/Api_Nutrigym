package com.example.NutriGym.dto;

import com.example.NutriGym.entidades.Productos;

public class CarritoItemDTO {

    private Long productoId;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer cantidad;
    private Integer stock;

    public CarritoItemDTO(Productos producto, Integer cantidad) {
        this.productoId = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.imagen = producto.getImagen();
        this.precio = producto.getPrecio();
        this.cantidad = cantidad;
        this.stock = producto.getStock();
    }

    public CarritoItemDTO() {
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
