package com.example.demo.controller;

import com.example.demo.dto.CategoriaRequestDTO;
import com.example.demo.dto.CategoriaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Categoria;
import com.example.demo.service.CategoriaService;



@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;


	@Operation(summary = "Inserir nova categoria", description = "Adiciona uma nova categoria ao sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = CategoriaResponseDTO.class), mediaType = "application/json")
			}, description = "Categoria criada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})
	@PostMapping

	public CategoriaResponseDTO inserir(@RequestBody CategoriaRequestDTO categoria) {
		return categoriaService.inserir(categoria);
	}

	@Operation(summary = "Alterar categoria", description = "Atualiza as informações de uma categoria existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Categoria.class), mediaType = "application/json")
			}, description = "Categoria atualizada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@PathVariable Long id, @RequestBody Categoria categoria) {
		return categoriaService.alterar(id, categoria);
	}

	@Operation(summary = "Listar categorias", description = "Retorna todas as categorias disponíveis no sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@GetMapping
	public ResponseEntity<String> listar() {
		return ResponseEntity.ok("Teste");
	}
}
