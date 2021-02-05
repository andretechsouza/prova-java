package br.com.confidencecambio.javabasico.controllers;

import br.com.confidencecambio.javabasico.dto.GerenteDTO;
import br.com.confidencecambio.javabasico.service.GerenteService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gerentes")
public class GerenteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GerenteController.class);

    @Autowired
    private GerenteService gerenteService;

    @ApiOperation(value = "Retorna informações do nome informado")
    @RequestMapping(value = "/campo", method = RequestMethod.GET)
    public ResponseEntity<GerenteDTO> processarNomeCompleto(@RequestParam(value = "nome", required = true) String nome){
        LOGGER.info("[Gerente] - Início da requisição");
        ResponseEntity<GerenteDTO> response = null;
        GerenteDTO dto = null;
        try {
            dto = new GerenteDTO();
            if(!StringUtils.isEmpty(nome)){
                dto.setNomeCompleto(gerenteService.processarNomeCompleto(nome));
                dto.setPrimeiroNome(this.gerenteService.processarPrimeiroNome(nome));
                dto.setNomeAbreviado(this.gerenteService.processarNomeAbreviado(nome));
                response = new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }catch (Exception e){
            LOGGER.error("[Gerente] - Ocorreu um erro ao processar esta requisição", e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            LOGGER.info("[Gerente] - Fim da requisição");
        }
        return response;
    }

}
