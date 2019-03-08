/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Funcionarios;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class FuncionariosServiceClient extends BaseServiceClient {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity httpEntity;
    private Log log = org.apache.commons.logging.LogFactory.getLog(FuncionariosServiceClient.class);

    @Override
    public void run() {
        log.info("---");
        runGetFuncionariosService();
        log.info("---");
    }


    private void runGetFuncionariosService() {
        Optional<Funcionarios> optFuncionarios = getFuncionarios();

        if (optFuncionarios.isPresent()) {
            Funcionarios funcionarios = optFuncionarios.get();
            if (funcionarios.getFuncionarios() != null) {
                log.info("Successfully executed!");
                log.info("Quantidade: " + funcionarios.getFuncionarios().size());
                log.debug(funcionarios.toString());
                log.info("Displaying up to " + MAX_LOG_ITEMS + " first results:");
                log.info(funcionarios.getFuncionarios().subList(0, Math.min(MAX_LOG_ITEMS, funcionarios.getFuncionarios().size())));
            } else {
                System.out.println("funcionarios.getFuncionarios() is null");
            }
        } else {
            System.out.println("optFuncionarios has no funcionarios");
        }
    }

    Optional<Funcionarios> getFuncionarios() {
        //new ParameterizedTypeReference<List<Coletor>>() {});
        return Optional.ofNullable(restTemplate.exchange(
                "http://apiinterno.coasin.cl/ProxyR2D2/Service/GetFuncionarios",
                HttpMethod.GET,
                httpEntity,
                Funcionarios.class).getBody());
    }


}
