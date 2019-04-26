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

    public static boolean inserirCampoAtuacao(CampoAtuacaoModel campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/inserirCampoAtuacao.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        InserirCampoAtuacao task = new InserirCampoAtuacao();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarCampoAtuacao(CampoAtuacaoModel campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/alterarCampoAtuacao.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idCampoAtuacao", String.valueOf(campoAtuacao.getIdCampoAtuacao()));
        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        AlterarCampoAtuacao task = new AlterarCampoAtuacao();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<CampoAtuacaoModel> selecionarCamposAtuacao() {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/selecionarCamposAtuacao.php";

        SelecionarCamposAtuacao mytask = new SelecionarCamposAtuacao();
        mytask.execute(uri);

        return camposAtuacao;
    }

    private static class SelecionarCamposAtuacao extends AsyncTask<String, String, List<CampoAtuacaoModel>> {

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

    private static class InserirCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Sucessoo"))
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

    private static class AlterarCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Sucessoo"))
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
