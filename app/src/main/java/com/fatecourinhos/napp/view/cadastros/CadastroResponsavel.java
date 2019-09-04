package com.fatecourinhos.napp.view.cadastros;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroResponsavel extends AppCompatActivity {

    //componentes da tela
    private AppCompatEditText editTextNomeResponsavel, editTextEmailResponsavel, editTextCelularResponsavel, editTextTelefoneResponsavel;
    private Button btn_cadastrar_responsavel;

    //variaveis globais
    Responsavel responsavel;
    String conteudo;
    boolean sucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_responsavel);

        getComponentes();

        if (getIntent().getExtras() != null) {

            responsavel = new Responsavel();
            responsavel.setIdResponsavel(getIntent().getExtras().getInt("idResponsavel"));

            editTextTelefoneResponsavel.setText(getIntent().getExtras().getString("telefoneResponsavel"));
            editTextNomeResponsavel.setText(getIntent().getExtras().getString("nomeResponsavel"));
            editTextCelularResponsavel.setText(getIntent().getExtras().getString("celularResponsavel"));
            editTextEmailResponsavel.setText(getIntent().getExtras().getString("emailResponsavel"));
            btn_cadastrar_responsavel.setText(R.string.btn_salvar);

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (conferirDados()) {

                        responsavel.setTelefoneResponsavel(editTextTelefoneResponsavel.getText().toString());
                        responsavel.setNomeResponsavel(editTextNomeResponsavel.getText().toString());
                        responsavel.setEmailResponsavel(editTextEmailResponsavel.getText().toString());
                        responsavel.setCelularResponsavel(editTextCelularResponsavel.getText().toString());

                        alterarResponsavel(responsavel);

                    } else
                        Toast.makeText(CadastroResponsavel.this,"Insira todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();

                }
            });

        } else {

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (conferirDados()) {

                        responsavel = new Responsavel();

                        responsavel.setTelefoneResponsavel(editTextTelefoneResponsavel.getText().toString());
                        responsavel.setNomeResponsavel(editTextNomeResponsavel.getText().toString());
                        responsavel.setEmailResponsavel(editTextEmailResponsavel.getText().toString());
                        responsavel.setCelularResponsavel(editTextCelularResponsavel.getText().toString());

                        inserirResponsavel(responsavel);

                    } else
                        Toast.makeText(CadastroResponsavel.this,"Insira todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();

                }
            });
        }

}

    //pega os componentes da tela
    private void getComponentes(){

        editTextCelularResponsavel = findViewById(R.id.edit_text_celular_responsavel);
        editTextEmailResponsavel = findViewById(R.id.edit_text_email_responsavel);
        editTextNomeResponsavel = findViewById(R.id.edit_text_nome_responsavel);
        editTextTelefoneResponsavel = findViewById(R.id.edit_text_telefone_responsavel);

        btn_cadastrar_responsavel = findViewById(R.id.btn_salvar_responsavel);

    }

    //confere se os campos necessarios foram inseridos
    private boolean conferirDados(){

        if (editTextNomeResponsavel.getText().toString().isEmpty())
            return false;
        else
            return true;

    }

    //realiza o cadastro
    private void inserirResponsavel(Responsavel responsavel) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/inserirResponsavel.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        CadastrarResponsavel task = new CadastrarResponsavel();
        task.execute(requestHttp);

    }

    //realiza a alteração
    private void alterarResponsavel(Responsavel responsavel) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/alterarResponsavel.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idResponsavel", String.valueOf(responsavel.getIdResponsavel()));
        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        AlterarResponsavel task = new AlterarResponsavel();
        task.execute(requestHttp);

    }

    private class CadastrarResponsavel extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso)
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }
    }

    private class AlterarResponsavel extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso)
                Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();
        }
    }

}
