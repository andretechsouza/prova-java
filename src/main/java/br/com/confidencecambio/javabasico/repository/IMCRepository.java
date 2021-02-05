package br.com.confidencecambio.javabasico.repository;

import org.springframework.stereotype.Repository;

@Repository
public class IMCRepository {

    public Long calcularIMC(double peso, double altura){
        var calculo = peso / (altura * altura);
        return Math.round(calculo);
    }
}
