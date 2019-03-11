/*
 * Copyright (c) 2019. Flavio Regis de Arruda <flavio.arruda@gmail.com>.
 */

package com.logicalis.la.integration.client.transform;

import com.logicalis.la.integration.client.coasin.model.Funcionario;
import com.logicalis.la.integration.client.coasin.model.Funcionarios;
import com.logicalis.la.integration.client.r2d2.ObjectFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import java.text.Normalizer;
import java.util.List;

@Service
public class FuncionarioCoasinToR2D2 implements Transformer<Funcionario, com.logicalis.la.integration.client.r2d2.Funcionario> {

    public static final int COASIN = 315119914; // COASIN https://www.dcode.fr/letter-number-cipher
    public static final int ID_CARGO_TOTVS = COASIN;
    private Log log = org.apache.commons.logging.LogFactory.getLog(FuncionarioCoasinToR2D2.class);

    @Override
    public com.logicalis.la.integration.client.r2d2.Funcionario transform(Funcionario from) throws Exception {
        com.logicalis.la.integration.client.r2d2.Funcionario to = new com.logicalis.la.integration.client.r2d2.Funcionario();
        ObjectFactory factory = new ObjectFactory();

        to.setApelido(factory.createFuncionarioApelido(from.getApelido() == null ? from.getNome().split("\\s+")[0] : from.getApelido()));
        to.setApontamentoViaRelogioPonto(Boolean.parseBoolean(from.getApontamento_via_relogio_ponto()));
        to.setBairro(factory.createFuncionarioBairro(from.getBairro()));
        // BairroLocalidade
        if (null != from.getBairro_localidade() &&
                from.getBairro_localidade().trim().length() > 0) {
            to.setBairroLocalidade(from.getBairro_localidade());
        } else {
            to.setBairroLocalidade("N/A");
        }

        to.setCargoConfianca(1);
        to.setCep(factory.createFuncionarioCep(from.getCep()));
        to.setCepLocalidade("99999");
        to.setCnpj(factory.createFuncionarioCnpj(from.getCnpj()));
        // Codigo Categoria Horaria
        if (from.getCodigo_categoria_horaria() == null || from.getCodigo_categoria_horaria().trim().length() == 0) {
            to.setCodigoCategoriaHoraria(999);
        } else {
            to.setCodigoCategoriaHoraria(Integer.parseInt(from.getCodigo_categoria_horaria()));
        }
        // Codigo Lotacao
        if (from.getDescricao_lotacao() != null && from.getDescricao_lotacao().trim().length() > 0) {
            to.setCodigoLotacao(Integer.toString(from.getDescricao_lotacao().hashCode()));
        } else {
            to.setCodigoLotacao("N/A");
        }

        to.setComplemento(factory.createFuncionarioComplemento(from.getComplemento()));
        to.setComplementoLocalidade(factory.createFuncionarioComplementoLocalidade(from.getComplemento_localidade()));
        to.setCpf(factory.createFuncionarioCpf(from.getCpf()));
        to.setDataAdmissao(DatatypeFactory.newInstance().newXMLGregorianCalendar(from.getData_admissao()));
        to.setDataNascimento(DatatypeFactory.newInstance().newXMLGregorianCalendar(from.getData_nascimento()));
        to.setDataDemissao(null);
        to.setDescricaoCargoTotvs("N/A");
        to.setDescricaoCategoriaHoraria("N/A");
        to.setDescricaoDisciplina("N/A");
        to.setDescricaoEmpresa(from.getDescricao_empresa());
        to.setDescricaoLotacao("N/A");
        to.setEmail(from.getEmail());
        to.setEndereco(factory.createFuncionarioEndereco("N/A"));
        to.setEnderecoLocalidade("N/A");
        to.setIdCargoTotvs(ID_CARGO_TOTVS);
        // Id Cidade Funcionario
        String id_cidade = from.getNome_localidade_pais() + from.getNome_uf_localidade() + from.getNome_cidade_localidade();
        id_cidade = removerAcentos(id_cidade);
        to.setIdCidadeFuncionarioTotvs(factory.createFuncionarioIdCidadeFuncionarioTotvs(id_cidade.hashCode()));
        // Id Cidade Localidade
        to.setIdCidadeLocalidadeTotvs(id_cidade.hashCode());
        //to.setIdDisciplinaTotvs(Integer.parseInt(from.getId_disciplina_totvs()));
        to.setIdDisciplinaTotvs(99999999);
        to.setIdEmpresaTotvs(from.getId_empresa_totvs());
        to.setIdLocalidadeBaseTotvs(from.getId_empresa_totvs() + "_" + 1); // ID LEGADO NA TAB. LOCALIDADE DO RD!
        to.setIdCidadeLocalidadeTotvs(from.getNome_cidade_localidade().hashCode());
        to.setIdPaisLocalidadeTotvs(from.getNome_localidade_pais().hashCode());
        to.setIdUfLocalidadeTotvs(from.getNome_uf_localidade().hashCode());
        to.setLocalePais(/*from.getLocale_pais()*/"es");
        to.setNome(from.getNome());
        to.setNomeCidadeLocalidade(from.getNome_cidade_localidade());
        to.setNomeLocalidade(from.getNome_cidade_localidade());
        to.setNomeLocalidadePais(from.getNome_localidade_pais());
        to.setNomeUfLocalidade(from.getNome_uf_localidade());
        to.setNumeroLocalidade(factory.createFuncionarioNumeroLocalidade("N/A"));
        to.setRegistro(from.getRegistro());
        to.setRegistroGestor(factory.createFuncionarioRegistroGestor("25140240"));
        to.setRg("N/A");
        to.setPis(factory.createFuncionarioPis(""));
        to.setRamal(factory.createFuncionarioRamal(null));


        return to;
    }

    public com.logicalis.la.integration.client.r2d2.Funcionarios transformAll(Funcionarios funcionarios) {
        com.logicalis.la.integration.client.r2d2.Funcionarios resp = new com.logicalis.la.integration.client.r2d2.Funcionarios();
        List<com.logicalis.la.integration.client.r2d2.Funcionario> funcionarioList = resp.getFuncionario();
        Funcionario last = null;
        for (Funcionario coasin : funcionarios.getFuncionarios()) {
            try {
                funcionarioList.add(this.transform(coasin));
                last = coasin;
            } catch (Exception e) {
                log.error(e);
            }
        }
        if (last != null) {
            try {

                com.logicalis.la.integration.client.r2d2.Funcionario coringa = this.transform(last);
                coringa.setNome("CoasinLogicalis Prueblas");
                JAXBElement<String> apelido = coringa.getApelido();
                apelido.setValue("CL Pruebas");
                coringa.setApelido(apelido);
                coringa.setEmail("clpruebas@coasinlogicalis.com");
                coringa.setRegistro("200999");
                funcionarioList.add(coringa);

            } catch (Exception e) {
                log.error(e);
            }
        }


        return resp;
    }

    private String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
