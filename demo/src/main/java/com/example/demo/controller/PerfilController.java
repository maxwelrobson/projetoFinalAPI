package com.example.demo.controller;

import com.example.demo.entity.Favorito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Perfil;
import com.example.demo.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@Operation(summary = "Inserir novo perfil", description = "Cria um novo perfil no sistema e retorna suas informações.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Perfil inserido com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PostMapping
	public Perfil inserir(@RequestBody Perfil perfil) { // Agora o JSON será mapeado corretamente
	    return perfilService.inserir(perfil);
	}

	@Operation(summary = "Buscar perfil por ID", description = "Retorna as informações de um perfil específico com base no ID informado.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Perfil encontrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@GetMapping
	public Perfil buscar(Long id) {
		return perfilService.buscar(id);
	}
	
}
