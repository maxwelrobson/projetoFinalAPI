package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Usuario;


public interface ClienteRepository extends JpaRepository<Usuario,Long>{


}
