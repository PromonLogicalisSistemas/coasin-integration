/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Escalas;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Optional;


@Service
public class EscalasServiceClient extends BaseServiceClient {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity httpEntity;
    private Log log = org.apache.commons.logging.LogFactory.getLog(EscalasServiceClient.class);

    @Override
    public void run() {
        log.info("---");
        runGetEscalasService();
        log.info("---");
    }


    private void runGetEscalasService() {
        Optional<Escalas> optEscalas = getEscalas();

        if (optEscalas.isPresent()) {
            Escalas escalas = optEscalas.get();
            if (escalas.getEscalas() != null) {
                log.info("Successfully executed!");
                log.info("Quantidade: " + escalas.getEscalas().size());
                log.debug(escalas.toString());
                log.info("Displaying up to " + MAX_LOG_ITEMS + " first results:");
                log.info(escalas.getEscalas().subList(0, Math.min(MAX_LOG_ITEMS, escalas.getEscalas().size())));
            } else {
                System.out.println("escalas.getEscalas() is null");
            }
        } else {
            System.out.println("optEscalas has no escalas");
        }
    }

    Optional<Escalas> getEscalas() {
        //new ParameterizedTypeReference<List<Coletor>>() {});
        final int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int y = Calendar.getInstance().get(Calendar.YEAR);
        String month = m < 10 ? "0" + m : String.valueOf(m);
        String year = String.valueOf(y);
        String param = year + month;
        log.info("year = " + y + ", month = " + m);
        log.info("param = " + param);
        return Optional.ofNullable(restTemplate.exchange(
                "http://apiinterno.coasin.cl/ProxyR2D2/Service/ListaEscalas?periodo=" + param,
                HttpMethod.GET,
                httpEntity,
                Escalas.class).getBody());
    }


}
