package com.fatecourinhos.napp.json;

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

            UsuarioModel usuario = new UsuarioModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipoUsuario"));
                usuario.setStatus(jsonObject.getInt("status"));
                //0 --> ATIVO

                usuarioModelList.add(usuario);
            }

            return usuarioModelList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
