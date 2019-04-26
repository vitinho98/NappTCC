package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalExternoJSONParser {

    public static List<ProfissionalExternoModel> parseDados(String content) {
        try {

            JSONArray jsonArray = new JSONArray(content);
            List<ProfissionalExternoModel> profissionaExternolList = new ArrayList<>();

            ResponsavelModel responsavelModel = new ResponsavelModel();
            CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
            ProfissionalExternoModel profissionalExternoModel = new ProfissionalExternoModel();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                profissionalExternoModel.setBairro(jsonObject.getString("bairro"));
                profissionalExternoModel.setCelularProfissionalExterno(jsonObject.getString("celularProfissionalExterno"));
                profissionalExternoModel.setTelefoneProfissionalExterno(jsonObject.getString("telefoneProfissionalExterno"));
                profissionalExternoModel.setCidadeProfissionalExterno(jsonObject.getString("cidadeProfissionalExterno"));
                profissionalExternoModel.setEndereco(jsonObject.getString("endereco"));
                profissionalExternoModel.setIdProfissionalExterno(jsonObject.getInt("idProfissionalExterno"));
                profissionalExternoModel.setNumero(jsonObject.getString("numero"));
                profissionalExternoModel.setEmailProfissionalExterno(jsonObject.getString("emailProfissionalExterno"));
                profissionalExternoModel.setNomeProfissionalExterno(jsonObject.getString("nomeProfissionalExterno"));

                campoAtuacaoModel.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                campoAtuacaoModel.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                profissionalExternoModel.setCampoAtuacao(campoAtuacaoModel);

                responsavelModel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavelModel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavelModel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                responsavelModel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                responsavelModel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));
                profissionalExternoModel.setFkResponsavel(responsavelModel);

                profissionaExternolList.add(profissionalExternoModel);

            }

            return profissionaExternolList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
