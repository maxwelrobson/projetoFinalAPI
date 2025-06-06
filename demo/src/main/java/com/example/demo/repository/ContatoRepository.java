package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Contato;

//acesso e guarda tudo no BD

public interface ContatoRepository extends JpaRepository<Contato, Long> {  //busca na lista de contatos realizados, identificando pelo email
	List<Contato> findByClienteEmail(String clienteEmail);
}