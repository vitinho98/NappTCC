package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.LocalAtendimento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalAtendimentoJSONParser {

    public static List<LocalAtendimento> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<LocalAtendimento> localAtendimentoList = new ArrayList<>();

            LocalAtendimento localAtendimento = new LocalAtendimento();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                localAtendimento.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                localAtendimento.setNomeBloco(jsonObject.getString("nomeBloco"));
                localAtendimento.setNomeLocal(jsonObject.getString("nomeLocal"));

                localAtendimentoList.add(localAtendimento);
            }

            return localAtendimentoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
