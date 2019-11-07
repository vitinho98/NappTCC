package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Anamnese;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnamneseJSONParser {

    public static List<Anamnese> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Anamnese> anamneseList = new ArrayList<>();

            Anamnese anamnese;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                anamnese = new Anamnese();
                anamnese.setQuestao1(jsonObject.getString("questao1"));
                anamnese.setQuestao2(jsonObject.getString("questao2"));
                anamnese.setQuestao3(jsonObject.getString("questao3"));
                anamnese.setQuestao4(jsonObject.getString("questao4"));
                anamnese.setQuestao5(jsonObject.getString("questao5"));
                anamnese.setQuestao6(jsonObject.getString("questao6"));
                anamnese.setQuestao7(jsonObject.getString("questao7"));
                anamnese.setQuestao8(jsonObject.getString("questao8"));
                anamnese.setQuestao9(jsonObject.getString("questao9"));

                anamneseList.add(anamnese);

            }

            return anamneseList;

        } catch (Exception e) {
            return null;
        }

    }

}
