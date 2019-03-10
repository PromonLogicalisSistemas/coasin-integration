/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.r2d2.service;

import com.logicalis.la.integration.client.r2d2.Funcionarios;
import com.logicalis.la.integration.client.r2d2.FuncionariosResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class R2D2FuncionariosServiceClient extends BaseServiceClient {

    private static final Logger log = LoggerFactory.getLogger(R2D2FuncionariosServiceClient.class);


    public FuncionariosResponse sendFuncionarios(Funcionarios funcionarios) {
        log.info("Sending " + funcionarios.getFuncionario().size() + " funcionarios");

        FuncionariosResponse response = (FuncionariosResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://ts-dev.br.promonlogicalis.com/web-service/funcionarios",
                        funcionarios);

        log.info("Sent successfully! Got FuncionariosResponse = " + response.getMsgError() + ", result = " + response.isFuncionariosResult());


        return response;
    }

}
