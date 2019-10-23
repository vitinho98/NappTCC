package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private boolean sucesso;
    private String conteudo;
    private EditText editTextEmail;
    private Button btnRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        editTextEmail = findViewById(R.id.editTextEmail);
        btnRecuperarSenha = findViewById(R.id.btn_recuperar_senha);

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextEmail.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Insira o e-mail!", Toast.LENGTH_SHORT).show();
                else
                    recuperarSenha(editTextEmail.getText().toString());
            }
        });

    }

    private void recuperarSenha(String email) {

        String uri = "http://vitorsilva.xyz/napp/usuario/recuperarSenha.php";
        RequestHttp requestHttp = new RequestHttp();
        RecuperarSenha task = new RecuperarSenha();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("email", email);

        task.execute(requestHttp);

    }

    private class RecuperarSenha extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = "Derrota";
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (conteudo.contains("Sucesso")) {
                Toast.makeText(getApplicationContext(), "Um e-mail foi enviado. Por favor, verifique!", Toast.LENGTH_SHORT).show();
                finish();
            } else if (conteudo.contains("E-mail não encontrado"))
                Toast.makeText(getApplicationContext(), "E-mail não encontrado! Por favor, verifique!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente!", Toast.LENGTH_SHORT).show();
        }

    }

}
