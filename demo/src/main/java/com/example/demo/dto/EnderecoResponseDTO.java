package com.example.demo.dto;

import  com.example.demo.entity.Endereco;

import java.util.List;


public record EnderecoResponseDTO(String cep, String logradouro, String bairro, String localidade, String uf) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(), endereco.getLocalidade(),
                endereco.getUf());
    }


}
