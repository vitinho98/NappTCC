package com.fatecourinhos.napp.json;

import android.os.CpuUsageInfo;

import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendaProfissionalJSONParser {

    public static List<AgendaProfissionalModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<AgendaProfissionalModel> agendaProfissionalList = new ArrayList<>();

<<<<<<< HEAD
            AgendaProfissionalModel agendaProfissionalModel = new AgendaProfissionalModel();
            ProfissionalModel profissionalModel = new ProfissionalModel();
            UsuarioModel usuarioModel = new UsuarioModel();
            CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
=======
            ProfissionalModel profissional = new ProfissionalModel();
            UsuarioModel usuario = new UsuarioModel();
            CampoAtuacaoModel campoAtuacao = new CampoAtuacaoModel();
            AgendaProfissionalModel agendaProfissional = new AgendaProfissionalModel();
>>>>>>> b16e1f8f21dc2b931eaf8d51f9c4da8a1aca1255

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));

<<<<<<< HEAD
                profissionalModel.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissionalModel.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissionalModel.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissionalModel.setEmailProfissional(jsonObject.getString("emailProfissional"));

                usuarioModel.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuarioModel.setLogin(jsonObject.getString("login"));
                usuarioModel.setSenha(jsonObject.getString("senha"));
                usuarioModel.setTipoUsuario(jsonObject.getString("tipo"));
                usuarioModel.setStatus(jsonObject.getInt("status"));
                profissionalModel.setFkUsuario(usuarioModel);

                campoAtuacaoModel.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacaoModel.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                profissionalModel.setCampoAtuacao(campoAtuacaoModel);

                agendaProfissionalModel.setFkProfissional(profissionalModel);

                agendaProfissionalList.add(agendaProfissionalModel);
=======
                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipo"));
                usuario.setStatus(jsonObject.getInt("status"));
                profissional.setFkUsuario(usuario);

                campoAtuacao.setNomeCampoAtuacao(jsonObject.getString("nomeCampoAtuacao"));
                campoAtuacao.setIdCampoAtuacao(jsonObject.getInt("idCampoAtuacao"));
                profissional.setCampoAtuacao(campoAtuacao);

                agendaProfissional.setDiaDaSemana(jsonObject.getString("diaDaSemana"));
                agendaProfissional.setHora(jsonObject.getString("hora"));
                agendaProfissional.setMinutos(jsonObject.getString("minutos"));
                agendaProfissional.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));

                agendaProfissionalList.add(agendaProfissional);
>>>>>>> b16e1f8f21dc2b931eaf8d51f9c4da8a1aca1255
            }

            return agendaProfissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
