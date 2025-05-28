package com.example.NutriGym.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.NutriGym.dto.FavoritoDTO;
import com.example.NutriGym.entidades.Favoritos;
import com.example.NutriGym.entidades.Productos;
import com.example.NutriGym.entidades.Usuarios;
import com.example.NutriGym.repositorios.FavoritoRepositorio;
import com.example.NutriGym.repositorios.ProductosRepositorio;
import com.example.NutriGym.repositorios.UsuarioRepositorio;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepositorio favoritoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ProductosRepositorio productoRepositorio;

    public FavoritoServiceImpl(FavoritoRepositorio favoritoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            ProductosRepositorio productoRepositorio) {
        this.favoritoRepositorio = favoritoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public Favoritos agregarAFavoritos(Long usuarioId, Long productoId) {
        Usuarios usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Productos producto = productoRepositorio.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Evitar duplicados
        favoritoRepositorio.findByUsuarioIdAndProductoId(usuarioId, productoId)
                .ifPresent(fav -> {
                    throw new RuntimeException("Ya está en favoritos");
                });

        Favoritos favorito = new Favoritos(usuario, producto);
        return favoritoRepositorio.save(favorito);
    }

    @Override
    @Transactional
    public void eliminarDeFavoritos(Long usuarioId, Long productoId) {
        favoritoRepositorio.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    @Override
    public List<FavoritoDTO> obtenerFavoritosPorUsuario(Long usuarioId) {
        List<Favoritos> favoritos = favoritoRepositorio.findByUsuarioId(usuarioId);
        return favoritos.stream()
                .map(fav -> new FavoritoDTO(
                        fav.getProducto().getId(),
                        fav.getProducto().getNombre(),
                        fav.getProducto().getImagen(),
                        fav.getProducto().getPrecio(),
                        fav.getProducto().getDescripcion(),
                        fav.getProducto().getCategoria()))
                .toList();
    }
}
