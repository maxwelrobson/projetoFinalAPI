package com.example.demo.controller;

import com.example.demo.entity.Favorito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PedidoRespondeDTO;
import com.example.demo.service.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Operation(summary = "Inserir novo pedido", description = "Cria um novo pedido no sistema e retorna suas informações.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Pedido inserido com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PostMapping
	public PedidoRespondeDTO inserir(@RequestBody PedidoRespondeDTO pedido){
		return pedidoService.inserir(pedido);
	}

	@Operation(summary = "Alterar pedido", description = "Modifica um pedido existente com novos detalhes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Pedido atualizado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@PutMapping("/{id}")
	public ResponseEntity<PedidoRespondeDTO> alterar(@PathVariable Long id, @RequestBody PedidoRespondeDTO pedido) {
		return pedidoService.alterar(id, pedido);
	}

	@Operation(summary = "Buscar pedido por ID", description = "Obtém os detalhes de um pedido específico pelo seu identificador.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
			}, description = "Pedido encontrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	@GetMapping("/{id}")
	public PedidoRespondeDTO listarPorId (@PathVariable Long id) {
		return pedidoService.listarPorId(id);
	}
}