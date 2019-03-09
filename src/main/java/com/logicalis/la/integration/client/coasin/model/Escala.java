
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Escala {
    @Getter
    @Setter
    private String cdn_funcionario;

    @Getter
    @Setter
    private String num_dia;

    @Getter
    @Setter
    private String cod_tipo_dia;

    @Getter
    @Setter
    private String hra_ini_jornada;

    @Getter
    @Setter
    private String hra_fim_jornada;

}
