package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.LocalAtendimentoJSONParser;
import com.fatecourinhos.napp.model.DiagnosticoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class LocalAtendimentoController {


    public static boolean sucesso;
    public static List<LocalAtendimentoModel> locaisAtendimento;

    public static boolean inserir(LocalAtendimentoModel localAtendimento) {

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        CadLocalAtendimento task = new CadLocalAtendimento();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<LocalAtendimentoModel> buscarDiagnostico() {

        String uri = "";

        SelectLocalAtendimento mytask = new SelectLocalAtendimento();
        mytask.execute(uri);

        return locaisAtendimento;
    }

    private static class SelectLocalAtendimento extends AsyncTask<String, String, List<LocalAtendimentoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LocalAtendimentoModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            locaisAtendimento = LocalAtendimentoJSONParser.parseDados(conteudo);

            return locaisAtendimento;
        }

        @Override
        protected void onPostExecute(final List<LocalAtendimentoModel> locaisAtendimento) {
            super.onPostExecute(locaisAtendimento);

        }
    }

    private static class CadLocalAtendimento extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {

            sucesso = true;

        }
    }
}
