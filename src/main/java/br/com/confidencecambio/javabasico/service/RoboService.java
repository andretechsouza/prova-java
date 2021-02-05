package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.repository.RoboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoboService {

    @Autowired
    private RoboRepository roboRepository;

    public String processarNomeCompleto(String nome){
        return roboRepository.processaNomeCompleto(nome);
    }

    public String processarPrimeiroNome(String nome){
        return roboRepository.processaPrimeiroNome(nome);
    }

    public String processarNomeAbreviado(String nome){
        return roboRepository.processaNomeAbreviado(nome);
    }



}
