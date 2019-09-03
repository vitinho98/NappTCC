package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.LocalAtendimento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalAtendimentoJSONParser {

    public static List<LocalAtendimento> parseDados(String content){

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<LocalAtendimento> locaisAtendimento = new ArrayList<>();

            LocalAtendimento localAtendimento;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                localAtendimento = new LocalAtendimento();
                localAtendimento.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                localAtendimento.setNomeBloco(jsonObject.getString("nomeBloco"));
                localAtendimento.setNomeLocal(jsonObject.getString("nomeLocal"));

                locaisAtendimento.add(localAtendimento);
            }

            return locaisAtendimento;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
