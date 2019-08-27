package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.LocalAtendimentoJSONParser;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class LocalAtendimentoController {


    public static boolean sucesso;
    public static List<LocalAtendimento> locaisAtendimento;


    public static List<LocalAtendimento> selecionarLocalAtendimento() {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/selecionarLocaisAtendimento.php";

        SelecionarLocaisAtendimento mytask = new SelecionarLocaisAtendimento();
        mytask.execute(uri);

        return locaisAtendimento;
    }

    private static class SelecionarLocaisAtendimento extends AsyncTask<String, String, List<LocalAtendimento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LocalAtendimento> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            locaisAtendimento = LocalAtendimentoJSONParser.parseDados(conteudo);

            return locaisAtendimento;
        }

        @Override
        protected void onPostExecute(final List<LocalAtendimento> locaisAtendimento) {
            super.onPostExecute(locaisAtendimento);

        }
    }

}
