package com.example.NutriGym.servicios;

import java.util.List;

import com.example.NutriGym.dto.FavoritoDTO;
import com.example.NutriGym.entidades.Favoritos;

public interface FavoritoService {

    Favoritos agregarAFavoritos(Long usuarioId, Long productoId);

    void eliminarDeFavoritos(Long usuarioId, Long productoId);

    List<FavoritoDTO> obtenerFavoritosPorUsuario(Long usuarioId);
}
