package br.com.confidencecambio.javabasico.controllers;

import br.com.confidencecambio.javabasico.dto.RoboDTO;
import br.com.confidencecambio.javabasico.service.RoboService;
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
@RequestMapping(value = "/robos")
public class RoboController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoboController.class);

    @Autowired
    private RoboService roboService;

    @ApiOperation(value = "Retorna informações do nome informado")
    @RequestMapping(value = "/campo", method = RequestMethod.GET)
    public ResponseEntity<RoboDTO> processarNomeCompleto(@RequestParam(value = "nome", required = true) String nome){
        LOGGER.info("[Robo] - Início da requisição");
        ResponseEntity<RoboDTO> response = null;
        RoboDTO dto = null;
        try {
            dto = new RoboDTO();
            if(!StringUtils.isEmpty(nome)){
                dto.setNomeCompleto(roboService.processarNomeCompleto(nome));
                dto.setPrimeiroNome(this.roboService.processarPrimeiroNome(nome));
                dto.setNomeAbreviado(this.roboService.processarNomeAbreviado(nome));
                response = new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }catch (Exception e){
            LOGGER.error("[Robo] - Ocorreu um erro ao processar esta requisição", e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            LOGGER.info("[Robo] - Fim da requisição");
        }
        return response;
    }

}
