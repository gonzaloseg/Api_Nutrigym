package com.example.NutriGym.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutriGym.entidades.Usuarios;
import com.example.NutriGym.servicios.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para Angular)
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuarios> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuarios> obtenerUsuarioPorcorreoElectronico(@RequestBody Usuarios usuario) {
        Optional<Usuarios> usuarioobtenido = usuarioService.obtenerUsuarioPorcorreoElectronico(usuario.getCorreoElectronico());
        if(usuarioobtenido.isPresent() && usuarioobtenido.get().getContrasena().equals(usuario.getContrasena())) {
        return usuarioobtenido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }else {
        	 return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Usuarios crearUsuario(@RequestBody Usuarios usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetalles) {
        Optional<Usuarios> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();
            usuario.setNombre(usuarioDetalles.getNombre());
            usuario.setFoto(usuarioDetalles.getFoto());
            usuario.setCorreoElectronico(usuarioDetalles.getCorreoElectronico());
            usuario.setContrasena(usuarioDetalles.getContrasena());
            usuario.setAltura(usuarioDetalles.getAltura());
            usuario.setPeso(usuarioDetalles.getPeso());
            usuario.setRol_usuario(usuarioDetalles.getRol_usuario());

            return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public Usuarios obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario no encontrado"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}