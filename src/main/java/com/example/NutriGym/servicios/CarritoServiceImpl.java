package com.example.NutriGym.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.NutriGym.dto.CarritoItemDTO;
import com.example.NutriGym.entidades.Carrito;
import com.example.NutriGym.entidades.Productos;
import com.example.NutriGym.entidades.Usuarios;
import com.example.NutriGym.repositorios.CarritoRepository;
import com.example.NutriGym.repositorios.ProductosRepositorio;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductosRepositorio productosRepository;

    public CarritoServiceImpl(CarritoRepository carritoRepository, ProductosRepositorio productosRepositorio) {
        this.carritoRepository = carritoRepository;
        this.productosRepository = productosRepositorio;
    }

    @Override
    @Transactional
    public Carrito agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad) {

        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<Carrito> existente = carritoRepository.findByUsuarioId(usuarioId).stream()
                .filter(item -> item.getProducto().getId().equals(productoId))
                .findFirst();

        Carrito item;
        int cantidadActual = existente.map(Carrito::getCantidad).orElse(0);
        int nuevaCantidad = cantidadActual + cantidad;

        if (nuevaCantidad > producto.getStock()) {
            throw new RuntimeException("No puedes agregar más unidades que las disponibles en stock");
        }

        if (nuevaCantidad < 1) {
            // Si la cantidad llega a cero o menos, eliminamos el producto del carrito
            existente.ifPresent(carritoRepository::delete);
            throw new RuntimeException("Producto eliminado del carrito por cantidad <= 0");
        }

        if (existente.isPresent()) {
            item = existente.get();
            item.setCantidad(nuevaCantidad);
        } else {
            item = new Carrito();
            Usuarios usuario = new Usuarios();
            usuario.setId(usuarioId); // solo se asigna el ID, sin buscarlo
            item.setUsuario(usuario);
            item.setProducto(producto);
            item.setCantidad(cantidad);
        }

        return carritoRepository.save(item);
    }

    @Override
    public void procesarCompra(Long usuarioId) {
        List<Carrito> items = carritoRepository.findByUsuarioId(usuarioId);

        for (Carrito item : items) {
            Productos producto = item.getProducto();
            int nuevaCantidad = producto.getStock() - item.getCantidad();

            if (nuevaCantidad < 0) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(nuevaCantidad);
            productosRepository.save(producto);
        }

        carritoRepository.deleteAll(items);
    }

    @Override
    public List<CarritoItemDTO> obtenerCarritoPorUsuario(Long usuarioId) {
        List<Carrito> carrito = carritoRepository.findByUsuarioId(usuarioId);

        return carrito.stream()
                .map(c -> new CarritoItemDTO(c.getProducto(), c.getCantidad()))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProductoDelCarrito(Long usuarioId, Long productoId) {
        Optional<Carrito> carritoOptional = carritoRepository.findByUsuarioIdAndProductoId(usuarioId, productoId);
        carritoOptional.ifPresent(carritoRepository::delete);
    }
}
