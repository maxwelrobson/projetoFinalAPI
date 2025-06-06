package com.example.demo.service;

import com.example.demo.dto.ProdutoRespondeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	public ProdutoRespondeDTO inserir (Produto produto) {
		System.out.println(produto);
		 Produto prod = repository.save(produto);
		System.out.println(prod);
		 return new ProdutoRespondeDTO(prod);
	}
	
	public List<Produto> listar() {
		List<Produto> produtos = repository.findAll();
		return produtos;
	}
	
	public ResponseEntity<Produto> alterar(Long id, Produto produto) {
		
		if (repository.findById(id).isPresent()) {
			produto.setId(id);
			return ResponseEntity.ok(repository.save(produto));
		}
		return ResponseEntity.notFound().build();
	}
}
