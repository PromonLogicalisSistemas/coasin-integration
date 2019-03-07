
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin;

import com.logicalis.la.integration.client.coasin.model.Coletores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoasinApplication {

    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${coasin.ws.api_key}")
    private String apiKey;

    public static void main(String[] args) {
        new SpringApplicationBuilder(CoasinApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, HttpEntity<Coletores> httpEntity) {
        return args -> {
            runGetColetoresService(restTemplate, httpEntity);

            /* Remove comments bellow if Spring starts Tomcat or refuses to exit */
            /* SpringApplication.exit(context); */
        };
    }

    private void runGetColetoresService(RestTemplate restTemplate, HttpEntity<Coletores> httpEntity) {
        ResponseEntity<Coletores> response = restTemplate.exchange(
                "http://apiinterno.coasin.cl/ProxyR2D2/Service/CargaColetores",
                HttpMethod.GET,
                httpEntity,
                Coletores.class);
        //new ParameterizedTypeReference<List<Coletor>>() {});

        if (response != null && response.hasBody()) {
            Coletores coletores = response.getBody();
            if (coletores != null && coletores.getColetores() != null) {
                System.out.println("Quantidade: " + coletores.getColetores().size());
                System.out.println(coletores.toString());
            } else {
                System.out.println("coletores is null OR coletores.getColetores() is null");
            }
        } else {
            System.out.println("response is null OR has no body");
        }
    }

    @Bean
    public HttpEntity<Coletores> getColetoresHttpEntity() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0");
        headers.add(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.8");
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, apiKey);

        return new HttpEntity<>(headers);
    }

}
