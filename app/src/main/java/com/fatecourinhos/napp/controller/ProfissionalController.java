package com.fatecourinhos.napp.controller;

import android.content.Intent;
import android.os.AsyncTask;
import java.util.List;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.ProfissionalCadastro;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;

public class ProfissionalController {

    public static boolean sucesso;
    public static List<ProfissionalModel> profissionais;

    public static boolean conferirDados(ProfissionalModel profissional){

        sucesso = true;

        if(profissional.getNomeProfissional() == null)
            sucesso = false;
        else if(profissional.getCelularProfissional() == null)
            sucesso = false;
        else if(profissional.getEmailProfissional() == null)
            sucesso = false;
        else if(profissional.getFkUsuario().getLogin() == null)
            sucesso = false;
        else if(profissional.getFkUsuario().getSenha() == null)
            sucesso = false;
        else if(profissional.getFkUsuario().getTipoUsuario() == null)
            sucesso = false;

        return sucesso;
    }

    public static boolean inserir(ProfissionalModel profissional){

        String uri = "http://vitorsilva.xyz/napp/APIIncluirDados.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("loginProfissional", profissional.getFkUsuario().getLogin());
        requestHttp.setParametro("senhaProfissional", profissional.getFkUsuario().getSenha());
        requestHttp.setParametro("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
        requestHttp.setParametro("statusProfissional", String.valueOf(profissional.getFkUsuario().getStatus()));

        CadProf task = new CadProf();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<ProfissionalModel> buscarProfisisonais(){

        String uri = "http:vitorsilva.xyz/napp/APIConsultarDados.php";

        SelectProf mytask = new SelectProf();
        mytask.execute(uri);

        return profissionais;
    }

    private static class SelectProf extends AsyncTask<String, String, List<ProfissionalModel>>{

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
        protected void onPostExecute(final List<ProfissionalModel> profissional) {
            super.onPostExecute(profissionais);

        }
    }

    private static class CadProf extends AsyncTask<RequestHttp, String, String> {
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

