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
public class Funcionario {

    @Getter
    @Setter
    private String registro;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String apelido;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String data_nascimento;

    @Getter
    @Setter
    private String endereco;

    @Getter
    @Setter
    private String bairro;

    @Getter
    @Setter
    private String complemento;

    @Getter
    @Setter
    private String cep;

    @Getter
    @Setter
    private String ramal;

    @Getter
    @Setter
    private String rg;

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String pis;

    @Getter
    @Setter
    private String data_admissao;

    @Getter
    @Setter
    private String id_cidade_funcionario_totvs;

    @Getter
    @Setter
    private String registro_gestor;

    @Getter
    @Setter
    private String apontamento_via_relogio_ponto;

    @Getter
    @Setter
    private String id_cargo_totvs;

    @Getter
    @Setter
    private String descricao_cargo_totvs;

    @Getter
    @Setter
    private String cargo_confianca;

    @Getter
    @Setter
    private String codigo_categoria_horaria;

    @Getter
    @Setter
    private String descricao_categoria_horaria;

    @Getter
    @Setter
    private String codigo_lotacao;

    @Getter
    @Setter
    private String descricao_lotacao;

    @Getter
    @Setter
    private String id_localidade_base_totvs;

    @Getter
    @Setter
    private String nome_localidade;

    @Getter
    @Setter
    private String cep_localidade;

    @Getter
    @Setter
    private String id_cidade_localidade_totvs;

    @Getter
    @Setter
    private String nome_cidade_localidade;

    @Getter
    @Setter
    private String id_uf_localidade_totvs;

    @Getter
    @Setter
    private String nome_uf_localidade;

    @Getter
    @Setter
    private String sigla_uf_localidade;

    @Getter
    @Setter
    private String endereco_localidade;

    @Getter
    @Setter
    private String bairro_localidade;

    @Getter
    @Setter
    private String numero_localidade;

    @Getter
    @Setter
    private String complemento_localidade;

    @Getter
    @Setter
    private String cnpj;

    @Getter
    @Setter
    private String id_disciplina_totvs;

    @Getter
    @Setter
    private String descricao_disciplina;

    @Getter
    @Setter
    private String id_empresa_totvs;

    @Getter
    @Setter
    private String descricao_empresa;

    @Getter
    @Setter
    private String id_pais_localidade_totvs;

    @Getter
    @Setter
    private String nome_localidade_pais;

    @Getter
    @Setter
    private String locale_pais;

}
