package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.RelatorioAluno;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RelatorioAlunoJSONParser {

    public static List<RelatorioAluno> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<RelatorioAluno> relatorios = new ArrayList<>();

            RelatorioAluno relatorio;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                relatorio = new RelatorioAluno();
                relatorio.setNomeAluno(jsonObject.getString("nomeAluno"));
                relatorio.setQuantidade(jsonObject.getInt("quantidade"));

                relatorios.add(relatorio);

            }

            return relatorios;

        } catch (Exception e) {
            return null;
        }

    }
}
