package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaProfissionalJSONParser {

    public static List<AgendaProfissional> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<AgendaProfissional> agendaProfissionalList = new ArrayList<>();
            AgendaProfissional agendaProfissional = new AgendaProfissional();

            Profissional profissionalModel = new Profissional();
            Usuario usuario = new Usuario();
            CampoAtuacao campoAtuacao = new CampoAtuacao();
            Profissional profissional = new Profissional();

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
                profissionalModel.setFkUsuario(usuario);


                agendaProfissional.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));
                agendaProfissional.setData(new Date(jsonObject.getString("dataHora")));
                agendaProfissional.setFkProfissional(profissionalModel);

                agendaProfissionalList.add(agendaProfissional);

            }

            return agendaProfissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
