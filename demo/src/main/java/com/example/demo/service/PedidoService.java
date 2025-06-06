package com.example.demo.service;

import java.util.Optional;

import com.example.demo.exception.PedidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.PedidoRespondeDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Endereco;
import com.example.demo.entity.Pedido;
import com.example.demo.enums.PedidoStatus;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public PedidoRespondeDTO inserir(PedidoRespondeDTO dto) {
		if (dto.getId() != null && repository.findById(dto.getId()).isPresent()) {
			throw new PedidoException("Pedido já cadastrado");
		}


		Usuario usuario = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> new PedidoException("Usuario não encontrado"));


		Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
				.orElseThrow(() -> new PedidoException("Endereço não encontrado"));


		Pedido pedido = new Pedido();
		pedido.setQuantidade(dto.getQuantidade());
		pedido.setPreco(dto.getPreco());
		pedido.setCliente(usuario);
		pedido.setEndereco(endereco);
		pedido.setProdutos(dto.getProdutos());
		pedido.setStatus(dto.getStatus() != null ? dto.getStatus() : PedidoStatus.PENDENTE);


		Pedido salvo = repository.save(pedido);


		return new PedidoRespondeDTO(
				salvo.getId(),
				salvo.getQuantidade(),
				salvo.getPreco(),
				salvo.getCliente().getId(),
				salvo.getEndereco().getId(),
				salvo.getStatus(),
				salvo.getProdutos()
		);
	}
	
	public ResponseEntity<PedidoRespondeDTO> alterar(Long id, PedidoRespondeDTO pedido) {

		Optional<Pedido> pedidoOptional = repository.findById(id);
		if (pedidoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Pedido pedidoEntity = pedidoOptional.get();
		Usuario usuario = clienteRepository.findById(pedido.getClienteId()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
		pedidoEntity.setQuantidade(pedido.getQuantidade());
		pedidoEntity.setPreco(pedido.getPreco());
		pedidoEntity.setCliente(usuario);
		pedidoEntity.setProdutos(pedido.getProdutos());
		Pedido atualizado = repository.save(pedidoEntity);
		PedidoRespondeDTO respostaDTO = new PedidoRespondeDTO(
				atualizado.getId(),
				atualizado.getQuantidade(),
				atualizado.getPreco(),
				atualizado.getCliente().getId(),
				atualizado.getEndereco().getId(),
				atualizado.getStatus(),
				atualizado.getProdutos()
		);
		return ResponseEntity.ok(respostaDTO);
	}
	
	public PedidoRespondeDTO listarPorId(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		
		if (pedido.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado com ID: " + id);
			
		}
		Pedido p = pedido.get();
		Double total = p.getPreco() * p.getQuantidade();
		PedidoRespondeDTO dto = new PedidoRespondeDTO();
		dto.setId(p.getId());
		dto.setQuantidade(p.getQuantidade());
		dto.setPreco(total);
		dto.setClienteId(p.getCliente().getId());
		dto.setEnderecoId(p.getEndereco().getId());
		dto.setStatus(p.getStatus());
		dto.setProdutos(p.getProdutos());
		return dto;
	}
}
