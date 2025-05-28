package com.example.NutriGym.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NutriGym.dto.FavoritoDTO;
import com.example.NutriGym.servicios.FavoritoService;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoControlador {

    private final FavoritoService favoritoService;

    public FavoritoControlador(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<?> agregarFavorito(@RequestParam Long usuarioId, @RequestParam Long productoId) {
        return ResponseEntity.ok(favoritoService.agregarAFavoritos(usuarioId, productoId));
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarFavorito(@RequestParam Long usuarioId, @RequestParam Long productoId) {
        favoritoService.eliminarDeFavoritos(usuarioId, productoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FavoritoDTO>> obtenerFavoritos(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(favoritoService.obtenerFavoritosPorUsuario(usuarioId));
    }
}
