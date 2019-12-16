package com.fatecourinhos.napp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fatecourinhos.napp.json.RelatorioAlunoJSONParser;
import com.fatecourinhos.napp.json.RelatorioFeedbackJSONParser;
import com.fatecourinhos.napp.json.RelatorioProfissionalJSONParser;
import com.fatecourinhos.napp.model.RelatorioAluno;
import com.fatecourinhos.napp.model.RelatorioFeedback;
import com.fatecourinhos.napp.model.RelatorioProfissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioAlunoListAdapter;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioFeedbackListAdapter;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioProfissionalListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Relatorio extends AppCompatActivity {

    TextView txtRelatorioTitulo, txtNomeAluno, txtNomeProfissional, txtNomeFeedback, txtQuantidade, txtOpcao1, txtOpcao2, txtData;
    ListView lista;
    private String conteudo;
    List<RelatorioProfissional> relatoriosProfissional;
    List<RelatorioAluno> relatoriosAluno;
    List<RelatorioFeedback> relatoriosFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        txtData = findViewById(R.id.txtData);
        txtRelatorioTitulo = findViewById(R.id.txtRelatorioTitulo);
        txtNomeAluno = findViewById(R.id.txtNomeAluno);
        txtNomeProfissional = findViewById(R.id.txtNomeProfissional);
        txtNomeFeedback = findViewById(R.id.txtNomeFeedback);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtOpcao1 = findViewById(R.id.txtOpcao1);
        txtOpcao2 = findViewById(R.id.txtOpcao2);

        lista = findViewById(R.id.listaRelatorio);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            switch (bundle.getInt("tipo")){
                case 1:

                    txtRelatorioTitulo.setText("Relatório por Profissional");
                    txtNomeProfissional.setText("Nome Profissional");
                    txtQuantidade.setText("Quantidade");

                    carregarRelatorios(1);

                    break;
                case 2:

                    txtNomeAluno.setText("Nome Aluno");
                    txtQuantidade.setText("Quantidade");

                    txtRelatorioTitulo.setText("Relatório por Aluno");

                    carregarRelatorios(2);

                    break;
                case 3:

                    txtNomeFeedback.setText("Nome Aluno");
                    txtData.setText("Data");
                    txtOpcao1.setText("Pergunta1");
                    txtOpcao2.setText("Pergunta2");

                    txtRelatorioTitulo.setText("Relatório por Feedback");

                    carregarRelatorios(3);

                    break;
            }

        }
    }

    private void carregarRelatorios(int tipo){

        String uri = "http://vitorsilva.xyz/napp/relatorios/listarRelatorio.php";
        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("tipo", String.valueOf(tipo));

        switch (tipo){
            case 1:

                ListarRelatorioProfissional task1 = new ListarRelatorioProfissional();

                task1.execute(requestHttp);

                break;
            case 2:

                ListarRelatorioAluno task2 = new ListarRelatorioAluno();

                task2.execute(requestHttp);

                break;
            case 3:

                ListarRelatorioFeedback task3 = new ListarRelatorioFeedback();

                task3.execute(requestHttp);

                break;
        }

    }

    private class ListarRelatorioAluno extends AsyncTask<RequestHttp, String, List<RelatorioAluno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<RelatorioAluno> doInBackground(RequestHttp... params) {
            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            relatoriosAluno = RelatorioAlunoJSONParser.parseDados(conteudo);

            return relatoriosAluno;
        }

        @Override
        protected void onPostExecute(List<RelatorioAluno> relatorioAlunos) {

            RelatorioAlunoListAdapter adapter = new RelatorioAlunoListAdapter(getApplicationContext(), R.layout.adapter_relatorio_aluno, relatorioAlunos);
            lista.setAdapter(adapter);

        }
    }

    private class ListarRelatorioProfissional extends AsyncTask<RequestHttp, String, List<RelatorioProfissional>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<RelatorioProfissional> doInBackground(RequestHttp... params) {
            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            relatoriosProfissional = RelatorioProfissionalJSONParser.parseDados(conteudo);

            return relatoriosProfissional;
        }

        @Override
        protected void onPostExecute(List<RelatorioProfissional> relatorioProfissionals) {

            RelatorioProfissionalListAdapter adapter = new RelatorioProfissionalListAdapter(getApplicationContext(), R.layout.adapter_relatorio_profissional, relatorioProfissionals);
            lista.setAdapter(adapter);

        }
    }

    private class ListarRelatorioFeedback extends AsyncTask<RequestHttp, String, List<RelatorioFeedback>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<RelatorioFeedback> doInBackground(RequestHttp... params) {
            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }
            Log.e("TESTE - ", conteudo);
            relatoriosFeedback = RelatorioFeedbackJSONParser.parseDados(conteudo);

            return relatoriosFeedback;
        }

        @Override
        protected void onPostExecute(List<RelatorioFeedback> relatorioFeedbacks) {

            RelatorioFeedbackListAdapter adapter = new RelatorioFeedbackListAdapter(getApplicationContext(), R.layout.adapter_relatorio_feedback, relatorioFeedbacks);
            lista.setAdapter(adapter);

        }
    }


}
