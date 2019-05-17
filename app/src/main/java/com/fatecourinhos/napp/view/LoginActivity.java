package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.UsuarioController;
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.AlunoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.cadastros.CadastroAluno;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AppCompatEditText editTextLogin, editTextSenha;
    TextInputLayout textInputLayoutLogin, textInputLayoutSenha;
    ImageView imgSobre;
    TextView txtCadastrar;
    Button btnLogin;
    boolean sucess;
    String conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getComponentes();

        if(validarShared()){

            if(preferences.contains("tipoUsuario")){

                String tipoUsuario = preferences.getString("tipoUsuario", null);

                if(tipoUsuario.equals("aluno")) {

                    startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
                    finish();

                } else if(tipoUsuario.equals("profissional") || tipoUsuario.equals("administrador")) {

                    UsuarioModel usuarioModel = new UsuarioModel();
                    usuarioModel.setIdUsuario(preferences.getInt("idUsuario", 0));

                    if (UsuarioController.isAtivo(usuarioModel)) {

                        startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                        finish();

                    }
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
    }

    //valida se login e senha foram digitados
    private boolean validarForm(){

        boolean retornoLogin, retornoSenha;

        if(editTextSenha.getText().toString().isEmpty()){
            textInputLayoutSenha.setErrorEnabled(true);
            textInputLayoutSenha.setError("Insira a senha");
            retornoSenha = false;
        }else{
            textInputLayoutSenha.setErrorEnabled(false);
            retornoSenha = true;
        }

        if(editTextLogin.getText().toString().isEmpty()){
            textInputLayoutLogin.setErrorEnabled(true);
            textInputLayoutLogin.setError("Insira o login");
            retornoLogin = false;
        }else{
            textInputLayoutLogin.setErrorEnabled(false);
            retornoLogin = true;
        }

        if(retornoLogin == true && retornoSenha == true){
            return true;
        }else{
            return false;
        }
    }

    //valida se já possui uma conta logada no dispositivo
    private boolean validarShared(){

        if(preferences.contains("conected")) {

            boolean resultado = preferences.getBoolean("conected", false);

            if(resultado){
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }

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

    //seta o evento on click dos componentes
    private void setOnClicks(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarForm()) {

                    UsuarioModel usuarioModel = new UsuarioModel();
                    usuarioModel.setLogin(editTextLogin.getText().toString());
                    usuarioModel.setSenha(editTextSenha.getText().toString());

                    autenticarUsuario(usuarioModel);

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

    //adiciona no sharedpreferences
    private void adicionarPreferences(AlunoModel aluno){

        editor.putInt("idUsuario", aluno.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", aluno.getFkUsuario().getTipoUsuario());
        editor.putInt("status", aluno.getFkUsuario().getStatus());
        editor.putInt("idAluno", aluno.getIdAluno());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //adiciona no sharedpreferences
    private void adicionarPreferences(ProfissionalModel profissional){

        editor.putInt("idUsuario", profissional.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", profissional.getFkUsuario().getTipoUsuario());
        editor.putInt("status", profissional.getFkUsuario().getStatus());
        editor.putInt("idProfissional", profissional.getIdProfissional());
        editor.putBoolean("conected", true);

        editor.commit();

    }

    //verifica o retorno do webservice e chama o menu adequado ou informa que não foi possível encontrar o usuário
    private void login(String conteudo){

        Log.e("CHEGOU ATE AQUI?", conteudo);

        String tipoUsuario = verificarTipoUsuario(conteudo);
        conteudo = criarJson(conteudo);

        if (tipoUsuario.equals("aluno")) {

            List<AlunoModel> alunosModel = AlunoJSONParser.parseDados(conteudo);
            AlunoModel alunoModel = alunosModel.get(0);

            adicionarPreferences(alunoModel);

            startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
            finish();

        } else {

            List<ProfissionalModel> profissionaisModel = ProfissionalJSONParser.parseDados(conteudo);
            ProfissionalModel profissionalModel = profissionaisModel.get(0);

            if (profissionalModel.getFkUsuario().getStatus() == 0) {

                adicionarPreferences(profissionalModel);

                startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                finish();

            } else {
                Toast.makeText(LoginActivity.this, "Usuário desativado!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //realiza as configurações para enviar dados ao banco de dados
    public void autenticarUsuario(UsuarioModel usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        autenticarUsuario task = new autenticarUsuario();
        task.execute(requestHttp);

    }

    //tarefa assincrona que recebe os dados do banco de dados
    private class autenticarUsuario extends AsyncTask<RequestHttp, Boolean, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.equals("Vazio"))
                return false;
            else {
                return true;
            }

        }

        protected void onPostExecute(boolean sucess) {
            super.onPostExecute(sucess);

            if(sucess == true)
                login(conteudo);
            else
                Toast.makeText(LoginActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
        }
    }

}
