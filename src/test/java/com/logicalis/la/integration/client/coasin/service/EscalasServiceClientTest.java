/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.CoasinApplication;
import com.logicalis.la.integration.client.coasin.model.Escalas;
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

import java.util.Calendar;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(classes = CoasinApplication.class)
@RunWith(SpringRunner.class)
public class EscalasServiceClientTest {

    private Resource escalas = new ClassPathResource("escalas.json");

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private EscalasServiceClient escalasServiceClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coasin.ws.escalas.url}")
    private String url;

    @Before
    public void setUp() {
        this.mockRestServiceServer = MockRestServiceServer.bindTo(this.restTemplate).build();
    }


    @Test
    public void shouldReturnValidPeriodo() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 01);

        String periodo = escalasServiceClient.getPeriodo(cal);

        BDDAssertions.then(periodo).isEqualTo("201507");
    }

    @Test
    public void shouldReturnAllEscalas() {
        BDDAssertions.then(this.escalas).isNotNull();
        BDDAssertions.then(this.restTemplate).isNotNull();
        BDDAssertions.then(this.mockRestServiceServer).isNotNull();
        String periodo = escalasServiceClient.getPeriodo(null);

        this.mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo(url + periodo))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.escalas, MediaType.APPLICATION_JSON_UTF8));

        final Optional<Escalas> optEscalas = escalasServiceClient.getEscalas(periodo);
        BDDAssertions.then(optEscalas.isPresent()).isTrue();

        Escalas escalas = optEscalas.get();
        BDDAssertions.then(escalas.getEscalas().size()).isEqualTo(1736);

        this.mockRestServiceServer.verify();
    }


}