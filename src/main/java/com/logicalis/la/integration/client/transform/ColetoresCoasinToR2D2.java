/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.transform;

import com.logicalis.la.integration.client.coasin.model.Coletor;
import com.logicalis.la.integration.client.coasin.model.Coletores;
import com.logicalis.la.integration.client.r2d2.ObjectFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ColetoresCoasinToR2D2 implements Transformer<Coletor, com.logicalis.la.integration.client.r2d2.ColetorCusto> {

    private Log log = org.apache.commons.logging.LogFactory.getLog(ColetoresCoasinToR2D2.class);


    private Calendar reqCal;

    @Override
    public com.logicalis.la.integration.client.r2d2.ColetorCusto transform(Coletor from) throws Exception {
        com.logicalis.la.integration.client.r2d2.ColetorCusto to = new com.logicalis.la.integration.client.r2d2.ColetorCusto();
        ObjectFactory factory = new ObjectFactory();

        to.setIdEmpresaTotvs(from.getId_empresa_totvs());
        to.setElementoPep(factory.createColetorCustoElementoPep(from.getColector_costo()));
        to.setDescricao(from.getDescripcion());
        to.setExcluido(from.getExcluido() != null && Boolean.parseBoolean(from.getExcluido()));
        // TODO: Alterar origem pra receber re ao invés do email. to.setRegistroGestor("25140240");
        to.setRegistroGestor(from.getEmail_gestor());
        to.setSituacao(factory.createColetorCustoSituacao(from.getSituacion()));
        to.setRegistroSolicitante("");
        to.setTipo(from.getTipo());

        return to;
    }

    public com.logicalis.la.integration.client.r2d2.ColetoresCustos transformAll(Coletores coletores) {
        com.logicalis.la.integration.client.r2d2.ColetoresCustos resp = new com.logicalis.la.integration.client.r2d2.ColetoresCustos();
        List<com.logicalis.la.integration.client.r2d2.ColetorCusto> coletorsList = resp.getColetorCusto();
        for (Coletor coasin : coletores.getColetores()) {
            try {
                coletorsList.add(this.transform(coasin));
            } catch (Exception e) {
                log.error(e);
            }
        }
        return resp;
    }


    public Calendar getReqCal() {
        return reqCal;
    }

    public void setReqCal(Calendar reqCal) {
        this.reqCal = reqCal;
    }
}
