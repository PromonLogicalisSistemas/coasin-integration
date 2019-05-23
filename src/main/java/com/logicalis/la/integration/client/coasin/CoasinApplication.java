
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin;

import com.logicalis.la.integration.client.coasin.service.ColetoresServiceClient;
import com.logicalis.la.integration.client.coasin.service.EscalasServiceClient;
import com.logicalis.la.integration.client.coasin.service.FuncionariosServiceClient;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoasinApplication {

    private Log log = org.apache.commons.logging.LogFactory.getLog(ColetoresServiceClient.class);

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ColetoresServiceClient coletoresClient;

    @Autowired
    private FuncionariosServiceClient funcionariosClient;

    @Autowired
    private EscalasServiceClient escalasClient;

    public static void main(String[] args) {
        new SpringApplicationBuilder(CoasinApplication.class).web(WebApplicationType.NONE).run(args);
    }


    @Bean
    public CommandLineRunner run() {
        return args -> {
            if (args.length < 1) {
                String msg = "Aplicação espera receber um argumento que pode assumir os valores: 'coletores'|'funcionarios'|'escalas'";
                System.err.println(msg);
                log.error(msg);
                return;
            }
            if ("coletores".equalsIgnoreCase(args[0])) {
                coletoresClient.run();
            } else if ("funcionarios".equalsIgnoreCase(args[0])) {
                funcionariosClient.run();
            } else if ("escalas".equalsIgnoreCase(args[0])) {
                escalasClient.run();
            } else {
                String msg = "Argumento " + args[0] + " não reconhecido, encerrando imediatamente";
                log.warn(msg);
            }
            /* Remove comments bellow if Spring starts Tomcat or refuses to exit */
            /* SpringApplication.exit(context); */
        };
    }

}
