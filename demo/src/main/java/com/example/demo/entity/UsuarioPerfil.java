package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.entity.pk.UsuarioPerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {
    @EmbeddedId
    private UsuarioPerfilPK id = new UsuarioPerfilPK();
    
    private LocalDate dataCriacao;
    
    public UsuarioPerfil(Usuario usuario, Perfil perfil) {
        id.setPerfil(perfil);
        id.setUsuario(usuario);
	}

	public UsuarioPerfil() {
    }

    public UsuarioPerfilPK getId() {
        return id;
    }

    public void setId(UsuarioPerfilPK id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return id.getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        id.setUsuario(usuario);
    }

    public Perfil getPerfil() {
        return id.getPerfil();
    }

    public void setPerfil(Perfil perfil) {
        id.setPerfil(perfil);
    }
}