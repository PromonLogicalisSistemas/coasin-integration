/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.transform;

import com.logicalis.la.integration.client.coasin.model.Escala;
import com.logicalis.la.integration.client.coasin.model.Escalas;
import com.logicalis.la.integration.client.r2d2.ObjectFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class EscalasCoasinToR2D2 implements Transformer<Escala, com.logicalis.la.integration.client.r2d2.Escala> {

    private Log log = org.apache.commons.logging.LogFactory.getLog(EscalasCoasinToR2D2.class);


    private Calendar reqCal;

    @Override
    public com.logicalis.la.integration.client.r2d2.Escala transform(Escala from) throws Exception {
        com.logicalis.la.integration.client.r2d2.Escala to = new com.logicalis.la.integration.client.r2d2.Escala();
        ObjectFactory factory = new ObjectFactory();

        to.setAnoPeriodo(reqCal.get(Calendar.YEAR));
        to.setMesPeriodo(reqCal.get(Calendar.MONTH) + 1 + "");
        to.setDia(Integer.parseInt(from.getNum_dia()));
        to.setInicioJornada(from.getHra_ini_jornada());
        to.setFimJornada(from.getHra_fim_jornada());
        to.setRegistro(from.getCdn_funcionario());
        to.setTipoDia(from.getCod_tipo_dia());

        return to;
    }

    public com.logicalis.la.integration.client.r2d2.Escalas transformAll(Escalas escalas) {
        com.logicalis.la.integration.client.r2d2.Escalas resp = new com.logicalis.la.integration.client.r2d2.Escalas();
        List<com.logicalis.la.integration.client.r2d2.Escala> escalasList = resp.getEscala();
        for (Escala coasin : escalas.getEscalas()) {
            try {
                escalasList.add(this.transform(coasin));
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
