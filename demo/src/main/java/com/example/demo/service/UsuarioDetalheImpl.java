package com.example.demo.service;

import com.example.demo.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {
	private UsuarioRepository repository;

	// Injeção de dependência através do construtor passando o repositório
	public UsuarioDetalheImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

		return usuario;
	}
}