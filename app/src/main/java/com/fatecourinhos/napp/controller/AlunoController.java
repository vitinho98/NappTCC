package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.AgendamentoJSONParser;
import com.fatecourinhos.napp.model.MensagemModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class AlunoController {

    public static boolean sucesso;
    public static List<MensagemModel> agendamento;

    public static List<MensagemModel> selecionarAgendamento(int idUsuario) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/selecionarAgendamentoAluno.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idUsuario", String.valueOf(idUsuario));

        SelecionarAgendamento mytask = new SelecionarAgendamento();
        mytask.execute(uri);

        return agendamento;
    }

    private static class SelecionarAgendamento extends AsyncTask<String, String, List<MensagemModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<MensagemModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            agendamento = AgendamentoJSONParser.parseDados(conteudo);

            return agendamento;
        }

        @Override
        protected void onPostExecute(final List<MensagemModel> agendamento) {
            super.onPostExecute(agendamento);

        }
    }

}
