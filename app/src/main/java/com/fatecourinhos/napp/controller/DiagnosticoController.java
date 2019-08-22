package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.DiagnosticoJSONParser;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class DiagnosticoController {

    public static boolean sucesso;
    public static List<Diagnostico> diagnosticos;

    public static boolean inserirDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/inserirDiagnostico.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnotico());

        InserirDiagnostico task = new InserirDiagnostico();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/alterarDiagnostico.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idDiagnostico", String.valueOf(diagnostico.getIdDiagostico()));
        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnotico());

        AlterarDiagnostico task = new AlterarDiagnostico();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<Diagnostico> selecionarDiagnosticos(){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/selecionarDiagnosticos.php";

        SelecionarDiagnosticos mytask = new SelecionarDiagnosticos();
        mytask.execute(uri);

        return diagnosticos;
    }

    private static class SelecionarDiagnosticos extends AsyncTask<String, String, List<Diagnostico>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Diagnostico> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            diagnosticos = DiagnosticoJSONParser.parseDados(conteudo);

            return diagnosticos;
        }

        @Override
        protected void onPostExecute(final List<Diagnostico> diagnosticos) {
            super.onPostExecute(diagnosticos);

        }
    }

    private static class InserirDiagnostico extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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

    private static class AlterarDiagnostico extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
