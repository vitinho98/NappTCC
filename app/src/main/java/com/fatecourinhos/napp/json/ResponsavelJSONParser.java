package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponsavelJSONParser {

    public static List<ResponsavelModel> parseDados(String content) {
        try {

            JSONArray jsonArray = new JSONArray(content);
            List<ResponsavelModel> responsavelList = new ArrayList<>();

            ResponsavelModel responsavelModel = new ResponsavelModel();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                responsavelModel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavelModel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavelModel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                responsavelModel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                responsavelModel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));

            }

            return responsavelList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
