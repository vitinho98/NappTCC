package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.ProfissionalExternoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class ProfissionalExternoController {

    public static boolean sucesso;
    public static List<ProfissionalExternoModel> profissionaisExternos;

    public static boolean inserir(ProfissionalExternoModel profissionalExterno) {

        String uri = "";

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
        requestHttp.setParametro("idCampoAtuacao", String.valueOf(profissionalExterno.getCampoAtuacao().getIdCampoAtuacao()));


        CadProfissionalExterno task = new CadProfissionalExterno();
        task.execute(requestHttp);

        return sucesso;

    }

    public static List<ProfissionalExternoModel> buscarProfissionaisExternos() {

        String uri = "";

        SelectProfissionalExterno mytask = new SelectProfissionalExterno();
        mytask.execute(uri);

        return profissionaisExternos;
    }

    private static class SelectProfissionalExterno extends AsyncTask<String, String, List<ProfissionalExternoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ProfissionalExternoModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            profissionaisExternos = ProfissionalExternoJSONParser.parseDados(conteudo);

            return profissionaisExternos;
        }

        @Override
        protected void onPostExecute(final List<ProfissionalExternoModel> profissionaisExternos) {
            super.onPostExecute(profissionaisExternos);

        }
    }

    private static class CadProfissionalExterno extends AsyncTask<RequestHttp, String, String> {
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