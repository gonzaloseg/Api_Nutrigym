package com.example.NutriGym.servicios;

import java.util.List;

import com.example.NutriGym.entidades.Carrito;

public interface CarritoService {

    Carrito agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad);

    void procesarCompra(Long usuarioId);

    List<Carrito> obtenerCarritoPorUsuario(Long usuarioId);
}