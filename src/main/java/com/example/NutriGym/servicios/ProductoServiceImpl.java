package com.example.NutriGym.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NutriGym.entidades.Productos;
import com.example.NutriGym.repositorios.ProductosRepositorio;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductosRepositorio productoRepositorio;

    @Override
    public List<Productos> listarProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Optional<Productos> obtenerProductoPorId(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public Productos guardarProducto(Productos producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id);
    }
}