package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.LocalAtendimentoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalAtendimentoJSONParser {

    public static List<LocalAtendimentoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<LocalAtendimentoModel> localAtendimentoList = new ArrayList<>();

            LocalAtendimentoModel localAtendimento = new LocalAtendimentoModel();

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
