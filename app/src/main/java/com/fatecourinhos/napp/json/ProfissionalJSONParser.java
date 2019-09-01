package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalJSONParser {

    public static List<Profissional> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<Profissional> profissionalList = new ArrayList<>();

            Usuario usuario = new Usuario();
            CampoAtuacao campoAtuacao = new CampoAtuacao();
            Profissional profissional = new Profissional();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

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

                try{
                    campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                    campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                    profissional.setCampoAtuacao(campoAtuacao);

                }catch (Exception e){
                    campoAtuacao.setNomeCampoAtuacao(null);
                    campoAtuacao.setIdCampoAtuacao(0);
                    profissional.setCampoAtuacao(campoAtuacao);

                    e.printStackTrace();
                }

                profissionalList.add(profissional);

            }

            return profissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
