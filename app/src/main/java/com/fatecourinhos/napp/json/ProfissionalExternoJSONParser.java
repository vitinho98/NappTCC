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

                profissionalExterno.setBairro(jsonObject.getString("bairro"));
                profissionalExterno.setCelularProfissionalExterno(jsonObject.getString("celularProfissionalExterno"));
                profissionalExterno.setTelefoneProfissionalExterno(jsonObject.getString("telefoneProfissionalExterno"));
                profissionalExterno.setCidadeProfissionalExterno(jsonObject.getString("cidadeProfissionalExterno"));
                profissionalExterno.setEndereco(jsonObject.getString("endereco"));
                profissionalExterno.setIdProfissionalExterno(jsonObject.getInt("idProfissionalExterno"));
                profissionalExterno.setNumero(jsonObject.getString("numero"));
                profissionalExterno.setEmailProfissionalExterno(jsonObject.getString("emailProfissionalExterno"));
                profissionalExterno.setNomeProfissionalExterno(jsonObject.getString("nomeProfissionalExterno"));

                campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                profissionalExterno.setFkCampoAtuacao(campoAtuacao);

                responsavel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
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
