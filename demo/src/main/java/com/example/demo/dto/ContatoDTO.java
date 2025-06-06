package com.example.demo.dto;

//troca informações com o front/ cliente, passando infos do contato

public class ContatoDTO {

    private String nome;
    private String empresaEmail;
    private String mensagem;
    private String clienteEmail;
    
    
	public ContatoDTO() {
		super();
	}

	public ContatoDTO(String nome, String empresaEmail, String mensagem, String clienteEmail) {
		super();
		this.nome = nome;
		this.empresaEmail = empresaEmail;
		this.mensagem = mensagem;
		this.clienteEmail = clienteEmail;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmpresaEmail() {
		return empresaEmail;
	}

	public void setEmpresaEmail(String empresaEmail) {
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