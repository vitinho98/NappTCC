package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.DiagnosticoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoJSONParser {

    public static List<DiagnosticoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<DiagnosticoModel> diagnosticoList = new ArrayList<>();

            DiagnosticoModel diagnostico= new DiagnosticoModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                diagnostico.setIdDiagostico(jsonObject.getInt("idDiagnostico"));
                diagnostico.setNomeDiagnotico(jsonObject.getString("nomeDiagnostico"));

                diagnosticoList.add(diagnostico);
            }

            return diagnosticoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
