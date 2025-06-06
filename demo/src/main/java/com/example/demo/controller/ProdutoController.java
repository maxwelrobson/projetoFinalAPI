package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.ProdutoRespondeDTO;
import com.example.demo.entity.Favorito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Operation(summary = "Inserir novo produto", description = "Cadastra um novo produto no sistema e retorna suas informações.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Produto inserido com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PostMapping
	public ProdutoRespondeDTO inserir(@RequestBody Produto produto) {
		System.out.println(produto);
		return produtoService.inserir(produto);
	}

	@Operation(summary = "Listar produtos", description = "Retorna a lista de todos os produtos disponíveis no sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Lista de produtos obtida com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@GetMapping
	public List<Produto> listar(){
		return produtoService.listar();
	}

	@Operation(summary = "Alterar produto", description = "Modifica um produto existente com novos detalhes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.alterar(id, produto);
	}
}
