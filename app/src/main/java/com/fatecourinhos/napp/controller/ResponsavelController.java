package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.ResponsavelJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class ResponsavelController {

    public static boolean sucesso;
    public static List<ResponsavelModel> responsaveis;

    public boolean inserirResponsavel(ResponsavelModel responsavel) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/inserirResponsavel.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        CadastrarResponsavel task = new CadastrarResponsavel();
        task.execute(requestHttp);

        return sucesso;

    }

    public boolean alterarResponsavel(ResponsavelModel responsavel) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/alterarResponsavel.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idResponsavel", String.valueOf(responsavel.getIdResponsavel()));
        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        AlterarResponsavel task = new AlterarResponsavel();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<ResponsavelModel> selecionarResponsaveis() {

        String uri = "http://vitorsilva.xyz/napp/responsavel/selecionarResponsaveis.php";

        SelecionarResponsaveis mytask = new SelecionarResponsaveis();
        mytask.execute(uri);

        return responsaveis;
    }

    private static class SelecionarResponsaveis extends AsyncTask<String, String, List<ResponsavelModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ResponsavelModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            responsaveis = ResponsavelJSONParser.parseDados(conteudo);

            return responsaveis;
        }

        @Override
        protected void onPostExecute(final List<ResponsavelModel> responsaveis) {
            super.onPostExecute(responsaveis);

        }
    }

    private static class CadastrarResponsavel extends AsyncTask<RequestHttp, String, String> {
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

    private static class AlterarResponsavel extends AsyncTask<RequestHttp, String, String> {
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
