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
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CadastroLocalAtendimento extends AppCompatActivity {

    //componentes da tela
    private AppCompatEditText editTextNomeBloco, editTextNomeLocal;
    private Button btnCadastrar;

    //variaveis gloabais
    private LocalAtendimento localAtendimento;
    private String conteudo;
    private boolean sucesso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_local);
        getComponentes();

        if (getIntent().getExtras() != null) {

            localAtendimento.setIdLocalAtendimento(getIntent().getExtras().getInt("idLocalAtendimento"));
            editTextNomeBloco.setText(getIntent().getExtras().getString("nomeBloco"));
            editTextNomeLocal.setText(getIntent().getExtras().getString("nomeLocal"));

            btnCadastrar.setText(R.string.btn_salvar);
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeBloco.getText().toString().isEmpty() || editTextNomeLocal.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();
                    else {
                        localAtendimento.setNomeBloco(editTextNomeBloco.getText().toString());
                        localAtendimento.setNomeLocal(editTextNomeLocal.getText().toString());
                        alterarLocalAtendimento(localAtendimento);
                    }

                }
            });

        } else {

            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeBloco.getText().toString().isEmpty() || editTextNomeLocal.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();
                    else {
                        localAtendimento.setNomeLocal(editTextNomeLocal.getText().toString());
                        localAtendimento.setNomeBloco(editTextNomeBloco.getText().toString());
                        inserirLocalAtendimento(localAtendimento);
                    }
                }
            });
        }
    }

    private void getComponentes() {

        localAtendimento = new LocalAtendimento();

        editTextNomeBloco = findViewById(R.id.edit_text_nome_bloco);
        editTextNomeLocal = findViewById(R.id.edit_text_nome_sala);
        btnCadastrar = findViewById(R.id.btn_cadastrar_local);

    }

    private void inserirLocalAtendimento(LocalAtendimento localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/inserirLocalAtendimento.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirLocalAtendimento task = new InserirLocalAtendimento();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        task.execute(requestHttp);

    }

    private void alterarLocalAtendimento(LocalAtendimento localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/alterarLocalAtendimento.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarLocalAtendimento task = new AlterarLocalAtendimento();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idLocalAtendimento", String.valueOf(localAtendimento.getIdLocalAtendimento()));
        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        task.execute(requestHttp);

    }

    private class InserirLocalAtendimento extends AsyncTask<RequestHttp, String, String> {

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

    private class AlterarLocalAtendimento extends AsyncTask<RequestHttp, String, String> {

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
