/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.r2d2.service;

import com.logicalis.la.integration.client.r2d2.ColetoresCustos;
import com.logicalis.la.integration.client.r2d2.ColetoresCustosResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class R2D2ColetoresCustosServiceClient extends BaseServiceClient {

    private static final Logger log = LoggerFactory.getLogger(R2D2ColetoresCustosServiceClient.class);

    @Value("${r2d2.ws.coletores.url}")
    private String urlR2D2;

    public ColetoresCustosResponse sendColetoresCustos(ColetoresCustos coletoresCustos) {
        log.info("Sending " + coletoresCustos.getColetorCusto().size() + " coletoresCustos");

        ColetoresCustosResponse response = (ColetoresCustosResponse) getWebServiceTemplate()
                .marshalSendAndReceive(urlR2D2,
                        coletoresCustos);

        log.info("Sent successfully! Got ColetoresCustosResponse = " + response.getMsgError() + ", result = " + response.isColetoresCustosResult());


        return response;
    }

}
