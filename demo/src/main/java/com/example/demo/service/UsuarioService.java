package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.pk.UsuarioPerfilPK;
import com.example.demo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.MailConfig;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.entity.UsuarioPerfil;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.UsuarioPerfilRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = repository.findAll();
		List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
		}
		return usuariosDTO;
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario) {
	    if (repository.findByEmail(usuario.getEmail()).isPresent()) {
	        throw new UsuarioException("Email já cadastrado!");
	    }

	    Usuario usuarioEntity = new Usuario();
	    usuarioEntity.setNome(usuario.getNome());
	    usuarioEntity.setEmail(usuario.getEmail());
	    usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));

	    for(UsuarioPerfil up : usuario.getUsuarioPerfis()){
			up.setUsuario(usuarioEntity);
			up.setPerfil(perfilRepository.findById(up.getPerfil().getId()).orElseThrow(()-> new RuntimeException("Perfil não encontrado!")));
			up.setDataCriacao(LocalDate.now());
		}

		repository.save(usuarioEntity);
		System.out.println(usuarioEntity.getUsuarioPerfis());
	    usuarioPerfilRepository.saveAll(usuario.getUsuarioPerfis());

		// envio de e-mail após cadastrar
		emailService.enviarEmail(usuarioEntity.getEmail(), "Usuario cadastrado!",
				"Olá " + usuarioEntity.getNome() + ", seu cadastro foi efetuado com sucesso!");

	    return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
	}

//	@Transactional
//	public ResponseEntity<Usuario> alterar(Long id, Usuario cliente, Long usuarioId) {
//		Usuario existente = repository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
//
//		Usuario usuarioOperador = repository.findById(usuarioId)
//				.orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado com ID: " + usuarioId));
//
//		// Atualiza dados simples
//		existente.setNome(cliente.getNome());
//		existente.setEmail(cliente.getEmail());
//		existente.setCep(cliente.getCep());
//		existente.setNumero(cliente.getNumero());
//		existente.setComplemento(cliente.getComplemento());
//		existente.setSenha(passwordEncoder.encode(cliente.getSenha())); // se quiser atualizar a senha
//
//		// Trata os perfis (se for enviado)
//		if (cliente.getUsuarioPerfis() != null && !cliente.getUsuarioPerfis().isEmpty()) {
//			Set<UsuarioPerfil> perfisAtualizados = new HashSet<>();
//
//			for (UsuarioPerfil up : cliente.getUsuarioPerfis()) {
//				UsuarioPerfilPK pk = new UsuarioPerfilPK();
//				pk.setUsuario(existente);
//				pk.setPerfil(perfilRepository.findById(up.getPerfil().getId())
//						.orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + up.getPerfil().getId())));
//				up.setId(pk);
//				up.setUsuario(existente);
//				up.setPerfil(pk.getPerfil());
//				up.setDataCriacao(LocalDate.now());
//
//				perfisAtualizados.add(up);
//			}
//
//			existente.setUsuarioPerfis(perfisAtualizados);
//			usuarioPerfilRepository.saveAll(perfisAtualizados);
//		}
//
//		Usuario atualizado = repository.save(existente);
//
//		emailService.enviarEmail(atualizado.getEmail(), "Cadastro atualizado!",
//				"Olá " + atualizado.getNome() + ", seu cadastro foi atualizado com sucesso!");
//
//		return ResponseEntity.ok(atualizado);
//	}

	public ResponseEntity<Usuario> alterar(Long id, Usuario cliente, Long usuarioId) {

		if (repository.findById(id).isPresent()) {

			Usuario usuario = repository.findById(usuarioId)
					.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));

			cliente.setUsuario(usuario);
			cliente.setId(id);

			Usuario atualizado = repository.save(cliente);

			// Envio de e-mail de atualização
			emailService.enviarEmail(atualizado.getEmail(), "Cadastro atualizado!",
					"Olá " + atualizado.getNome() + ", seu cadastro foi atualizado com sucesso!");

			return ResponseEntity.ok(atualizado);
		}
		return ResponseEntity.notFound().build();
	}
}
