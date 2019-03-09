/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Escalas {
    @JsonProperty("Escalas")
    private List<Escala> escalas;

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Escalas=[");
        if (escalas != null) {
            for (Escala e : escalas) {
                sb.append(e.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
