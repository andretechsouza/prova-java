package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String processarNomeCompleto(String nome){
        return clienteRepository.processaNomeCompleto(nome);
    }

    public String processarPrimeiroNome(String nome){
        return clienteRepository.processaPrimeiroNome(nome);
    }

    public String processarNomeAbreviado(String nome){
        return clienteRepository.processaNomeAbreviado(nome);
    }



}
