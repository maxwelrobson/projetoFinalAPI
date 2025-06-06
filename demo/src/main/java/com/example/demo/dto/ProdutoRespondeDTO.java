package com.example.demo.dto;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Produto;

public record ProdutoRespondeDTO(Long id, String nome, Double preco, Long Categoria) {
    public ProdutoRespondeDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria().getId());
    }
}
