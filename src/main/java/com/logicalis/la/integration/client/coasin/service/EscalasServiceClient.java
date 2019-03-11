/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.service;

import com.logicalis.la.integration.client.coasin.model.Escalas;
import com.logicalis.la.integration.client.r2d2.EscalasResponse;
import com.logicalis.la.integration.client.r2d2.service.R2D2EscalasServiceClient;
import com.logicalis.la.integration.client.transform.EscalasCoasinToR2D2;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Optional;


@Service
public class EscalasServiceClient extends BaseServiceClient {

    @Autowired
    EscalasCoasinToR2D2 escalasTransformer;
    @Autowired
    R2D2EscalasServiceClient r2d2EscalasServiceClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntity httpEntity;
    private Log log = org.apache.commons.logging.LogFactory.getLog(EscalasServiceClient.class);

    @Value("${coasin.ws.escalas.url}")
    private String url;

    @Value("${r2d2.ws.escalas.url}")
    private String urlR2D2;

    @Override
    public void run() {
        log.info("---");
        runGetEscalasService();
        log.info("---");
    }

    private void runGetEscalasService() {
        String periodo = getPeriodo(null);
        Optional<Escalas> optEscalas = getEscalas(periodo);

        if (optEscalas.isPresent()) {
            Escalas escalas = optEscalas.get();
            if (escalas.getEscalas() != null) {
                log.info("Successfully executed!");
                log.info("Quantidade: " + escalas.getEscalas().size());
                log.debug(escalas.toString());
                log.info("Displaying up to " + MAX_LOG_ITEMS + " first results:");
                log.info(escalas.getEscalas().subList(0, Math.min(MAX_LOG_ITEMS, escalas.getEscalas().size())));
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt(periodo.substring(0, 4)));
                cal.set(Calendar.MONTH, Integer.parseInt(periodo.substring(5)) - 1);
                escalasTransformer.setReqCal(cal);
                com.logicalis.la.integration.client.r2d2.Escalas escalasR2D2 = escalasTransformer.transformAll(escalas);
                try {
                    EscalasResponse response = r2d2EscalasServiceClient.sendEscalas(escalasR2D2);
                    log.info(response);
                } catch (Exception e) {
                    log.error(e);
                }
            } else {
                System.out.println("escalas.getEscalas() is null");
            }
        } else {
            System.out.println("optEscalas has no escalas");
        }
    }

    Optional<Escalas> getEscalas(String periodo) {
        //new ParameterizedTypeReference<List<Coletor>>() {});
        //String param = getPeriodo();
        return Optional.ofNullable(restTemplate.exchange(
                url + periodo,
                HttpMethod.GET,
                httpEntity,
                Escalas.class).getBody());
    }

    String getPeriodo(Calendar cal) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        final int m = cal.get(Calendar.MONTH) + 1;
        final int y = cal.get(Calendar.YEAR);
        String month = m < 10 ? "0" + m : String.valueOf(m);
        String year = String.valueOf(y);
        String param = year + month;
        log.info("year = " + y + ", month = " + m);
        log.info("param = " + param);
        return param;
    }

    @Bean
    public EscalasCoasinToR2D2 escalaCoasinToR2D2() {
        return new EscalasCoasinToR2D2();
    }


    @Bean
    public R2D2EscalasServiceClient r2d2EscalasServiceClient(Jaxb2Marshaller marshaller) {
        R2D2EscalasServiceClient client = new R2D2EscalasServiceClient();
        client.setDefaultUri(urlR2D2);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
