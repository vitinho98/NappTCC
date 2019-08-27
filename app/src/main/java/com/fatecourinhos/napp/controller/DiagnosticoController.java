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
}
