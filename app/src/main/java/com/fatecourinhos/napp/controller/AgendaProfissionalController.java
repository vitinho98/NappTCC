package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.AgendaProfissionalJSONParser;
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class AgendaProfissionalController {

    public static boolean sucesso;
    public static List<AgendaProfissional> agendasProfissional;



    public static boolean alterarAgendaProfissional(AgendaProfissional agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/alterarAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        //TODO
        requestHttp.setParametro("idAgendaUsuario", String.valueOf(agendaProfissional.getIdAgendaProfissional()));
        requestHttp.setParametro("horario", String.valueOf(agendaProfissional.getData()));
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        AlterarAgendaProfissional task = new AlterarAgendaProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<AgendaProfissional> selecionarAgendaProfissional() {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/selecionarAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        SelecionarAgendaProfissional mytask = new SelecionarAgendaProfissional();
        mytask.execute(uri);

        return agendasProfissional;
    }

    private static class SelecionarAgendaProfissional extends AsyncTask<String, String, List<AgendaProfissional>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<AgendaProfissional> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            agendasProfissional = AgendaProfissionalJSONParser.parseDados(conteudo);

            return agendasProfissional;
        }

        @Override
        protected void onPostExecute(final List<AgendaProfissional> agendasProfissional) {
            super.onPostExecute(agendasProfissional);

        }
    }



    private static class AlterarAgendaProfissional extends AsyncTask<RequestHttp, String, String> {
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
