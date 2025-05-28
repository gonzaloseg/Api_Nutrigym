package com.example.NutriGym.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NutriGym.entidades.Productos;
import com.example.NutriGym.servicios.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para Angular)
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Productos> listarProductos(@RequestParam(required = false) String categoria) {
        if (categoria != null) {
            return productoService.listarProductos().stream().filter(producto -> producto.getCategoria().equals(categoria)).toList();
        }
        return productoService.listarProductos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Productos> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Productos crearProducto(@RequestBody Productos producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @RequestBody Productos productoDetalles) {
        Optional<Productos> productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente.isPresent()) {
            Productos producto = productoExistente.get();
            producto.setNombre(productoDetalles.getNombre());
            producto.setStock(productoDetalles.getStock());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setCategoria(productoDetalles.getCategoria());
            producto.setDescripcion(productoDetalles.getDescripcion());
            producto.setImagen(productoDetalles.getImagen());
            producto.setImagen2(productoDetalles.getImagen2());
            producto.setImagen3(productoDetalles.getImagen3());

            return ResponseEntity.ok(productoService.guardarProducto(producto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.obtenerProductoPorId(id).isPresent()) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}