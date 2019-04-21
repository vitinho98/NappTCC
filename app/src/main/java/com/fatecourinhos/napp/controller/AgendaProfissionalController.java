package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.AgendaProfissionalJSONParser;
import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class AgendaProfissionalController {

    public static boolean sucesso;
    public static List<AgendaProfissionalModel> agendasProfissional;

    public static boolean inserir(AgendaProfissionalModel agendaProfissional) {

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("diaDaSemana", agendaProfissional.getDiaDaSemana());
        requestHttp.setParametro("horario", agendaProfissional.getHorario());
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        CadAgendaProfissional task = new CadAgendaProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<AgendaProfissionalModel> buscarAgendaProfissional() {

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        SelectAgendaProfissional mytask = new SelectAgendaProfissional();
        mytask.execute(uri);

        return agendasProfissional;
    }

    private static class SelectAgendaProfissional extends AsyncTask<String, String, List<AgendaProfissionalModel>> {

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

    private static class CadAgendaProfissional extends AsyncTask<RequestHttp, String, String> {
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
