package com.example.NutriGym.servicios;


import com.example.NutriGym.entidades.Usuarios; 
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuarios> listarUsuarios();
    Optional<Usuarios> obtenerUsuarioPorId(Long id);
    Optional<Usuarios> obtenerUsuarioPorcorreoElectronico(String correoElectronico);
    Usuarios guardarUsuario(Usuarios usuario);
    void eliminarUsuario(Long id);
    
}