/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.transform;

public interface Transformer<From, To> {

    To transform(From source) throws Exception;

}
