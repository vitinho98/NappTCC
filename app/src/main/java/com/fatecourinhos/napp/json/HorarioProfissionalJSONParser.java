package com.fatecourinhos.napp.json;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fatecourinhos.napp.model.Horario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HorarioProfissionalJSONParser {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Horario> parseDados(String content) {

        String dia, mes, ano, hora, minuto;

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Horario> agendaProfissionalList = new ArrayList<>();
            Horario agendaProfissional;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                agendaProfissional = new Horario();

                String data = jsonObject.getString("dataHora");
                ano = data.substring(0,4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
                hora = data.substring(11,13);
                minuto = data.substring(14,16);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minuto));

                agendaProfissional.setIdAgendaProfissional(jsonObject.getInt("idHorarioProfissional"));
                agendaProfissional.setData(calendar.getTime());

                agendaProfissionalList.add(agendaProfissional);

            }

            return agendaProfissionalList;

        } catch (Exception e) {
            return null;
        }
    }

}
