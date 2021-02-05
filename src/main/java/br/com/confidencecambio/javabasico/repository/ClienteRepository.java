package br.com.confidencecambio.javabasico.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class ClienteRepository {

    public String processaNomeCompleto(String nome){
        return nome.trim().toUpperCase();
    }

    public String processaNomeAbreviado(String nome){
        String nomeInteiro = nome;
        nomeInteiro = nomeInteiro.replace(' ',';');
        String nomePedacos[] = nomeInteiro.split(";");
        int k;
        String saida="";
        for(k=0 ; k<nomePedacos.length ; k++)
        {
            if(k == 0 || k == (nomePedacos.length-1)){
                saida = saida + StringUtils.capitalize(nomePedacos[k]);
            }
            else {
                saida = saida + " " + StringUtils.capitalize(nomePedacos[k].charAt(0) + ". ");
            }
        }
        return saida;
    }

    public String processaPrimeiroNome(String nome) {
        String retorno= "";
        String pattern = "^([a-zA-ZÈ-Úè-ú]+)\\s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(nome);
        if (m.find()) {
            retorno = m.group(0);
            return retorno.toUpperCase().trim();
        }
        return StringUtils.capitalize(retorno.trim());
    }

}
