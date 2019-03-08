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
public class Funcionarios {
    @JsonProperty("Funcionarios")
    private List<Funcionario> funcionarios;

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Funcionarios=[");
        if (funcionarios != null) {
            for (Funcionario f : funcionarios) {
                sb.append(f.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
