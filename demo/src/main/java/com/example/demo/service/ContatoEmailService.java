package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContatoDTO;
import com.example.demo.entity.Contato;
import com.example.demo.repository.ContatoRepository;

//regras - enviar, salvar e buscar contatos p /email de clientes 

@Service 
public class ContatoEmailService {
        
        //injeção de dep. p/ enviar o email
    @Autowired  
    private JavaMailSender encaminhar;

          //injeção de dep. p/ salvar contatos realizados
    @Autowired
    private ContatoRepository contatoRepository;

        //envia email c/ dados do contato
    public void contatoPorEmail(ContatoDTO contatoDTO) {

           //mensagem do email
        SimpleMailMessage email = new SimpleMailMessage();

        //para quem será enviado //trocar para o do matheus, quando passar p/ DEMO
        email.setTo("taiane.albuquerque.1994@gmail.com");

        //assunto
        email.setSubject("Novo contato de: " + contatoDTO.getNome());

        //corpo do email
        email.setText("Email: " + contatoDTO.getEmpresaEmail() + "\n\nMensagem:\n" + contatoDTO.getMensagem());

        //envio
        encaminhar.send(email);
        
        // Conversão do DTO, para salvar no BD
        Contato contato = new Contato();
        
        contato.setNome(contatoDTO.getNome());
        contato.setempresaEmail(contatoDTO.getEmpresaEmail());
        contato.setMensagem(contatoDTO.getMensagem());
        contato.setClienteEmail(contatoDTO.getClienteEmail());

        // Salvando no BD
        contatoRepository.save(contato);
        
    }
       
     // Busca lista de contatos p/ email
    public List<Contato> listarContatoPorEmail(String mail) {
        return contatoRepository.findByClienteEmail(mail);
    }

    // salva o contato
    public Contato salvarContato(Contato contato) {
        return contatoRepository.save(contato);
    }
}