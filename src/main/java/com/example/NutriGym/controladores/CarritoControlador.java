package com.example.NutriGym.controladores;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NutriGym.dto.AgregarProductoDTO;
import com.example.NutriGym.entidades.Carrito;
import com.example.NutriGym.servicios.CarritoService;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para Angular)
public class CarritoControlador {

    private final CarritoService carritoService;

    public CarritoControlador(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProducto(@RequestBody AgregarProductoDTO dto) {
        System.out.println("Ha entrado en agregar Producto al carrito");
        Carrito item = carritoService.agregarProductoAlCarrito(dto.getUsuarioId(), dto.getProductoId(),
                dto.getCantidad());
        return ResponseEntity.ok(item);
    }

    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestParam Long usuarioId) {
        carritoService.procesarCompra(usuarioId);
        return ResponseEntity.ok(Map.of("mensaje", "Compra realizada exitosamente"));
    }

    @GetMapping
    public ResponseEntity<?> verCarrito(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoPorUsuario(usuarioId));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarProducto(@RequestParam Long usuarioId, @RequestParam Long productoId) {
        carritoService.eliminarProductoDelCarrito(usuarioId, productoId);
        return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado del carrito"));
    }
}
