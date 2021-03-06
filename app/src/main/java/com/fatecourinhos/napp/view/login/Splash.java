package com.fatecourinhos.napp.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.cadastros.aluno.CadastroAnamnese;
import com.fatecourinhos.napp.view.menu.MenuAluno;
import com.fatecourinhos.napp.view.menu.MenuProfissional;

public class Splash extends AppCompatActivity {

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
            int primeiroLogin = preferences.getInt("primeiroLogin", 0);

            if (tipoUsuario.contains("Aluno")) {

                startActivity(new Intent(Splash.this, MenuAluno.class));

                /*if (primeiroLogin == 1) {

                } else {
                    startActivity(new Intent(Splash.this, CadastroAnamnese.class));
                }*/

                finish();

            } else if (tipoUsuario.contains("Profissional") || tipoUsuario.contains("Administrador")) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(preferences.getInt("idUsuario", 0));

                isAtivo(usuario);

            } else {
                startActivity(new Intent(Splash.this, Login.class));
                finish();
            }

        } else {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
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
        isAtivo task = new isAtivo();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idUsuario", String.valueOf(usuario.getIdUsuario()));

        task.execute(requestHttp);

    }

    private class isAtivo extends AsyncTask<RequestHttp, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    ativo = true;
                else
                    ativo = false;

            } catch (Exception e) {
                ativo = false;
            }

            return ativo;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);

            if (ativo) {
                startActivity(new Intent(Splash.this, MenuProfissional.class));
                finish();
            } else {
                startActivity(new Intent(Splash.this, Login.class));
                finish();
            }

        }

    }

}
