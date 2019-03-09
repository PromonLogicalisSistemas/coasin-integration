/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Coletores;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class ColetoresServiceClient extends BaseServiceClient {

    @Value("${coasin.ws.coletores.url}")
    private String url;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity httpEntity;
    private Log log = org.apache.commons.logging.LogFactory.getLog(ColetoresServiceClient.class);

    @Override
    public void run() {
        log.info("---");
        runGetColetoresService();
        log.info("---");
    }


    private void runGetColetoresService() {
        Optional<Coletores> optColetores = getColetores();

        if (optColetores.isPresent()) {
            Coletores coletores = optColetores.get();
            if (coletores.getColetores() != null) {
                log.info("Successfully executed!");
                log.info("Quantidade: " + coletores.getColetores().size());
                log.debug(coletores.toString());
                log.info("Displaying up to " + MAX_LOG_ITEMS + " first results:");
                log.info(coletores.getColetores().subList(0, Math.min(MAX_LOG_ITEMS, coletores.getColetores().size())));
            } else {
                System.out.println("coletores.getFuncionarios() is null");
            }
        } else {
            System.out.println("optColetores has no coletores");
        }
    }

    Optional<Coletores> getColetores() {
        //new ParameterizedTypeReference<List<Coletor>>() {});
        return Optional.ofNullable(restTemplate.exchange(
                url,
                //"http://apiinterno.coasin.cl/ProxyR2D2/Service/CargaColetores",
                HttpMethod.GET,
                httpEntity,
                Coletores.class).getBody());
    }


}
