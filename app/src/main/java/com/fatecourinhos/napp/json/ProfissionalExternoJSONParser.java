package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.model.Responsavel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalExternoJSONParser {

    public static List<ProfissionalExterno> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<ProfissionalExterno> profissionaisExternos = new ArrayList<>();

            Responsavel responsavel;
            CampoAtuacao campoAtuacao;
            ProfissionalExterno profissionalExterno;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

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

                campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                profissionalExterno.setFkCampoAtuacao(campoAtuacao);

                responsavel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                responsavel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                responsavel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));
                profissionalExterno.setFkResponsavel(responsavel);

                profissionaisExternos.add(profissionalExterno);

            }

            return profissionaisExternos;

        } catch (Exception e) {
            return null;
        }
    }

}
