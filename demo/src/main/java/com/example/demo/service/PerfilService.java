package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Perfil;
import com.example.demo.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository repository;

	public Perfil buscar(Long id) {
		Optional<Perfil> perfil = repository.findById(id);
		return perfil.get();
	}
	
	public Perfil inserir(Perfil perfil) {
		return repository.save(perfil);
	}
}