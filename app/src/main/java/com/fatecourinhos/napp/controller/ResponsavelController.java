package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.ResponsavelJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class ResponsavelController {

    public static boolean sucesso;
    public static List<ResponsavelModel> responsaveis;

    public static boolean inserir(ResponsavelModel responsavel) {

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        CadResponsavel task = new CadResponsavel();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<ResponsavelModel> buscarResponsaveis() {

        String uri = "";

        SelectResponsavel mytask = new SelectResponsavel();
        mytask.execute(uri);

        return responsaveis;
    }

    private static class SelectResponsavel extends AsyncTask<String, String, List<ResponsavelModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ResponsavelModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            responsaveis = ResponsavelJSONParser.parseDados(conteudo);

            return responsaveis;
        }

        @Override
        protected void onPostExecute(final List<ResponsavelModel> responsaveis) {
            super.onPostExecute(responsaveis);

        }
    }

    private static class CadResponsavel extends AsyncTask<RequestHttp, String, String> {
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
