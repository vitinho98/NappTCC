package com.fatecourinhos.napp.json;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fatecourinhos.napp.model.Mensagem;
import com.fatecourinhos.napp.model.Profissional;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MensagemJSONParser {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Mensagem> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Mensagem> mensagens = new ArrayList<>();

            String dia, mes, ano, hora, minuto;
            Profissional profissional;
            Mensagem mensagem;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                profissional = new Profissional();
                mensagem = new Mensagem();

                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                mensagem.setFkProfissional(profissional);
                mensagem.setIdMensagem(jsonObject.getInt("idMensagem"));
                mensagem.setMensagem(jsonObject.getString("mensagem"));

                String data = jsonObject.getString("dataHora");
                ano = data.substring(0,4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
                hora = data.substring(11,13);
                minuto = data.substring(14,16);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minuto));
                mensagem.setDataHora(calendar.getTime());

                mensagens.add(mensagem);

            }

            return mensagens;

        } catch (Exception e) {
            return null;
        }

    }

}
