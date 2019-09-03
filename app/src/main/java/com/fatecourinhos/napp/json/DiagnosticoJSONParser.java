package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Diagnostico;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoJSONParser {

    public static List<Diagnostico> parseDados(String content){

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Diagnostico> diagnosticos = new ArrayList<>();

            Diagnostico diagnostico;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                diagnostico = new Diagnostico();
                diagnostico.setIdDiagostico(jsonObject.getInt("idDiagnostico"));
                diagnostico.setNomeDiagnotico(jsonObject.getString("nomeDiagnostico"));

                diagnosticos.add(diagnostico);
            }

            return diagnosticos;

        } catch (Exception e) {
            return null;
        }
    }
    
}
