package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.DiagnosticoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoJSONParser {

    public static List<DiagnosticoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<DiagnosticoModel> diagnosticoList = new ArrayList<>();

            DiagnosticoModel diagnosticoModel= new DiagnosticoModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                diagnosticoModel.setIdDiagostico(jsonObject.getInt("idDiagnostico"));
                diagnosticoModel.setNomeDiagnotico(jsonObject.getString("nomeDiagnostico"));

                diagnosticoList.add(diagnosticoModel);
            }

            return diagnosticoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
