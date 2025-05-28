package com.example.NutriGym.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> agregarProducto(@RequestParam Long usuarioId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {
        Carrito item = carritoService.agregarProductoAlCarrito(usuarioId, productoId, cantidad);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestParam Long usuarioId) {
        carritoService.procesarCompra(usuarioId);
        return ResponseEntity.ok("Compra realizada exitosamente");
    }

    @GetMapping
    public ResponseEntity<?> verCarrito(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoPorUsuario(usuarioId));
        }
}