package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public String processarNomeCompleto(String nome){
        return gerenteRepository.processaNomeCompleto(nome);
    }

    public String processarPrimeiroNome(String nome){
        return gerenteRepository.processaPrimeiroNome(nome);
    }

    public String processarNomeAbreviado(String nome){
        return gerenteRepository.processaNomeAbreviado(nome);
    }



}
