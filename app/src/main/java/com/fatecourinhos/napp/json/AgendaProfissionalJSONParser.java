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
            AgendaProfissionalModel agendaProfissionalModel = new AgendaProfissionalModel();

            ProfissionalModel profissionalModel = new ProfissionalModel();
            UsuarioModel usuarioModel = new UsuarioModel();
            CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
            ProfissionalModel profissional = new ProfissionalModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));

                usuarioModel.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuarioModel.setLogin(jsonObject.getString("login"));
                usuarioModel.setSenha(jsonObject.getString("senha"));
                usuarioModel.setTipoUsuario(jsonObject.getString("tipo"));
                usuarioModel.setStatus(jsonObject.getInt("status"));
                profissionalModel.setFkUsuario(usuarioModel);

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
