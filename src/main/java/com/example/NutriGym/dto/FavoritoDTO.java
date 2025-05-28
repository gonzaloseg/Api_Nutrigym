package com.example.NutriGym.dto;

public class FavoritoDTO {
    private Long productoId;
    private String nombre;
    private String imagen;
    private Double precio;
    private String descripcion;
    private String categoria;

    public FavoritoDTO(Long productoId, String nombre, String imagen, Double precio, String descripcion,
            String categoria) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public FavoritoDTO() {
    }

    // Getters y setters

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
