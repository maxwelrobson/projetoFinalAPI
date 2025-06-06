package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Favorito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.WishlistResponseDTO;
import com.example.demo.entity.Wishlist;
import com.example.demo.service.WishlistService;

@RestController
@RequestMapping("listadedesejos")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @Operation(summary = "Criar lista de desejos", description = "Cria uma nova lista de desejos para o usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Lista de desejos criada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @PostMapping
    public ResponseEntity<Wishlist> criar(@RequestBody WishlistResponseDTO dto){
        return ResponseEntity.ok(wishlistService.criarWishlist(dto));
    }

    @Operation(summary = "Buscar lista de desejos por ID", description = "Retorna uma lista de desejos específica com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Lista de desejos encontrada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar lista de desejos", description = "Modifica os detalhes de uma lista de desejos existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Lista de desejos atualizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> atualizar(@PathVariable Long id, @RequestBody WishlistResponseDTO dto){
        return ResponseEntity.ok(wishlistService.atualizr(id, dto));
    }

    @Operation(summary = "Deletar lista de desejos", description = "Remove uma lista de desejos do sistema com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Lista de desejos removida com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        wishlistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar listas de desejos por cliente", description = "Retorna todas as listas de desejos associadas a um cliente específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Listas de desejos obtidas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Wishlist>> listarPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(wishlistService.listarPorCliente(clienteId));
    }
}
