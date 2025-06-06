package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}