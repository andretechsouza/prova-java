package br.com.confidencecambio.javabasico.controllers;

import br.com.confidencecambio.javabasico.dto.ClienteDTO;
import br.com.confidencecambio.javabasico.service.ClienteService;
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
@RequestMapping(value = "/clientes")
public class ClienteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Retorna informações do nome informado")
    @RequestMapping(value = "/campo", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> processarNomeCompleto(@RequestParam(value = "nome", required = true) String nome){
        LOGGER.info("[Cliente] - Início da requisição");
        ResponseEntity<ClienteDTO> response = null;
        ClienteDTO clienteDTO = null;
        try {
            clienteDTO = new ClienteDTO();
            if(!StringUtils.isEmpty(nome)){
                clienteDTO.setNomeCompleto(clienteService.processarNomeCompleto(nome));
                clienteDTO.setPrimeiroNome(this.clienteService.processarPrimeiroNome(nome));
                clienteDTO.setNomeAbreviado(this.clienteService.processarNomeAbreviado(nome));
                response = new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            LOGGER.error("[Cliente] - Ocorreu um erro ao processar esta requisição", e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            LOGGER.info("[Cliente] - Fim da requisição");
        }
        return response;
    }

}
