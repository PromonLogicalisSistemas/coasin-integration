/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.r2d2.service;

import com.logicalis.la.integration.client.r2d2.Funcionarios;
import com.logicalis.la.integration.client.r2d2.FuncionariosResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class R2D2FuncionariosServiceClient extends BaseServiceClient {

    private static final Logger log = LoggerFactory.getLogger(R2D2FuncionariosServiceClient.class);

    @Value("${r2d2.ws.funcionarios.url}")
    private String urlR2D2;

    public FuncionariosResponse sendFuncionarios(Funcionarios funcionarios) {
        log.info("Sending " + funcionarios.getFuncionario().size() + " funcionarios");

        FuncionariosResponse response = (FuncionariosResponse) getWebServiceTemplate()
                .marshalSendAndReceive(urlR2D2,
                        funcionarios);

        log.info("Sent successfully! Got FuncionariosResponse = " + response.getMsgError() + ", result = " + response.isFuncionariosResult());


        return response;
    }

}
