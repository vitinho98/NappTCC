package com.fatecourinhos.napp.view.login;

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
import com.fatecourinhos.napp.model.Anamnese;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.cadastros.aluno.CadastroAnamnese;
import com.fatecourinhos.napp.view.menu.MenuAluno;
import com.fatecourinhos.napp.view.menu.MenuProfissional;
import com.fatecourinhos.napp.view.senha.RecuperarSenha;
import com.fatecourinhos.napp.view.cadastros.aluno.CadastroAluno;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Login extends AppCompatActivity {

    //componente da tela de login
    private AppCompatEditText editTextLogin, editTextSenha;
    private TextInputLayout textInputLayoutLogin, textInputLayoutSenha;
    private ImageView imgSobre;
    private TextView txtCadastrar, txtRecuperarSenha;
    private Button btnLogin;
    private ProgressBar pgBar;

    //variaveis globais
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean sucesso;
    private String conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getComponentes();
        setOnClicks();
    }

    //chama os componentes da tela
    private void getComponentes() {

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        editor = preferences.edit();

        btnLogin = findViewById(R.id.btn_entrar);
        txtCadastrar = findViewById(R.id.txt_cadastrar);
        txtRecuperarSenha = findViewById(R.id.txt_esqueci_senha);
        imgSobre = findViewById(R.id.img_sobre);

        textInputLayoutLogin = findViewById(R.id.txt_layout_login);
        textInputLayoutSenha = findViewById(R.id.txt_layout_senha);

        editTextLogin = findViewById(R.id.edit_text_login);
        editTextSenha = findViewById(R.id.edit_text_senha);

        pgBar = findViewById(R.id.pgBarLogin);
        pgBar.setVisibility(View.INVISIBLE);

    }

    //seta o evento on click dos componentes
    private void setOnClicks() {

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
                startActivity(new Intent(Login.this, Sobre.class));
            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, CadastroAluno.class));
            }
        });

        txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RecuperarSenha.class));
            }
        });

    }

    //valida se os campos login e senha foram digitados
    private boolean validarForm() {

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

    //adiciona um aluno no sharedpreferences
    private void adicionarPreferences(Aluno aluno) {

        editor.putInt("idUsuario", aluno.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", aluno.getFkUsuario().getTipoUsuario());
        editor.putInt("status", aluno.getFkUsuario().getStatus());
        editor.putInt("idAluno", aluno.getIdAluno());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //adiciona um profissional no sharedpreferences
    private void adicionarPreferences(Profissional profissional) {

        editor.putInt("idUsuario", profissional.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", profissional.getFkUsuario().getTipoUsuario());
        editor.putInt("status", profissional.getFkUsuario().getStatus());
        editor.putInt("idProfissional", profissional.getIdProfissional());
        editor.putString("nome", profissional.getNomeProfissional());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //pega o retorno do web service e retorna o tipo de usuário
    private String verificarTipoUsuario(String conteudo) {

        int fim = conteudo.indexOf("@");
        String tipo = conteudo.substring(0, fim);
        return tipo;

    }

    //pega o retorno do web service e retorna o json
    private String criarJson(String conteudo) {

        int inicio = conteudo.indexOf("@");
        String json = conteudo.substring(inicio+1);
        return json;

    }

    //verifica o retorno do webservice e chama o menu adequado ou informa que não foi possível encontrar o usuário
    private void login(String conteudo) {

        String tipoUsuario = verificarTipoUsuario(conteudo);
        conteudo = criarJson(conteudo);

        if (tipoUsuario.equals("aluno")) {

            System.out.println(tipoUsuario);
            List<Aluno> alunos = AlunoJSONParser.parseDados(conteudo);
            Aluno aluno = alunos.get(0);

            adicionarPreferences(aluno);

            if(aluno.getFkUsuario().getPrimeiroLogin() == 1){
                startActivity(new Intent(Login.this, MenuAluno.class));
                finish();
            }else{
                startActivity(new Intent(Login.this, CadastroAnamnese.class));
                finish();
            }

        } else {

            List<Profissional> profissionais = ProfissionalJSONParser.parseDados(conteudo);
            Profissional profissional = profissionais.get(0);

            if (profissional.getFkUsuario().getStatus() == 0) {

                adicionarPreferences(profissional);

                startActivity(new Intent(Login.this, MenuProfissional.class));
                finish();

            } else
                Toast.makeText(Login.this, "Usuário desativado!", Toast.LENGTH_LONG).show();
        }
    }

    //realiza as configurações para enviar dados ao banco de dados
    private void autenticarUsuario(Usuario usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";
        RequestHttp requestHttp = new RequestHttp();
        autenticarUsuario task = new autenticarUsuario();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        task.execute(requestHttp);

    }

    //tarefa assincrona que recebe os dados do banco de dados
    private class autenticarUsuario extends AsyncTask<RequestHttp, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            try {

                if (conteudo.contains("Vazio"))
                    sucesso = false;
                else
                    sucesso = true;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pgBar.setVisibility(View.INVISIBLE);

            if (sucesso)
                login(conteudo);
            else
                Toast.makeText(Login.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
        }

    }

}
