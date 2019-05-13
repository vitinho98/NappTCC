package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.ResponsavelModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponsavelJSONParser {

    public static List<ResponsavelModel> parseDados(String content) {
        try {

            JSONArray jsonArray = new JSONArray(content);
            List<ResponsavelModel> responsavelList = new ArrayList<>();

            ResponsavelModel responsavel = new ResponsavelModel();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                responsavel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                responsavel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                responsavel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));

                responsavelList.add(responsavel);

                responsavelList.add(responsavelModel);

            }

            return responsavelList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
