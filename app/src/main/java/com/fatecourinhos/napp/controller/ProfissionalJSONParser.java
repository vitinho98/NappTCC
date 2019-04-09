package com.fatecourinhos.napp.controller;

import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalJSONParser {
    public static List<ProfissionalModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<ProfissionalModel> profissionalList = new ArrayList<>();
            UsuarioModel usuarioModel = new UsuarioModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject =jsonArray.getJSONObject(i);
                ProfissionalModel profissional = new ProfissionalModel();

                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));
                usuarioModel.setLogin(jsonObject.getString("login"));
                usuarioModel.setSenha(jsonObject.getString("senha"));
                usuarioModel.setTipoUsuario(jsonObject.getString("tipo"));
                usuarioModel.setStatus(jsonObject.getInt("status"));
                profissional.setFkUsuario(usuarioModel);

                profissionalList.add(profissional);

            }


            return profissionalList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }

}
