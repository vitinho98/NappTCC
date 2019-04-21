package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.DiagnosticoModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class CampoAtuacaoController {


    public static boolean sucesso;
    public static List<CampoAtuacaoModel> camposAtuacao;

    public static boolean inserir(CampoAtuacaoModel campoAtuacao) {

        String uri = "";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        CadCampoAtuacao task = new CadCampoAtuacao();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<CampoAtuacaoModel> buscarCamposAtuacao() {

        String uri = "";

        SelectCampoAtuacao mytask = new SelectCampoAtuacao();
        mytask.execute(uri);

        return camposAtuacao;
    }

    private static class SelectCampoAtuacao extends AsyncTask<String, String, List<CampoAtuacaoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<CampoAtuacaoModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            camposAtuacao = CampoAtuacaoJSONParser.parseDados(conteudo);

            return camposAtuacao;
        }

        @Override
        protected void onPostExecute(final List<CampoAtuacaoModel> camposAtuacao) {
            super.onPostExecute(camposAtuacao);

        }
    }

    private static class CadCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
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
