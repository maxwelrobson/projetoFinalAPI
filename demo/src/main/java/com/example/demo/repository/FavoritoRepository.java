package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Favorito;
import com.example.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    Favorito findFavoritoByClienteAndProduto(Usuario usuario, Produto produto);
    List<Favorito> findAllByCliente(Usuario usuario);
    Optional<Favorito> findByClienteAndProduto(Usuario usuario, Produto produto);
}
