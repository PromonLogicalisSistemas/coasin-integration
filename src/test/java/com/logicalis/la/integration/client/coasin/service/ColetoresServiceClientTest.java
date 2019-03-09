/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.CoasinApplication;
import com.logicalis.la.integration.client.coasin.model.Coletores;
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
public class ColetoresServiceClientTest {

    private Resource coletores = new ClassPathResource("coletores.json");

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ColetoresServiceClient coletoresServiceClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coasin.ws.coletores.url}")
    private String url;

    @Before
    public void setUp() {
        this.mockRestServiceServer = MockRestServiceServer.bindTo(this.restTemplate).build();
    }


    @Test
    public void shouldReturnAllColetores() {
        BDDAssertions.then(this.coletores).isNotNull();
        BDDAssertions.then(this.restTemplate).isNotNull();
        BDDAssertions.then(this.mockRestServiceServer).isNotNull();

        this.mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.coletores, MediaType.APPLICATION_JSON_UTF8));

        final Optional<Coletores> optColetores = coletoresServiceClient.getColetores();
        BDDAssertions.then(optColetores.isPresent()).isTrue();

        Coletores coletores = optColetores.get();
        BDDAssertions.then(coletores.getColetores().size()).isEqualTo(1164);

        this.mockRestServiceServer.verify();
    }


}