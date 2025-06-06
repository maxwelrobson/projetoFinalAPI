package com.example.demo.service;


import com.example.demo.exception.EnderecoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;

	
	public Endereco buscarEnderecoPorCep (String cep) throws EnderecoException{
		var endereco = Optional.ofNullable(repository.findByCep(cep)) ;

		if (endereco.isPresent()) {
			return endereco.get(); //Verifica se o endereço já existe no sistema
		} else {
			RestTemplate rs = new RestTemplate(); //Se não ele pega do site ViaCep
			String url = "https://viacep.com.br/ws/" + cep + "/json";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(url, Endereco.class)) ;

			//Tira o tracinho do cep
			if (enderecoViaCep.isPresent()) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return  inserir(enderecoViaCep.get());

			}

		}
		throw new EnderecoException(HttpStatus.NOT_FOUND);

	}

		private Endereco inserir(Endereco endereco) {
			return repository.save(endereco);
		}

}
