package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.AgendamentoJSONParser;
import com.fatecourinhos.napp.model.AgendamentoModel;
import com.fatecourinhos.napp.model.MensagemModel;
import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class AlunoController {

    public static boolean sucesso;
    public static List<AgendamentoModel> agendamento;

    public static boolean inserirAluno(UsuarioModel usuario){

        String uri = "http://vitorsilva.xyz/napp/aluno/incluirAluno.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        InserirAluno task = new InserirAluno();
        task.execute(requestHttp);

        return sucesso;
    }

    public static List<AgendamentoModel> selecionarAgendamento(int idUsuario) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/selecionarAgendamentoAluno.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");

        requestHttp.setParametro("idUsuario", String.valueOf(idUsuario));

        SelecionarAgendamento mytask = new SelecionarAgendamento();
        mytask.execute(uri);

        return agendamento;
    }

    private static class SelecionarAgendamento extends AsyncTask<String, String, List<AgendamentoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<AgendamentoModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            agendamento = AgendamentoJSONParser.parseDados(conteudo);

            return agendamento;
        }

        @Override
        protected void onPostExecute(final List<AgendamentoModel> agendamento) {
            super.onPostExecute(agendamento);

        }
    }

    private static class InserirAluno extends AsyncTask<RequestHttp, String, String>{

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
