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

    public static boolean inserirLocalAtendimento(LocalAtendimentoModel localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/inserirLocalAtendimento.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        InserirLocalAtendimento task = new InserirLocalAtendimento();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarLocalAtendimento(LocalAtendimentoModel localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/alterarLocalAtendimento.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idLocalAtendimento", String.valueOf(localAtendimento.getIdLocalAtendimento()));
        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        AlterarLocalAtendimento task = new AlterarLocalAtendimento();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<LocalAtendimentoModel> selecionarLocalAtendimento() {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/selecionarLocaisAtendimento.php";

        SelecionarLocaisAtendimento mytask = new SelecionarLocaisAtendimento();
        mytask.execute(uri);

        return locaisAtendimento;
    }

    private static class SelecionarLocaisAtendimento extends AsyncTask<String, String, List<LocalAtendimentoModel>> {

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

    private static class InserirLocalAtendimento extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private static class AlterarLocalAtendimento extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
