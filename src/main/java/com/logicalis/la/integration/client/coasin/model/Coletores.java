package com.logicalis.la.integration.client.coasin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Coletores {

    @JsonProperty("Coletores")
    private List<Coletor> coletores;

    public List<Coletor> getColetores() {
        return coletores;
    }

    public void setColetores(List<Coletor> coletores) {
        this.coletores = coletores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Coletores=[");
        if (coletores != null) {
            for (Coletor c : coletores) {
                sb.append(c.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }


}
