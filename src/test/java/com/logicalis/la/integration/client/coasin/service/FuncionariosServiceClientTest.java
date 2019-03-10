/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.CoasinApplication;
import com.logicalis.la.integration.client.coasin.model.Funcionarios;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(classes = CoasinApplication.class)
@RunWith(SpringRunner.class)
public class FuncionariosServiceClientTest {

    private Resource funcionarios = new ClassPathResource("funcionarios.json");

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private FuncionariosServiceClient funcionariosServiceClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coasin.ws.funcionarios.url}")
    private String url;

    @Before
    public void setUp() {
        this.mockRestServiceServer = MockRestServiceServer.bindTo(this.restTemplate).build();
    }


    @Test
    public void shouldReturnAllFuncionarios() {
        BDDAssertions.then(this.funcionarios).isNotNull();
        BDDAssertions.then(this.restTemplate).isNotNull();
        BDDAssertions.then(this.mockRestServiceServer).isNotNull();

        this.mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.funcionarios, MediaType.APPLICATION_JSON_UTF8));

        final Optional<Funcionarios> optFuncionarios = funcionariosServiceClient.getFuncionarios();
        BDDAssertions.then(optFuncionarios.isPresent()).isTrue();

        Funcionarios funcionarios = optFuncionarios.get();
        BDDAssertions.then(funcionarios.getFuncionarios().size()).isEqualTo(41);

        this.mockRestServiceServer.verify();
    }


}