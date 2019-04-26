package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CampoAtuacaoJSONParser {

    public static List<CampoAtuacaoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<CampoAtuacaoModel> campoAtuacaoList = new ArrayList<>();

            CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                campoAtuacaoModel.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacaoModel.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));

                campoAtuacaoList.add(campoAtuacaoModel);

            }

            return campoAtuacaoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
