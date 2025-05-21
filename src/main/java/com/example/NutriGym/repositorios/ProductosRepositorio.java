package com.example.NutriGym.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NutriGym.entidades.Productos;


public interface ProductosRepositorio  extends JpaRepository<Productos, Long>{

}
