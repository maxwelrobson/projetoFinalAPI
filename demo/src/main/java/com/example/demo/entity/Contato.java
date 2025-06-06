package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//guarda os dados a serem usados

@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
	private String nome;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String empresaEmail;

	@NotBlank(message = "Mensagem é obrigatória")
	private String mensagem;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String clienteEmail; // relaciona o contato ao empresaEmail do cliente

	public Contato() {
		super();
	}

	public Contato(String nome, String empresaEmail, String mensagem, String clienteEmail) {
		this.nome = nome;
		this.empresaEmail = empresaEmail;
		this.mensagem = mensagem;
		this.clienteEmail = clienteEmail;
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

	public String getempresaEmail() {
		return empresaEmail;
	}

	public void setempresaEmail(String empresaEmail) {
		this.empresaEmail = empresaEmail;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getClienteEmail() {
		return clienteEmail;
	}

	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}
}