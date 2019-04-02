package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.HttpManager;
import com.fatecourinhos.napp.controller.RequestHttp;
import com.fatecourinhos.napp.model.ProfissionalModel;

public class ProfissionalActivity extends AppCompatActivity {

    final ProfissionalModel profissional = new ProfissionalModel();
    private AppCompatEditText editTextNome, editTextCel, editTextEmail;
    private Button btn_cadastrar_profissional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_cadastro);

        editTextNome = findViewById(R.id.edit_text_nome_profissional);
        editTextCel = findViewById(R.id.edit_text_telefone_profissional);
        editTextEmail = findViewById(R.id.edit_text_email_profissional);

        btn_cadastrar_profissional = findViewById(R.id.btn_cadastrar_profissional);



        if((getIntent().getExtras()!=null)) {

            String nome = (String) getIntent().getExtras().get("nomeProfissional");

            editTextNome = findViewById(R.id.edit_text_nome_profissional);

            editTextNome.setText(nome);
        }

        btn_cadastrar_profissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarDados("http://192.168.0.43/testetcc/APIIncluirDados.php");

            }
        });


    }

    private void enviarDados(String uri){
        profissional.setNomeProfissional(editTextNome.getText().toString());
        profissional.setCelularProfissional(editTextCel.getText().toString());
        profissional.setEmailProfissional(editTextEmail.getText().toString());

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());

        MyTask task = new MyTask();
        task.execute(requestHttp);
    }


    private class MyTask extends AsyncTask<RequestHttp, String, String>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            String conteudo = (String) HttpManager.getDados(params[0]);
            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(ProfissionalActivity.this, MenuProfissionalActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
