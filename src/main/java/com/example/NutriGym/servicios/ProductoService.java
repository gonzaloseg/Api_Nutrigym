package com.example.NutriGym.servicios;

import java.util.List;
import java.util.Optional;

import com.example.NutriGym.entidades.Productos;

public interface ProductoService {
	
    List<Productos> listarProductos();
    Optional<Productos> obtenerProductoPorId(Long id);
    Productos guardarProducto(Productos producto);
    void eliminarProducto(Long id);
}
