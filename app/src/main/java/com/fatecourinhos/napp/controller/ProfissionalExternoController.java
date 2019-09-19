package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.fatecourinhos.napp.json.ProfissionalExternoJSONParser;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class ProfissionalExternoController {

    public static boolean sucesso;
    public static List<ProfissionalExterno> profissionaisExternos;

    public static boolean inserirProfissionalExterno(ProfissionalExterno profissionalExterno) {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/inserirProfissionalExterno.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
        requestHttp.setParametro("bairroProfissionalExterno", profissionalExterno.getBairro());
        requestHttp.setParametro("celularProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("telefoneProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("cidadeProfissionalExterno", profissionalExterno.getCidadeProfissionalExterno());
        requestHttp.setParametro("idResponsavel", String.valueOf(profissionalExterno.getFkResponsavel().getIdResponsavel()));
        requestHttp.setParametro("enderecoProfissionalExterno", profissionalExterno.getEndereco());
        requestHttp.setParametro("emailProfissionalExterno", profissionalExterno.getEmailProfissionalExterno());
        requestHttp.setParametro("numeroProfissionalExterno", profissionalExterno.getNumero());
        requestHttp.setParametro("idCampoAtuacao", String.valueOf(profissionalExterno.getFkCampoAtuacao().getIdCampoAtuacao()));

        CadastrarProfissionalExterno task = new CadastrarProfissionalExterno();
        task.execute(requestHttp);

        return sucesso;

    }

    public static boolean alterarProfissionalExterno(ProfissionalExterno profissionalExterno) {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/alterarProfExterno.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idProfissionalExterno", String.valueOf(profissionalExterno.getIdProfissionalExterno()));
        requestHttp.setParametro("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
        requestHttp.setParametro("bairroProfissionalExterno", profissionalExterno.getBairro());
        requestHttp.setParametro("celularProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("telefoneProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("cidadeProfissionalExterno", profissionalExterno.getCidadeProfissionalExterno());
        requestHttp.setParametro("enderecoProfissionalExterno", profissionalExterno.getEndereco());
        requestHttp.setParametro("emailProfissionalExterno", profissionalExterno.getEmailProfissionalExterno());
        requestHttp.setParametro("numeroProfissionalExterno", profissionalExterno.getNumero());
        requestHttp.setParametro("fkCampoAtuacao", String.valueOf(profissionalExterno.getFkCampoAtuacao().getIdCampoAtuacao()));
        requestHttp.setParametro("fkResponsavel", String.valueOf(profissionalExterno.getFkResponsavel().getIdResponsavel()));

        AlterarProfissionalExterno task = new AlterarProfissionalExterno();
        task.execute(requestHttp);

        return sucesso;

    }



    private static class CadastrarProfissionalExterno extends AsyncTask<RequestHttp, String, String> {
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

    private static class AlterarProfissionalExterno extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);
            Log.e("TESTE", "ANTES DA CONSULTA");
            if(conteudo.equals("Sucesso")){
                sucesso = true;
            }else{
                sucesso = false;
            }
            Log.e("TESTE", "DEPOIS DA CONSULTA - " + conteudo);

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}