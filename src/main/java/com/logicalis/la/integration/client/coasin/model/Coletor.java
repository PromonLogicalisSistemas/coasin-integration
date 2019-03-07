
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Coletor {
    @Getter
    @Setter
    private String colector_costo;

    @Getter
    @Setter
    private String tipo;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private String email_gestor;

    @Getter
    @Setter
    private String situacion;

    @Getter
    @Setter
    private String excluido;

    @Getter
    @Setter
    private String id_empresa_totvs;

    @Getter
    @Setter
    private String email_pl;


    @Override
    public String toString() {
        return "Coletor{" +
                "colector_costo='" + colector_costo + '\'' +
                "tipo='" + tipo + '\'' +
                "descripcion='" + descripcion + '\'' +
                "email_gestor='" + email_gestor + '\'' +
                "situacion='" + situacion + '\'' +
                "excluido='" + excluido + '\'' +
                "id_empresa_totvs='" + id_empresa_totvs + '\'' +
                "email_pl='" + email_pl + '\'' +
                '}';
    }
}
