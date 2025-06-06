package com.example.demo.dto;


import com.example.demo.entity.Categoria;
import com.example.demo.entity.Produto;

import java.util.List;

public record CategoriaResponseDTO(Long id, String tipo, List<Produto> produtos) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getTipo(), categoria.getProdutos());
    }
}


