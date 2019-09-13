package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioJSONParser {

    public static List<Usuario> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            Usuario usuario;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                usuario = new Usuario();
                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipoUsuario"));
                usuario.setStatus(jsonObject.getInt("status"));
                //0 --> ATIVO

                usuarioList.add(usuario);
            }

            return usuarioList;

        }catch (Exception e) {
            return null;
        }
    }

}
