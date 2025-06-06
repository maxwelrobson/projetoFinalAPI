package com.example.demo.service;

import com.example.demo.dto.CategoriaRequestDTO;
import com.example.demo.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

		public CategoriaResponseDTO inserir (CategoriaRequestDTO dto){
			Categoria cat = new Categoria();
			cat.setTipo(dto.getTipo());
			repository.save(cat);
			return new CategoriaResponseDTO(cat);
		}

		public ResponseEntity<Categoria> alterar (Long id, Categoria categoria){

			if (repository.findById(id).isPresent()) {
				categoria.setId(id);
				return ResponseEntity.ok(repository.save(categoria));
			}
			return ResponseEntity.notFound().build();
		}

}

