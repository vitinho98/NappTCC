package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.AgendaProfissionalJSONParser;
import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class AgendaProfissionalController {

    public static boolean sucesso;
    public static List<AgendaProfissionalModel> agendasProfissional;

    public static boolean inserirAgendaProfissional(AgendaProfissionalModel agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/inserirAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("diaDaSemana", agendaProfissional.getDiaDaSemana());
        requestHttp.setParametro("horario", agendaProfissional.getHora());
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        InserirAgendaProfissional task = new InserirAgendaProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarAgendaProfissional(AgendaProfissionalModel agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/alterarAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idAgendaUsuario", String.valueOf(agendaProfissional.getIdAgendaProfissional()));
        requestHttp.setParametro("diaDaSemana", agendaProfissional.getDiaDaSemana());
        requestHttp.setParametro("horario", agendaProfissional.getHora());
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        AlterarAgendaProfissional task = new AlterarAgendaProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<AgendaProfissionalModel> selecionarAgendaProfissional() {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/selecionarAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        SelecionarAgendaProfissional mytask = new SelecionarAgendaProfissional();
        mytask.execute(uri);

        return agendasProfissional;
    }

    private static class SelecionarAgendaProfissional extends AsyncTask<String, String, List<AgendaProfissionalModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<AgendaProfissionalModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            agendasProfissional = AgendaProfissionalJSONParser.parseDados(conteudo);

            return agendasProfissional;
        }

        @Override
        protected void onPostExecute(final List<AgendaProfissionalModel> agendasProfissional) {
            super.onPostExecute(agendasProfissional);

        }
    }

    private static class InserirAgendaProfissional extends AsyncTask<RequestHttp, String, String> {
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
