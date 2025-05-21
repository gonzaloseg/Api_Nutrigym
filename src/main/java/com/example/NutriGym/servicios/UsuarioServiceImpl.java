package com.example.NutriGym.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NutriGym.entidades.Usuarios;
import com.example.NutriGym.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServiceImpl  implements UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuarios> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuarios> obtenerUsuarioPorcorreoElectronico(String correoElectronico) {
        return usuarioRepositorio.findByCorreoElectronico(correoElectronico);
    }

    @Override
    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

	@Override
	public Optional<Usuarios> obtenerUsuarioPorId(Long id) {
		  return usuarioRepositorio.findById(id);
	}

}