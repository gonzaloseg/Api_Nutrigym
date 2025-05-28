package com.example.NutriGym.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutriGym.entidades.Favoritos;

@Repository
public interface FavoritoRepositorio extends JpaRepository<Favoritos, Long> {
    List<Favoritos> findByUsuarioId(Long usuarioId);

    Optional<Favoritos> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
}
