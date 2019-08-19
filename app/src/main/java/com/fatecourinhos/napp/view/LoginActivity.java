package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.cadastros.CadastroAluno;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //componente da tela de login
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AppCompatEditText editTextLogin, editTextSenha;
    TextInputLayout textInputLayoutLogin, textInputLayoutSenha;
    ImageView imgSobre;
    TextView txtCadastrar;
    Button btnLogin;
    ProgressBar pgBar;

    //variaveis globais
    boolean ativo;
    boolean sucess;
    String conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getComponentes();

        if (validarShared()) {

            if (preferences.contains("tipoUsuario")) {

                String tipoUsuario = preferences.getString("tipoUsuario", null);

                if (tipoUsuario.contains("aluno")) {

                    startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
                    finish();

                } else if (tipoUsuario.contains("profissional") || tipoUsuario.contains("Administrador")) {

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(preferences.getInt("idUsuario", 0));

                    isAtivo(usuario);

                }
            }
        }

        setOnClicks();

    }

    //chama os componentes da tela
    private void getComponentes(){

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        editor = preferences.edit();

        btnLogin = findViewById(R.id.btn_entrar);
        txtCadastrar = findViewById(R.id.txt_cadastrar);
        imgSobre = findViewById(R.id.img_sobre);

        textInputLayoutLogin = findViewById(R.id.txt_layout_login);
        textInputLayoutSenha = findViewById(R.id.txt_layout_senha);

        editTextLogin = findViewById(R.id.edit_text_login);
        editTextSenha = findViewById(R.id.edit_text_senha);

        pgBar = findViewById(R.id.pgBarLogin);
        pgBar.setVisibility(View.INVISIBLE);
    }

    //seta o evento on click dos componentes
    private void setOnClicks(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarForm()) {

                    Usuario usuario = new Usuario();
                    usuario.setLogin(editTextLogin.getText().toString());
                    usuario.setSenha(editTextSenha.getText().toString());

                    autenticarUsuario(usuario);

                }
            }
        });

        imgSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SobreActivity.class));
            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroAluno.class));
            }
        });

    }

    //valida se os campos login e senha foram digitados
    private boolean validarForm(){

        boolean retornoLogin, retornoSenha;

        if (editTextSenha.getText().toString().isEmpty()) {

            textInputLayoutSenha.setErrorEnabled(true);
            textInputLayoutSenha.setError("Insira a senha");
            retornoSenha = false;

        } else {

            textInputLayoutSenha.setErrorEnabled(false);
            retornoSenha = true;

        }

        if (editTextLogin.getText().toString().isEmpty()) {

            textInputLayoutLogin.setErrorEnabled(true);
            textInputLayoutLogin.setError("Insira o login");
            retornoLogin = false;

        } else {

            textInputLayoutLogin.setErrorEnabled(false);
            retornoLogin = true;

        }

        if (retornoLogin == true && retornoSenha == true)
            return true;
        else
            return false;

    }

    //valida se já possui uma conta logada no dispositivo
    private boolean validarShared(){

        if (preferences.contains("conected")) {

            boolean resultado = preferences.getBoolean("conected", false);
            return resultado;

        } else
            return false;

    }

    //adiciona um aluno no sharedpreferences
    private void adicionarPreferences(Aluno aluno){

        editor.putInt("idUsuario", aluno.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", aluno.getFkUsuario().getTipoUsuario());
        editor.putInt("status", aluno.getFkUsuario().getStatus());
        editor.putInt("idAluno", aluno.getIdAluno());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //adiciona um profissional no sharedpreferences
    private void adicionarPreferences(Profissional profissional){

        editor.putInt("idUsuario", profissional.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", profissional.getFkUsuario().getTipoUsuario());
        editor.putInt("status", profissional.getFkUsuario().getStatus());
        editor.putInt("idProfissional", profissional.getIdProfissional());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //pega o retorno do web service e retorna o tipo de usuário
    private String verificarTipoUsuario(String conteudo){

        int fim = conteudo.indexOf("@");
        String tipo = conteudo.substring(0, fim);
        return tipo;

    }

    //pega o retorno do web service e retorna o json
    private String criarJson(String conteudo){

        int inicio = conteudo.indexOf("@");
        String json = conteudo.substring(inicio+1);
        return json;

    }

    //verifica o retorno do webservice e chama o menu adequado ou informa que não foi possível encontrar o usuário
    private void login(String conteudo){

        String tipoUsuario = verificarTipoUsuario(conteudo);
        conteudo = criarJson(conteudo);

        if (tipoUsuario.equals("aluno")) {

            List<Aluno> alunos = AlunoJSONParser.parseDados(conteudo);
            Aluno aluno = alunos.get(0);

            adicionarPreferences(aluno);

            startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
            finish();

        } else {

            List<Profissional> profissionais = ProfissionalJSONParser.parseDados(conteudo);
            Profissional profissional = profissionais.get(0);

            if (profissional.getFkUsuario().getStatus() == 0) {

                adicionarPreferences(profissional);

                startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                finish();

            } else
                Toast.makeText(LoginActivity.this, "Usuário desativado!", Toast.LENGTH_LONG).show();
        }
    }

    //realiza as configurações para enviar dados ao banco de dados
    public void autenticarUsuario(Usuario usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        autenticarUsuario task = new autenticarUsuario();
        task.execute(requestHttp);

    }

    //verifica se o usuario salvo no dispotivo ainda está ativo
    public void isAtivo(Usuario usuario){

        String uri = "http://vitorsilva.xyz/napp/usuario/verificarStatus.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idUsuario", String.valueOf(usuario.getIdUsuario()));

        isAtivo task = new isAtivo();
        task.execute(requestHttp);

    }

    //tarefa assincrona que recebe os dados do banco de dados
    private class autenticarUsuario extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if (conteudo.contains("Vazio"))
                sucess = false;
            else
                sucess = true;

            return conteudo;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pgBar.setVisibility(View.INVISIBLE);

            if(sucess == true)
                login(conteudo);
            else
                Toast.makeText(LoginActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    //tarefa assincrona que recebe os dados do banco de dados
    private class isAtivo extends AsyncTask<RequestHttp, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                ativo = true;
            else
                ativo = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (ativo) {
                startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                finish();
            }
        }
    }

}
