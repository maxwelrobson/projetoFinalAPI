package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
    Endereco findByCep(String cep);

}
