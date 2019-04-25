package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioJSONParser {

    public static List<UsuarioModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<UsuarioModel> usuarioModelList = new ArrayList<>();

            UsuarioModel usuarioModel = new UsuarioModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                usuarioModel.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuarioModel.setLogin(jsonObject.getString("login"));
                usuarioModel.setSenha(jsonObject.getString("senha"));
                usuarioModel.setTipoUsuario(jsonObject.getString("tipoUsuario"));
                usuarioModel.setStatus(jsonObject.getInt("status"));
                //0 --> ATIVO

                usuarioModelList.add(usuarioModel);
            }

            return usuarioModelList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
