package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.RelatorioFeedback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFeedbackJSONParser {

    public static List<RelatorioFeedback> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<RelatorioFeedback> relatorios = new ArrayList<>();

            RelatorioFeedback relatorio;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                relatorio = new RelatorioFeedback();
                relatorio.setData(jsonObject.getString("dataHora"));
                relatorio.setNomeAluno(jsonObject.getString("nomeAluno"));
                relatorio.setOpcao1(jsonObject.getString("opcao1"));
                relatorio.setOpcao2(jsonObject.getString("opcao2"));

                relatorios.add(relatorio);

            }

            return relatorios;

        } catch (Exception e) {
            return null;
        }

    }
}
