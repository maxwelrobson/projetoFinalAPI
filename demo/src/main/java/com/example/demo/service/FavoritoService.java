package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Favorito;
import com.example.demo.entity.Produto;
import com.example.demo.exception.FavoritoException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.FavoritoRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;



    public ResponseEntity<String> adicionarFavorito(Long clienteId, Long produtoId) {

        Optional<Usuario> clienteOptional = clienteRepository.findById(clienteId);
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (!clienteOptional.isPresent() || !produtoOptional.isPresent()) {
            throw new FavoritoException("Usuario ou Produto encontrado");
        }
        Usuario usuario = clienteOptional.get();
        Produto produto = produtoOptional.get();

        Favorito favorito = new Favorito();
        favorito.setCliente(usuario);
        favorito.setProduto(produto);
        favoritoRepository.save(favorito);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado aos favoritos com sucesso.");
    }


    public List<Favorito> listarFavoritosPorCliente(Long idCliente) {
        Optional<Usuario> clienteOptional = clienteRepository.findById(idCliente);
        if (!clienteOptional.isPresent()) {
            throw new FavoritoException("Usuario não encontrado");
        }
        Usuario usuario = clienteOptional.get();
        return favoritoRepository.findAllByCliente(usuario);
    }


    public void atualizarFavorito(Long idFavorito, Long novoIdProduto) {
        Optional<Favorito> favoritoOptional = favoritoRepository.findById(idFavorito);
        if (!favoritoOptional.isPresent()) {
            throw new FavoritoException("Favorito não encontrado");
        }
        Favorito favorito = favoritoOptional.get();

        Optional<Produto> produtoOptional = produtoRepository.findById(novoIdProduto);
        if (!produtoOptional.isPresent()) {
            throw new FavoritoException("Novo produto não encontrado");
        }

        Produto novoProduto = produtoOptional.get();

        favorito.setProduto(novoProduto);
        favoritoRepository.save(favorito);
    }

    public void removerFavorito(Long idCliente, Long idProduto) {
        Optional<Usuario> clienteOptional = clienteRepository.findById(idCliente);
        if (!clienteOptional.isPresent()) {
            throw new FavoritoException("Usuario não encontrado");
        }
        Usuario usuario = clienteOptional.get();

        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (!produtoOptional.isPresent()) {
            throw new FavoritoException("Produto não encontrado");
        }
        Produto produto = produtoOptional.get();

        Optional<Favorito> favoritoOptional = favoritoRepository.findByClienteAndProduto(usuario, produto);
        if (!favoritoOptional.isPresent()) {
            throw new FavoritoException("Favorito não encontrado");
        }

        favoritoRepository.delete(favoritoOptional.get());
    }



}
