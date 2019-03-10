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
public class Funcionario {

    private String registro;
    private String nome;
    private String apelido;
    private String email;
    private String data_nascimento;
    private String endereco;
    private String bairro;
    private String complemento;
    private String cep;
    private String ramal;
    private String rg;
    private String cpf;
    private String pis;
    private String data_admissao;
    private String id_cidade_funcionario_totvs;
    private String registro_gestor;
    private String apontamento_via_relogio_ponto;
    private String id_cargo_totvs;
    private String descricao_cargo_totvs;
    private String cargo_confianca;
    private String codigo_categoria_horaria;
    private String descricao_categoria_horaria;
    private String descricao_lotacao;
    private String id_localidade_base_totvs;
    private String cep_localidade;
    private String id_cidade_localidade_totvs;
    private String nome_cidade_localidade;
    private String id_uf_localidade_totvs;
    private String nome_uf_localidade;
    private String sigla_uf_localidade;
    private String endereco_localidade;
    private String bairro_localidade;
    private String numero_localidade;
    private String complemento_localidade;
    private String cnpj;
    private String id_disciplina_totvs;
    private String descricao_disciplina;
    private String id_empresa_totvs;
    private String descricao_empresa;
    private String id_pais_localidade_totvs;
    private String nome_localidade_pais;
    private String locale_pais;

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getId_cidade_funcionario_totvs() {
        return id_cidade_funcionario_totvs;
    }

    public void setId_cidade_funcionario_totvs(String id_cidade_funcionario_totvs) {
        this.id_cidade_funcionario_totvs = id_cidade_funcionario_totvs;
    }

    public String getRegistro_gestor() {
        return registro_gestor;
    }

    public void setRegistro_gestor(String registro_gestor) {
        this.registro_gestor = registro_gestor;
    }

    public String getApontamento_via_relogio_ponto() {
        return apontamento_via_relogio_ponto;
    }

    public void setApontamento_via_relogio_ponto(String apontamento_via_relogio_ponto) {
        this.apontamento_via_relogio_ponto = apontamento_via_relogio_ponto;
    }

    public String getId_cargo_totvs() {
        return id_cargo_totvs;
    }

    public void setId_cargo_totvs(String id_cargo_totvs) {
        this.id_cargo_totvs = id_cargo_totvs;
    }

    public String getDescricao_cargo_totvs() {
        return descricao_cargo_totvs;
    }

    public void setDescricao_cargo_totvs(String descricao_cargo_totvs) {
        this.descricao_cargo_totvs = descricao_cargo_totvs;
    }

    public String getCargo_confianca() {
        return cargo_confianca;
    }

    public void setCargo_confianca(String cargo_confianca) {
        this.cargo_confianca = cargo_confianca;
    }

    public String getCodigo_categoria_horaria() {
        return codigo_categoria_horaria;
    }

    public void setCodigo_categoria_horaria(String codigo_categoria_horaria) {
        this.codigo_categoria_horaria = codigo_categoria_horaria;
    }

    public String getDescricao_categoria_horaria() {
        return descricao_categoria_horaria;
    }

    public void setDescricao_categoria_horaria(String descricao_categoria_horaria) {
        this.descricao_categoria_horaria = descricao_categoria_horaria;
    }

    public String getDescricao_lotacao() {
        return descricao_lotacao;
    }

    public void setDescricao_lotacao(String descricao_lotacao) {
        this.descricao_lotacao = descricao_lotacao;
    }

    public String getId_localidade_base_totvs() {
        return id_localidade_base_totvs;
    }

    public void setId_localidade_base_totvs(String id_localidade_base_totvs) {
        this.id_localidade_base_totvs = id_localidade_base_totvs;
    }

    public String getCep_localidade() {
        return cep_localidade;
    }

    public void setCep_localidade(String cep_localidade) {
        this.cep_localidade = cep_localidade;
    }

    public String getId_cidade_localidade_totvs() {
        return id_cidade_localidade_totvs;
    }

    public void setId_cidade_localidade_totvs(String id_cidade_localidade_totvs) {
        this.id_cidade_localidade_totvs = id_cidade_localidade_totvs;
    }

    public String getNome_cidade_localidade() {
        return nome_cidade_localidade;
    }

    public void setNome_cidade_localidade(String nome_cidade_localidade) {
        this.nome_cidade_localidade = nome_cidade_localidade;
    }

    public String getId_uf_localidade_totvs() {
        return id_uf_localidade_totvs;
    }

    public void setId_uf_localidade_totvs(String id_uf_localidade_totvs) {
        this.id_uf_localidade_totvs = id_uf_localidade_totvs;
    }

    public String getNome_uf_localidade() {
        return nome_uf_localidade;
    }

    public void setNome_uf_localidade(String nome_uf_localidade) {
        this.nome_uf_localidade = nome_uf_localidade;
    }

    public String getSigla_uf_localidade() {
        return sigla_uf_localidade;
    }

    public void setSigla_uf_localidade(String sigla_uf_localidade) {
        this.sigla_uf_localidade = sigla_uf_localidade;
    }

    public String getEndereco_localidade() {
        return endereco_localidade;
    }

    public void setEndereco_localidade(String endereco_localidade) {
        this.endereco_localidade = endereco_localidade;
    }

    public String getBairro_localidade() {
        return bairro_localidade;
    }

    public void setBairro_localidade(String bairro_localidade) {
        this.bairro_localidade = bairro_localidade;
    }

    public String getNumero_localidade() {
        return numero_localidade;
    }

    public void setNumero_localidade(String numero_localidade) {
        this.numero_localidade = numero_localidade;
    }

    public String getComplemento_localidade() {
        return complemento_localidade;
    }

    public void setComplemento_localidade(String complemento_localidade) {
        this.complemento_localidade = complemento_localidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getId_disciplina_totvs() {
        return id_disciplina_totvs;
    }

    public void setId_disciplina_totvs(String id_disciplina_totvs) {
        this.id_disciplina_totvs = id_disciplina_totvs;
    }

    public String getDescricao_disciplina() {
        return descricao_disciplina;
    }

    public void setDescricao_disciplina(String descricao_disciplina) {
        this.descricao_disciplina = descricao_disciplina;
    }

    public String getId_empresa_totvs() {
        return id_empresa_totvs;
    }

    public void setId_empresa_totvs(String id_empresa_totvs) {
        this.id_empresa_totvs = id_empresa_totvs;
    }

    public String getDescricao_empresa() {
        return descricao_empresa;
    }

    public void setDescricao_empresa(String descricao_empresa) {
        this.descricao_empresa = descricao_empresa;
    }

    public String getId_pais_localidade_totvs() {
        return id_pais_localidade_totvs;
    }

    public void setId_pais_localidade_totvs(String id_pais_localidade_totvs) {
        this.id_pais_localidade_totvs = id_pais_localidade_totvs;
    }

    public String getNome_localidade_pais() {
        return nome_localidade_pais;
    }

    public void setNome_localidade_pais(String nome_localidade_pais) {
        this.nome_localidade_pais = nome_localidade_pais;
    }

    public String getLocale_pais() {
        return locale_pais;
    }

    public void setLocale_pais(String locale_pais) {
        this.locale_pais = locale_pais;
    }
}

