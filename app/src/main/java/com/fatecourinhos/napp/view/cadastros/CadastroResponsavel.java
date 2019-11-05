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
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroResponsavel extends AppCompatActivity {

    //componentes da tela
    private AppCompatEditText editTextNomeResponsavel, editTextEmailResponsavel, editTextCelularResponsavel, editTextTelefoneResponsavel;
    private Button btnCadastrar;

    //variaveis globais
    private Responsavel responsavel;
    private String conteudo;
    private boolean inserir = true;
    private boolean sucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_responsavel);
        getComponentes();

        if (getIntent().getExtras() != null) {

            inserir = false;
            responsavel.setIdResponsavel(getIntent().getExtras().getInt("idResponsavel"));

            editTextTelefoneResponsavel.setText(getIntent().getExtras().getString("telefoneResponsavel"));
            editTextNomeResponsavel.setText(getIntent().getExtras().getString("nomeResponsavel"));
            editTextCelularResponsavel.setText(getIntent().getExtras().getString("celularResponsavel"));
            editTextEmailResponsavel.setText(getIntent().getExtras().getString("emailResponsavel"));
            btnCadastrar.setText(R.string.btn_salvar);

        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conferirDados()) {

                    responsavel.setTelefoneResponsavel(editTextTelefoneResponsavel.getText().toString());
                    responsavel.setNomeResponsavel(editTextNomeResponsavel.getText().toString());
                    responsavel.setEmailResponsavel(editTextEmailResponsavel.getText().toString());
                    responsavel.setCelularResponsavel(editTextCelularResponsavel.getText().toString());

                    if (inserir)
                        inserirResponsavel(responsavel);
                    else
                        alterarResponsavel(responsavel);

                } else
                    Toast.makeText(CadastroResponsavel.this,"Insira todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //pega os componentes da tela
    private void getComponentes(){

        responsavel = new Responsavel();

        editTextCelularResponsavel = findViewById(R.id.edit_text_celular_responsavel);
        editTextEmailResponsavel = findViewById(R.id.edit_text_email_responsavel);
        editTextNomeResponsavel = findViewById(R.id.edit_text_nome_responsavel);
        editTextTelefoneResponsavel = findViewById(R.id.edit_text_telefone_responsavel);
        btnCadastrar = findViewById(R.id.btn_salvar_responsavel);

        SimpleMaskFormatter maskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        SimpleMaskFormatter maskTelefone = new SimpleMaskFormatter("(NN) NNNN-NNNN");

        MaskTextWatcher mtwCelular = new MaskTextWatcher(editTextCelularResponsavel, maskCelular);
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(editTextTelefoneResponsavel, maskTelefone);

        editTextCelularResponsavel.addTextChangedListener(mtwCelular);
        editTextTelefoneResponsavel.addTextChangedListener(mtwTelefone);

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
        CadastrarResponsavel task = new CadastrarResponsavel();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        task.execute(requestHttp);

    }

    //realiza a alteração
    private void alterarResponsavel(Responsavel responsavel) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/alterarResponsavel.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarResponsavel task = new AlterarResponsavel();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idResponsavel", String.valueOf(responsavel.getIdResponsavel()));
        requestHttp.setParametro("nomeResponsavel", responsavel.getNomeResponsavel());
        requestHttp.setParametro("telefoneResponsavel", responsavel.getTelefoneResponsavel());
        requestHttp.setParametro("celularResponsavel", responsavel.getCelularResponsavel());
        requestHttp.setParametro("emailResponsavel", responsavel.getEmailResponsavel());

        task.execute(requestHttp);

    }

    private class CadastrarResponsavel extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();
        }

    }

}
