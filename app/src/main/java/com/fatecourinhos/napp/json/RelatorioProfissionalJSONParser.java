package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.RelatorioProfissional;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RelatorioProfissionalJSONParser {

    public static List<RelatorioProfissional> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<RelatorioProfissional> relatorios = new ArrayList<>();

            RelatorioProfissional relatorio;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                relatorio = new RelatorioProfissional();
                relatorio.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                relatorio.setQuantidade(jsonObject.getInt("quantidade"));

                relatorios.add(relatorio);

            }

            return relatorios;

        } catch (Exception e) {
            return null;
        }

    }
}
