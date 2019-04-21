package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.DiagnosticoJSONParser;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.DiagnosticoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class DiagnosticoController {

    public static boolean sucesso;
    public static List<DiagnosticoModel> diagnosticos;

    public static boolean inserir(DiagnosticoModel diagnostico){

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnotico());

        CadDiagnostico task = new CadDiagnostico();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<DiagnosticoModel> buscarDiagnostico(){

        String uri = "";

        SelectDiagnostico mytask = new SelectDiagnostico();
        mytask.execute(uri);

        return diagnosticos;
    }

    private static class SelectDiagnostico extends AsyncTask<String, String, List<DiagnosticoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<DiagnosticoModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            diagnosticos = DiagnosticoJSONParser.parseDados(conteudo);

            return diagnosticos;
        }

        @Override
        protected void onPostExecute(final List<DiagnosticoModel> diagnosticos) {
            super.onPostExecute(diagnosticos);

        }
    }

    private static class CadDiagnostico extends AsyncTask<RequestHttp, String, String> {
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
