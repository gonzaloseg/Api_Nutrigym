package com.example.NutriGym.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NutriGym.entidades.Usuarios;


public interface UsuarioRepositorio  extends JpaRepository<Usuarios, Long>{

	Optional<Usuarios> findByCorreoElectronico(String correoElectronico);
}
