package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class CampoAtuacaoController {


    public static boolean sucesso;
    public static List<CampoAtuacao> camposAtuacao;

    public static boolean inserirCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/inserirCampoAtuacao.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        InserirCampoAtuacao task = new InserirCampoAtuacao();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarCampoAtuacao(CampoAtuacao campoAtuacao) {

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
