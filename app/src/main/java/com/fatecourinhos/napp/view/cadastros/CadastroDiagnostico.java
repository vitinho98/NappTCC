package com.fatecourinhos.napp.view.cadastros;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CadastroDiagnostico extends AppCompatActivity {

    //componente da tela
    private AppCompatEditText editTextNomeDiagnostico;
    private Button botao;

    //variaveis globais
    private Diagnostico diagnostico;
    private String conteudo;
    private boolean sucesso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_diagnostico);

        editTextNomeDiagnostico = findViewById(R.id.edit_text_nome_diagnostico);
        botao = findViewById(R.id.btn_cadastrar_diagnostico);
        diagnostico = new Diagnostico();

        if (getIntent().getExtras() != null) {

            editTextNomeDiagnostico.setText(getIntent().getExtras().getString("nomeDiagnostico"));
            diagnostico.setIdDiagnostico(getIntent().getExtras().getInt("idDiagnostico"));

            botao.setText(R.string.btn_salvar);
            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeDiagnostico.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();
                    else {
                        diagnostico.setNomeDiagnostico(editTextNomeDiagnostico.getText().toString());
                        alterarDiagnostico(diagnostico);
                    }

                }
            });

        } else {

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeDiagnostico.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();
                    else {
                        diagnostico.setNomeDiagnostico(editTextNomeDiagnostico.getText().toString());
                        inserirDiagnostico(diagnostico);
                    }

                }
            });

        }

    }

    private void inserirDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/inserirDiagnostico.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirDiagnostico task = new InserirDiagnostico();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnostico());

        task.execute(requestHttp);

    }

    private void alterarDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/alterarDiagnostico.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarDiagnostico task = new AlterarDiagnostico();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idDiagnostico", String.valueOf(diagnostico.getIdDiagnostico()));
        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnostico());

        task.execute(requestHttp);

    }

    private class InserirDiagnostico extends AsyncTask<RequestHttp, String, String> {

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

    private class AlterarDiagnostico extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
        }

    }

}
