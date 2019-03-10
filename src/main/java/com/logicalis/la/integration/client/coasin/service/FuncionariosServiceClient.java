/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Funcionarios;
import com.logicalis.la.integration.client.r2d2.FuncionariosResponse;
import com.logicalis.la.integration.client.r2d2.service.R2D2FuncionariosServiceClient;
import com.logicalis.la.integration.client.transform.FuncionarioCoasinToR2D2;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class FuncionariosServiceClient extends BaseServiceClient {

    @Autowired
    FuncionarioCoasinToR2D2 funcionariosTransformer;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity httpEntity;
    @Autowired
    R2D2FuncionariosServiceClient r2d2FuncionariosServiceClient;
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

                com.logicalis.la.integration.client.r2d2.Funcionarios funcionariosR2D2 = funcionariosTransformer.transformAll(funcionarios);
                try {
                    FuncionariosResponse funcionariosResponse = r2d2FuncionariosServiceClient.sendFuncionarios(funcionariosR2D2);
                    log.info(funcionariosResponse);
                } catch (Exception e) {
                    log.error(e);
                }

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


    @Bean
    public FuncionarioCoasinToR2D2 funcionarioCoasinToR2D2() {
        return new FuncionarioCoasinToR2D2();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.logicalis.la.integration.client.r2d2");
        return marshaller;
    }

    @Bean
    public R2D2FuncionariosServiceClient r2d2FuncionariosServiceClient(Jaxb2Marshaller marshaller) {
        R2D2FuncionariosServiceClient client = new R2D2FuncionariosServiceClient();
        client.setDefaultUri("http://ts-dev.br.promonlogicalis.com/web-service/funcionarios");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
