package br.com.confidencecambio.javabasico.controllers;

import br.com.confidencecambio.javabasico.service.IMCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("Operações relacionadas ao IMC")
public class ImcController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImcController.class);

    @Autowired
    private IMCService imcService;

    @ApiOperation(value = "Retorna o IMC, com base nos dados informados")
    @RequestMapping(value = "/imc", method = RequestMethod.GET)
    public ResponseEntity<String> ola(@RequestParam(value = "altura", required = true) float altura,
                                    @RequestParam(value = "peso", required = true) float peso) {
        float calculoIMC = imcService.processarIMC(peso, altura);
        String processado = null;
        String retorno = null;
        ResponseEntity<String> response = null;

        try{
            if(calculoIMC < 18.5){
                processado = "Baixo peso";
            }else if(calculoIMC >= 18.5 && calculoIMC < 24.9){
                processado = "Peso ideal";
            }else if(calculoIMC >= 25 && calculoIMC <= 29.9 ){
                processado = "Sobrepeso";
            }else if(calculoIMC >= 30 && calculoIMC <= 34.9){
                processado = "Obesidade Grau 1";
            }else if(calculoIMC >= 35 && calculoIMC <= 39.9){
                processado = "Obesidade Grau 2";
            }else if(calculoIMC >= 40){
                processado = "Obesidade Grau 3";
            }

            retorno = "Você está com: " + processado + " e o seu IMC é: " + calculoIMC;
            response = new ResponseEntity<>(retorno, HttpStatus.OK);
            LOGGER.info("[IMC] - Processamento realizado com sucesso");
        }catch (Exception e){
            LOGGER.error("[IMC] - Ocorreu um erro durante o processamento", e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
