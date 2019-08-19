package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendaProfissionalJSONParser {

    public static List<AgendaProfissionalModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<AgendaProfissionalModel> agendaProfissionalList = new ArrayList<>();
            AgendaProfissionalModel agendaProfissionalModel = new AgendaProfissionalModel();

            Profissional profissionalModel = new Profissional();
            Usuario usuario = new Usuario();
            CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
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

                campoAtuacaoModel.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacaoModel.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                profissionalModel.setCampoAtuacao(campoAtuacaoModel);

                agendaProfissionalModel.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));
                agendaProfissionalModel.setDiaDaSemana(jsonObject.getString("diaDaSemana"));
                agendaProfissionalModel.setHora(jsonObject.getString("hora"));
                agendaProfissionalModel.setMinutos(jsonObject.getString("minutos"));
                agendaProfissionalModel.setFkProfissional(profissionalModel);

                agendaProfissionalList.add(agendaProfissionalModel);

            }

            return agendaProfissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
