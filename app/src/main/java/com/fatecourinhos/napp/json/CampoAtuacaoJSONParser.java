package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CampoAtuacaoJSONParser {

    public static List<CampoAtuacao> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<CampoAtuacao> campoAtuacaoList = new ArrayList<>();
            CampoAtuacao campoAtuacao;

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                campoAtuacao = new CampoAtuacao();
                campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));

                campoAtuacaoList.add(campoAtuacao);

            }

            return campoAtuacaoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
