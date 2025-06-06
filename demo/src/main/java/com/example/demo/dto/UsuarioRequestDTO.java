package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.Endereco;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.UsuarioPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDTO {
	@NotBlank
	private String nome;
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
    private List<EnderecoResponseDTO> enderecos;

	private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

	public UsuarioRequestDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<EnderecoResponseDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResponseDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
        this.usuarioPerfis = usuarioPerfis;
    }

    public UsuarioRequestDTO(Usuario usuario) {

		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
        this.enderecos = usuario.getEnderecos().stream().map(EnderecoResponseDTO::new).collect(Collectors.toList());
        }
	}



