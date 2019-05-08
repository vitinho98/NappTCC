package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;
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

            UsuarioModel usuario = new UsuarioModel();
            CampoAtuacaoModel campoAtuacao = new CampoAtuacaoModel();
            ProfissionalModel profissional = new ProfissionalModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));

                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipo"));
                usuario.setStatus(jsonObject.getInt("status"));
                profissional.setFkUsuario(usuario);

                campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                profissional.setCampoAtuacao(campoAtuacao);

                profissionalList.add(profissional);

            }

            return profissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
