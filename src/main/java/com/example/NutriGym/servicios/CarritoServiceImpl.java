package com.example.NutriGym.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public Carrito agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad) {
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        // Aquí puedes buscar si ya hay un ítem con ese producto para ese usuario
        Optional<Carrito> existente = carritoRepository.findByUsuarioId(usuarioId).stream()
                .filter(item -> item.getProducto().getId().equals(productoId))
                .findFirst();

        Carrito item;
        if (existente.isPresent()) {
            item = existente.get();
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            item = new Carrito();
            Usuarios usuario = new Usuarios();
            usuario.setId(usuarioId); // Solo seteamos el ID, evitamos hacer otra query
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
    public List<Carrito> obtenerCarritoPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
        }

}