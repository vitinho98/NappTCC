package com.fatecourinhos.napp.controller;

import com.fatecourinhos.napp.model.ProfissionalModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalJSONParser {
    public static List<ProfissionalModel> parseDado(String content){
        try{

            JSONArray jsonArray = new JSONArray();
            List<ProfissionalModel> profissionalList = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject =jsonArray.getJSONObject(i);
                ProfissionalModel profissional = new ProfissionalModel();

                profissional.setIdProfissional(jsonObject.getInt("id"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));

            }


            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }

}
