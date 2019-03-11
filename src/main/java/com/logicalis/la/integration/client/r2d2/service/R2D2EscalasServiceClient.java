/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.r2d2.service;

import com.logicalis.la.integration.client.r2d2.Escalas;
import com.logicalis.la.integration.client.r2d2.EscalasResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class R2D2EscalasServiceClient extends BaseServiceClient {

    private static final Logger log = LoggerFactory.getLogger(R2D2EscalasServiceClient.class);

    @Value("${r2d2.ws.escalas.url}")
    private String urlR2D2;

    public EscalasResponse sendEscalas(Escalas escalas) {
        log.info("Sending " + escalas.getEscala().size() + " escalas");

        EscalasResponse response = (EscalasResponse) getWebServiceTemplate()
                .marshalSendAndReceive(urlR2D2,
                        escalas);

        log.info("Sent successfully! Got EscalasResponse = " + response.getMsgError() + ", result = " + response.isEscalasResult());


        return response;
    }

}
