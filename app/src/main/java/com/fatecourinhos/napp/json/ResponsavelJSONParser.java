package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Responsavel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponsavelJSONParser {

    public static List<Responsavel> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Responsavel> responsaveis = new ArrayList<>();

            Responsavel responsavel;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                responsavel = new Responsavel();
                responsavel.setCelularResponsavel(jsonObject.getString("celularResponsavel"));
                responsavel.setEmailResponsavel(jsonObject.getString("emailResponsavel"));
                responsavel.setIdResponsavel(jsonObject.getInt("idResponsavel"));
                responsavel.setNomeResponsavel(jsonObject.getString("nomeResponsavel"));
                responsavel.setTelefoneResponsavel(jsonObject.getString("telefoneResponsavel"));

                responsaveis.add(responsavel);

            }

            return responsaveis;

        } catch (Exception e) {
            return null;
        }

    }

}
