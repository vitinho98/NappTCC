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
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CadastroCampoAtuacao extends AppCompatActivity {

    //componente da tela
    private AppCompatEditText editTextNomeCampo;
    private Button botao;

    //variaveis globais
    private CampoAtuacao campoAtuacao;
    private String conteudo;
    private boolean sucesso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_area_atuacao);

        editTextNomeCampo = findViewById(R.id.edit_text_nome_area);
        botao = findViewById(R.id.btn_cadastrar_campo_atuacao);
        campoAtuacao = new CampoAtuacao();

        if (getIntent().getExtras() != null) {

            editTextNomeCampo.setText(getIntent().getExtras().getString("nomeCampoAtuacao"));
            campoAtuacao.setIdCampoAtuacao(getIntent().getExtras().getInt("idCampoAtuacao"));

            botao.setText(R.string.btn_salvar);
            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeCampo.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(),"Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();
                    else {
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeCampo.getText().toString());
                        alterarCampoAtuacao(campoAtuacao);
                    }

                }
            });

        } else {

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTextNomeCampo.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();
                    else {
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeCampo.getText().toString());
                        inserirCampoAtuacao(campoAtuacao);
                    }

                }
            });

        }

    }

    private void inserirCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/inserirCampoAtuacao.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirCampoAtuacao task = new InserirCampoAtuacao();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        task.execute(requestHttp);

    }

    private void alterarCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/alterarCampoAtuacao.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarCampoAtuacao task = new AlterarCampoAtuacao();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idCampoAtuacao", String.valueOf(campoAtuacao.getIdCampoAtuacao()));
        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        task.execute(requestHttp);

    }

    private class InserirCampoAtuacao extends AsyncTask<RequestHttp, String, String> {

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

    private class AlterarCampoAtuacao extends AsyncTask<RequestHttp, String, String> {

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
