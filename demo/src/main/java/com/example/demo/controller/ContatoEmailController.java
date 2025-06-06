package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContatoDTO;
import com.example.demo.entity.Contato;
import com.example.demo.service.ContatoEmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

//recebe requisições e aciona o service

@RestController
@RequestMapping("/contatos")
public class ContatoEmailController {

    //injeção de dep. p/ enviar o email.
    @Autowired
    private ContatoEmailService contatoEmailService;

    
    @Operation(summary = "Envia o email do Frontend(cliente) para o Backend(empresa)", description = "A resposta retorna a mensagem de email automática ao cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Contato.class), mediaType = "application/json") }, description = "Retorna resposta automática."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PostMapping
    public ResponseEntity<String> enviarContato(@Valid @RequestBody ContatoDTO contatoDTO) {
        contatoEmailService.contatoPorEmail(contatoDTO);  //serviço usado para enviar

    //se ok, retorna a mensagem no email
        return ResponseEntity.ok("Obrigado(a) pelo contato, " + contatoDTO.getNome() + ". Retornaremos o mais breve possível!");
    }
    
    
    @Operation(summary = "Lista todos os emails recebidos por clientes, através de busca pelo email cadastrado.", description = "A resposta lista os contatos cliente x empresa.")
 	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
 			@Content(schema = @Schema(implementation = Contato.class), mediaType = "application/json") }, description = "Retorna todos os contatos especificos de um cliente."),
 			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
 			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
 			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
 			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping("/{email}") // chave de busca
    public ResponseEntity<List<Contato>> buscarContatosDoCliente(@PathVariable String email) {  //Busca contatos feitos por email, usando o metodo do service
        List<Contato> contatos = contatoEmailService.listarContatoPorEmail(email);
        if (contatos.isEmpty()) { // se não tiver nenhum contato feito, lança o erro
            return ResponseEntity.noContent().build(); // retorna este erro, 204
        }
        
        return ResponseEntity.ok(contatos);  //se ok, retorna os contatos realizados (lista), e status 200
    }
}