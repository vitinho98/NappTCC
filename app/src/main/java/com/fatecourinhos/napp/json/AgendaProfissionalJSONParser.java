package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaProfissionalJSONParser {

    public static List<AgendaProfissional> parseDados(String content){
        try{
            SimpleDateFormat formatDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

            JSONArray jsonArray = new JSONArray(content);
            List<AgendaProfissional> agendaProfissionalList = new ArrayList<>();
            AgendaProfissional agendaProfissional;

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                agendaProfissional = new AgendaProfissional();

                agendaProfissional.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));
                agendaProfissional.setData(formatDataHora.parse(jsonObject.getString("dataHora")));

                agendaProfissionalList.add(agendaProfissional);

            }

            return agendaProfissionalList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
