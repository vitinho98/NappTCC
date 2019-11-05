package com.fatecourinhos.napp.view.cadastros.profissional;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.ArrayList;
import java.util.List;

public class CadastroMensagem2 extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private Button btnEnviarMsg;

    private SharedPreferences preferences;
    private List<Aluno> alunos;
    private String conteudo;
    private int idProfissional;
    private boolean sucesso;
    private String mensagem;

    private CheckBox cb;
    private List<Integer> ids = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensagem2);

        preferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idProfissional"))
            idProfissional = preferences.getInt("idProfissional", 0);

        if (getIntent().getExtras() != null) {
            mensagem = getIntent().getExtras().getString("msg");
        }

        progressBar = findViewById(R.id.progressBarEnviarMsg);
        linearLayout = findViewById(R.id.layout_checks);
        btnEnviarMsg = findViewById(R.id.btn_enviar_msg);

        selecionarAlunos();

    }

    private void selecionarAlunos() {

        String uri = "http://vitorsilva.xyz/napp/aluno/selecionarAlunos.php";
        SelecionarAlunos task = new SelecionarAlunos();

        task.execute(uri);

    }

    private class SelecionarAlunos extends AsyncTask<String, String, List<Aluno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected List<Aluno> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            alunos = AlunoJSONParser.parseDados(conteudo);
            return alunos;
        }

        @Override
        protected void onPostExecute(final List<Aluno> alunos) {
            super.onPostExecute(alunos);

            for (Aluno aluno : alunos) {

                CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setText(aluno.getNomeAluno());
                checkBox.setId(aluno.getIdAluno());
                linearLayout.addView(checkBox);

            }

            progressBar.setVisibility(ProgressBar.INVISIBLE);

            btnEnviarMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (Aluno aluno : alunos) {
                        cb = new CheckBox(getApplicationContext());
                        cb = findViewById(aluno.getIdAluno());
                        if (cb.isChecked())
                            ids.add(cb.getId());
                    }

                    inserirMensagens();

                }
            });
        }

    }

    private void inserirMensagens(){

        String uri = "http://vitorsilva.xyz/napp/mensagem/inserirMensagem.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirMensagem task = new InserirMensagem();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("mensagem", mensagem);
        requestHttp.setParametro("ids", String.valueOf(ids));
        requestHttp.setParametro("idProfissional", String.valueOf(idProfissional));

        task.execute(requestHttp);

    }

    private class InserirMensagem extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

}
