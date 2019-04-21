package com.fatecourinhos.napp.json;

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

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                agendaProfissionalModel.setDiaDaSemana(jsonObject.getString("diaDaSemana"));
                agendaProfissionalModel.setHorario(jsonObject.getString("horario"));
                agendaProfissionalModel.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));

                agendaProfissionalList.add(agendaProfissionalModel);
            }

            return agendaProfissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
