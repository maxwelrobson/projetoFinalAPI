package com.example.demo.entity.pk;

import java.io.Serializable;

import com.example.demo.entity.Perfil;

import com.example.demo.entity.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//Indica que essa classe pode ser incorporada dentro de outra entidade
@Embeddable
public class UsuarioPerfilPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    // Construtor padrão (necessário para JPA)
    public UsuarioPerfilPK() {
    }

    // ✅ Construtor que aceita Usuario e Perfil
    public UsuarioPerfilPK(Usuario usuario, Perfil perfil) {
        this.usuario = usuario;
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        return usuario.hashCode() + perfil.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsuarioPerfilPK that = (UsuarioPerfilPK) obj;
        return usuario.equals(that.usuario) && perfil.equals(that.perfil);
    }
}