package com.example.demo.dto;

import com.example.demo.entity.Categoria;

public class CategoriaRequestDTO {
    private String tipo;

    public CategoriaRequestDTO() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public CategoriaRequestDTO(Categoria categoria) {
        this.tipo = categoria.getTipo();


    }
}
