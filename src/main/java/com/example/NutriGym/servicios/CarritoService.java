package com.example.NutriGym.servicios;

import java.util.List;

import com.example.NutriGym.dto.CarritoItemDTO;
import com.example.NutriGym.entidades.Carrito;

public interface CarritoService {

    Carrito agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad);

    void procesarCompra(Long usuarioId);

    List<CarritoItemDTO> obtenerCarritoPorUsuario(Long usuarioId);

    void eliminarProductoDelCarrito(Long usuarioId, Long productoId);
}
