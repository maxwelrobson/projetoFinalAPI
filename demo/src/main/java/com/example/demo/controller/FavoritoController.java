package com.example.demo.controller;

import com.example.demo.dto.AtualizarFavoritoDTO;
import com.example.demo.dto.FavoritoRequestDTO;
import com.example.demo.entity.Favorito;
import com.example.demo.service.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Operation(summary = "Funcionalidade para adicionar um favorito.", description = "Adiciona um produto nos favoritos do cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json") }, description = "Retorna resposta automática."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PostMapping
    public ResponseEntity<String> adicionarFavorito(@RequestBody FavoritoRequestDTO favoritoRequest) {
        return favoritoService.adicionarFavorito(favoritoRequest.getClienteId(), favoritoRequest.getProdutoId());
    }


    @Operation(summary = "Funcionalidade para mostrar os favoritos do cliente.", description = "Mostra todos os favoritos do cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json") }, description = "Retorna resposta automática."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Favorito>> listarFavoritos(@PathVariable Long clienteId) {
        List<Favorito> favoritos = favoritoService.listarFavoritosPorCliente(clienteId) ;
        return ResponseEntity.ok(favoritos);
    }

    @Operation(summary = "Funcionalidade para alterar o favorito.", description = "Altera um produto dos favoritos do cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json") }, description = "Retorna resposta automática."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PutMapping("/{idFavorito}")
    public ResponseEntity<String> atualizarFavorito(@PathVariable Long idFavorito, @RequestBody AtualizarFavoritoDTO dto) {
        favoritoService.atualizarFavorito(idFavorito, dto.getNovoProdutoID());
        return ResponseEntity.ok("Favorito atualizado com sucesso.");
    }

    @Operation(summary = "Funcionalidade para excluir um favorito.", description = "Exclui um produto dos favoritos do cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json") }, description = "Retorna resposta automática."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @DeleteMapping("/{clienteId}/{produtoId}")
    public ResponseEntity<String> removerFavorito(@PathVariable Long clienteId, @PathVariable Long produtoId) {
        favoritoService.removerFavorito(clienteId, produtoId);
        return ResponseEntity.ok("Produto removido dos favoritos com sucesso.");
    }
}
