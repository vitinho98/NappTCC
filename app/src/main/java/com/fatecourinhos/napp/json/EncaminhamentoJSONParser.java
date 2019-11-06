package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Encaminhamento;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.model.Responsavel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EncaminhamentoJSONParser {

    public static List<Encaminhamento> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Encaminhamento> encaminhamentos = new ArrayList<>();

            Encaminhamento encaminhamento;
            ProfissionalExterno profissionalExterno;
            Profissional profissional;
            Responsavel responsavel;
            CampoAtuacao campoAtuacao;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                encaminhamento = new Encaminhamento();
                profissional = new Profissional();
                responsavel = new Responsavel();
                campoAtuacao = new CampoAtuacao();
                profissionalExterno = new ProfissionalExterno();

                profissionalExterno.setIdProfissionalExterno(jsonObject.getInt("idProfissionalExterno"));
                profissionalExterno.setNomeProfissionalExterno(jsonObject.getString("nomeProfissionalExterno"));
                profissionalExterno.setCidadeProfissionalExterno(jsonObject.getString("cidadeProfissionalExterno"));
                profissionalExterno.setBairro(jsonObject.getString("bairro"));
                profissionalExterno.setEndereco(jsonObject.getString("endereco"));
                profissionalExterno.setNumero(jsonObject.getString("numero"));
                profissionalExterno.setEmailProfissionalExterno(jsonObject.getString("emailProfissionalExterno"));
                profissionalExterno.setTelefoneProfissionalExterno(jsonObject.getString("telefoneProfissionalExterno"));
                profissionalExterno.setCelularProfissionalExterno(jsonObject.getString("celularProfissionalExterno"));

                try {

                    campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                    campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                    profissionalExterno.setFkCampoAtuacao(campoAtuacao);

                } catch (Exception e) {
                    profissionalExterno.setFkCampoAtuacao(null);
                }

                try {

                    responsavel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                    responsavel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                    responsavel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                    responsavel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                    responsavel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));
                    profissionalExterno.setFkResponsavel(responsavel);

                } catch (Exception e) {
                    profissionalExterno.setFkResponsavel(null);
                }

                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));

                encaminhamento.setFkProfissional(profissional);
                encaminhamento.setFkProfissionalExterno(profissionalExterno);

            }

            return encaminhamentos;

        } catch (Exception e) {
            return null;
        }

    }

}
