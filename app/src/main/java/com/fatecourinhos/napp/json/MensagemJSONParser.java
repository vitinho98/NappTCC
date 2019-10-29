package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Mensagem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MensagemJSONParser {

    public static List<Mensagem> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Mensagem> mensagens = new ArrayList<>();

            Mensagem mensagem;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                mensagem = new Mensagem();

                mensagens.add(mensagem);

            }

            return mensagens;

        } catch (Exception e) {
            return null;
        }
    }

}
