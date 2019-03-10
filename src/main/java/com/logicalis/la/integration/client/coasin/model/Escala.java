
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Escala {

    private String cdn_funcionario;

    public String getCdn_funcionario() {
        return cdn_funcionario;
    }

    public void setCdn_funcionario(String cdn_funcionario) {
        this.cdn_funcionario = cdn_funcionario;
    }

    public String getNum_dia() {
        return num_dia;
    }

    public void setNum_dia(String num_dia) {
        this.num_dia = num_dia;
    }

    public String getCod_tipo_dia() {
        return cod_tipo_dia;
    }

    public void setCod_tipo_dia(String cod_tipo_dia) {
        this.cod_tipo_dia = cod_tipo_dia;
    }

    public String getHra_ini_jornada() {
        return hra_ini_jornada;
    }

    public void setHra_ini_jornada(String hra_ini_jornada) {
        this.hra_ini_jornada = hra_ini_jornada;
    }

    public String getHra_fim_jornada() {
        return hra_fim_jornada;
    }

    public void setHra_fim_jornada(String hra_fim_jornada) {
        this.hra_fim_jornada = hra_fim_jornada;
    }

    private String num_dia;

    private String cod_tipo_dia;

    private String hra_ini_jornada;

    private String hra_fim_jornada;

}
