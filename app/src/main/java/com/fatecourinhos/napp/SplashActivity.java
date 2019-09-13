package com.fatecourinhos.napp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.LoginActivity;
import com.fatecourinhos.napp.view.MenuAlunoActivity;
import com.fatecourinhos.napp.view.MenuProfissionalActivity;

public class SplashActivity extends AppCompatActivity {

    //variaveis para manipular bd do celular
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    //variaveis globais
    private boolean ativo;
    private String conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        editor = preferences.edit();

        if (validarShared()) {

            String tipoUsuario = preferences.getString("tipoUsuario", null);

            if (tipoUsuario.contains("Aluno")) {

                startActivity(new Intent(SplashActivity.this, MenuAlunoActivity.class));
                finish();

            } else if (tipoUsuario.contains("Profissional") || tipoUsuario.contains("Administrador")) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(preferences.getInt("idUsuario", 0));

                isAtivo(usuario);

            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }

        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

    }

    //valida se já possui uma conta logada no dispositivo
    private boolean validarShared() {

        if (preferences.contains("conected")) {

            boolean resultado = preferences.getBoolean("conected", false);
            return resultado;

        } else
            return false;

    }

    //verifica se o usuario salvo no dispotivo ainda está ativo
    private void isAtivo(Usuario usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/verificarStatus.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idUsuario", String.valueOf(usuario.getIdUsuario()));

        isAtivo task = new isAtivo();
        task.execute(requestHttp);

    }

    private class isAtivo extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if (conteudo.contains("Sucesso"))
                ativo = true;
            else
                ativo = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (ativo) {
                startActivity(new Intent(SplashActivity.this, MenuProfissionalActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }
    }

}
