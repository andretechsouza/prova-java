package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.repository.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IMCService {

    @Autowired
    private IMCRepository repository;

    public Long processarIMC(double peso, double altura){
        return repository.calcularIMC(peso, altura);
    }
}
