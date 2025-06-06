package com.example.demo.service;

import com.example.demo.dto.WishlistResponseDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Produto;
import com.example.demo.entity.Wishlist;
import com.example.demo.exception.WishlistException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Wishlist criarWishlist(WishlistResponseDTO dto) {
        Usuario usuario = clienteRepository.findById(dto.getClinteId()).orElseThrow(() -> new WishlistException("Usuario não encontrado"));
        List<Produto> produtos = produtoRepository.findAllById(dto.getProdutosIds());

        Wishlist wishlist = new Wishlist();
        wishlist.setCliente(usuario);
        wishlist.setProdutos(produtos);

        return wishlistRepository.save(wishlist);
    }

    public Wishlist buscarPorId(Long id) {
        return wishlistRepository.findById(id).orElseThrow(() -> new WishlistException("Wishlist não encontrada"));
    }

    public Wishlist atualizr(Long id, WishlistResponseDTO dto) {
        Wishlist wishlist = buscarPorId(id);
        List<Produto> produtos = produtoRepository.findAllById(dto.getProdutosIds());
        wishlist.setProdutos(produtos);
        return wishlistRepository.save(wishlist);
    }

    public void deletar(Long id) {
        Wishlist wishlist = buscarPorId(id);
        wishlistRepository.delete(wishlist);
    }

    public List<Wishlist> listarPorCliente(Long clienteId) {
        return wishlistRepository.findByClienteId(clienteId);
    }

}
