package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;
import java.util.List;

import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class ProfissionalController {

    public boolean sucesso;
    public List<ProfissionalModel> profissionais;

    public boolean inserirProfissional(ProfissionalModel profissional){

        String uri = "http://vitorsilva.xyz/napp/profissional/inserirProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("loginProfissional", profissional.getFkUsuario().getLogin());
        requestHttp.setParametro("senhaProfissional", profissional.getFkUsuario().getSenha());
        requestHttp.setParametro("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
        requestHttp.setParametro("campoAtuacaoProfissional", String.valueOf(profissional.getCampoAtuacao().getIdCampoAtuacao()));
        requestHttp.setParametro("statusProfissional", String.valueOf(profissional.getFkUsuario().getStatus()));

        InserirProfissional task = new InserirProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public boolean alterarProfissional(ProfissionalModel profissional){

        String uri = "http://vitorsilva.xyz/napp/profissional/alterarProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idProfissional", String.valueOf(profissional.getIdProfissional()));
        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("loginProfissional", profissional.getFkUsuario().getLogin());
        requestHttp.setParametro("senhaProfissional", profissional.getFkUsuario().getSenha());
        requestHttp.setParametro("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
        requestHttp.setParametro("campoAtuacaoProfissional", String.valueOf(profissional.getCampoAtuacao().getIdCampoAtuacao()));
        requestHttp.setParametro("statusProfissional", String.valueOf(profissional.getFkUsuario().getStatus()));

        AlterarProfissional task = new AlterarProfissional();
        task.execute(requestHttp);

        return sucesso;

    }

    public List<ProfissionalModel> selecionarProfisisonais(){

        String uri = "http://vitorsilva.xyz/napp/profissional/selecionarProfissionais.php";

        SelecionarProfissionais mytask = new SelecionarProfissionais();
        mytask.execute(uri);

        return profissionais;
    }

    private class SelecionarProfissionais extends AsyncTask<String, String, List<ProfissionalModel>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ProfissionalModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            profissionais = ProfissionalJSONParser.parseDados(conteudo);

            return profissionais;
        }

        @Override
        protected void onPostExecute(List<ProfissionalModel> profissionais) {
            super.onPostExecute(profissionais);

        }
    }

    private class InserirProfissional extends AsyncTask<RequestHttp, String, String> {
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

    private class AlterarProfissional extends AsyncTask<RequestHttp, String, String> {
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

