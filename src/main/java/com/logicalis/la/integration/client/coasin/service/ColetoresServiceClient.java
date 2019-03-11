/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Coletores;
import com.logicalis.la.integration.client.r2d2.ColetoresCustosResponse;
import com.logicalis.la.integration.client.r2d2.service.R2D2ColetoresCustosServiceClient;
import com.logicalis.la.integration.client.transform.ColetoresCoasinToR2D2;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class ColetoresServiceClient extends BaseServiceClient {

    @Value("${coasin.ws.coletores.url}")
    private String urlCoasin;

    @Value("${r2d2.ws.coletores.url}")
    private String urlR2D2;

    @Autowired
    ColetoresCoasinToR2D2 coletoresTransformer;
    @Autowired
    R2D2ColetoresCustosServiceClient r2d2ColetoresCustosServiceClient;

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
                com.logicalis.la.integration.client.r2d2.ColetoresCustos coletoresCustosR2D2 = coletoresTransformer.transformAll(coletores);
                try {
                    ColetoresCustosResponse response = r2d2ColetoresCustosServiceClient.sendColetoresCustos(coletoresCustosR2D2);
                    log.info(response);
                } catch (Exception e) {
                    log.error(e);
                }
            } else {
                System.out.println("coletores.getColetores() is null");
            }
        } else {
            System.out.println("optColetores has no coletores");
        }
    }

    Optional<Coletores> getColetores() {
        //new ParameterizedTypeReference<List<Coletor>>() {});
        return Optional.ofNullable(restTemplate.exchange(
                urlCoasin,
                HttpMethod.GET,
                httpEntity,
                Coletores.class).getBody());
    }


    @Bean
    public ColetoresCoasinToR2D2 coletoresCoasinToR2D2() {
        return new ColetoresCoasinToR2D2();
    }


    @Bean
    public R2D2ColetoresCustosServiceClient r2d2ColetoresCustosServiceClient(@Lazy Jaxb2Marshaller marshaller) {
        R2D2ColetoresCustosServiceClient client = new R2D2ColetoresCustosServiceClient();
        client.setDefaultUri(urlR2D2);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }



}
