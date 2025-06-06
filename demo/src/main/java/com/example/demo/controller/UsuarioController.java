package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Operation(summary = "Lista todos os clientes", description = "A resposta lista os dados dos clientes id, nome, cpf e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Usuario.class), mediaType = "application/json") }, description = "Retorna todos os clientes"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}


	@Operation(summary = "Cadastra um cliente", description = "A resposta cadastra os dados dos clientes id, nome, cpf e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Usuario.class), mediaType = "application/json") }, description = "Retorna todos os clientes"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponseDTO inserir(@RequestBody UsuarioRequestDTO usuario) {
		return service.inserir(usuario);
	}

	@Operation(summary = "Altera um cliente", description = "A resposta altera os dados dos clientes id, nome, cpf e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Usuario.class), mediaType = "application/json") }, description = "Retorna todos os clientes"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PutMapping("/{id}/{usuarioId}") //passa id usuario e seu id usuario
	public ResponseEntity<Usuario> alterar(@PathVariable Long id, @PathVariable Long usuarioId,
										   @RequestBody @Valid Usuario usuario) {
		return service.alterar(id, usuario, usuarioId);
	}
}
