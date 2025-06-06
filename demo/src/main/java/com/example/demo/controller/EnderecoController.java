package com.example.demo.controller;


import com.example.demo.entity.Endereco;
import com.example.demo.entity.Favorito;
import com.example.demo.exception.EnderecoException;
import com.example.demo.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Operation(summary = "Buscar endereço por CEP", description = "Retorna um endereço baseado no CEP informado, caso esteja disponível no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Favorito.class), mediaType = "application/json")
            }, description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })

    @GetMapping("{cep}")
    public ResponseEntity<Endereco> buscarCep(@PathVariable String cep) throws EnderecoException {
        var endereco = service.buscarEnderecoPorCep(cep);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        }
        return ResponseEntity.notFound().build();
    }


}
