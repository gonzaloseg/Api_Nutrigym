package com.example.NutriGym.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutriGym.entidades.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioId(Long usuarioId);

    Optional<Carrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void delete(Carrito carrito);
}
