
/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Coletor {
    private String colector_costo;

    public String getColector_costo() {
        return colector_costo;
    }

    public void setColector_costo(String colector_costo) {
        this.colector_costo = colector_costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail_gestor() {
        return email_gestor;
    }

    public void setEmail_gestor(String email_gestor) {
        this.email_gestor = email_gestor;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getExcluido() {
        return excluido;
    }

    public void setExcluido(String excluido) {
        this.excluido = excluido;
    }

    public String getId_empresa_totvs() {
        return id_empresa_totvs;
    }

    public void setId_empresa_totvs(String id_empresa_totvs) {
        this.id_empresa_totvs = id_empresa_totvs;
    }

    public String getEmail_pl() {
        return email_pl;
    }

    public void setEmail_pl(String email_pl) {
        this.email_pl = email_pl;
    }

    private String tipo;

    private String descripcion;

    private String email_gestor;

    private String situacion;

    private String excluido;

    private String id_empresa_totvs;

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
