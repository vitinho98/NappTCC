package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalJSONParser {

    public static List<Profissional> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Profissional> profissionais = new ArrayList<>();
            Usuario usuario;
            Profissional profissional ;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                usuario = new Usuario();
                profissional = new Profissional();

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celularProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));

                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipoUsuario"));
                usuario.setStatus(jsonObject.getInt("status"));
                profissional.setFkUsuario(usuario);

                profissionais.add(profissional);
            }

            return profissionais;

        } catch (Exception e) {
            return null;
        }
    }

}
