package com.example.demo.dto;

import com.example.demo.entity.Usuario;

public class ClienteRequestDTO {
	private Long id;
	private String nome;
	private String email;

	private Long usuarioId;

	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;

	}

}
