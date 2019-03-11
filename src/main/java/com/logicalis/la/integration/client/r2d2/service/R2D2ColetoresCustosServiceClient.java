/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.r2d2.service;

import com.logicalis.la.integration.client.r2d2.ColetoresCustos;
import com.logicalis.la.integration.client.r2d2.ColetoresCustosResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class R2D2ColetoresCustosServiceClient extends BaseServiceClient {

    private static final Logger log = LoggerFactory.getLogger(R2D2ColetoresCustosServiceClient.class);


    public ColetoresCustosResponse sendColetoresCustos(ColetoresCustos coletoresCustos) {
        log.info("Sending " + coletoresCustos.getColetorCusto().size() + " coletoresCustos");

        ColetoresCustosResponse response = (ColetoresCustosResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://ts-dev.br.promonlogicalis.com/web-service/coletores-custos",
                        coletoresCustos);

        log.info("Sent successfully! Got ColetoresCustosResponse = " + response.getMsgError() + ", result = " + response.isColetoresCustosResult());


        return response;
    }

}
