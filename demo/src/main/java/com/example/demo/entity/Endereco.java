package com.example.demo.entity;

import com.example.demo.dto.EnderecoResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario usuario;
    
	public Endereco() {
		super();
	}
	public Endereco(Long id, String cep, String logradouro, String bairro, String localidade, String uf) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

//	public EnderecoResponseDTO toDTO() {
//		EnderecoResponseDTO dto = new EnderecoResponseDTO();
//		dto.setCep(this.getCep());
//		dto.setLogradouro(this.getLogradouro());
//		dto.setBairro(this.getBairro());
//		dto.setLocalidade(this.getLocalidade());
//		dto.setUf(this.getUf());
//		return dto;
//	}
}
