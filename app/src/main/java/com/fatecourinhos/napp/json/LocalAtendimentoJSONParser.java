package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalAtendimentoJSONParser {

    public static List<LocalAtendimentoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<LocalAtendimentoModel> localAtendimentoList = new ArrayList<>();

            LocalAtendimentoModel localAtendimentoModel = new LocalAtendimentoModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                localAtendimentoModel.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                localAtendimentoModel.setNomeBloco(jsonObject.getString("nomeBloco"));
                localAtendimentoModel.setNomeLocal(jsonObject.getString("nomeLocal"));

                localAtendimentoList.add(localAtendimentoModel);
            }

            return localAtendimentoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
