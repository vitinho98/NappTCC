package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        txtCadastrar = findViewById(R.id.txt_cadastrar);
        imgSobre = findViewById(R.id.img_sobre);

        textInputLayoutLogin = findViewById(R.id.txt_layout_login);
        textInputLayoutSenha = findViewById(R.id.txt_layout_senha);

        editTextLogin = findViewById(R.id.edit_text_login);
        editTextSenha = findViewById(R.id.edit_text_senha);

        btnLogin = findViewById(R.id.btn_entrar);

        if (conferirShared()) {

            String tipoUsuario = preferences.getString("tipoUsuario", null);

            if (tipoUsuario.equals("aluno")) {

                startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
                finish();

            } else {

                UsuarioModel usuarioModel = new UsuarioModel();
                usuarioModel.setIdUsuario(preferences.getInt("idUsuario", 0));

                UsuarioController usuarioController = new UsuarioController();

                if (usuarioController.isAtivo(usuarioModel)) {

                    startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                    finish();

                }
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarForm()) {

                    UsuarioModel usuarioModel = new UsuarioModel();
                    UsuarioController usuarioController = new UsuarioController();

                    usuarioModel.setLogin(editTextLogin.getText().toString());
                    usuarioModel.setSenha(editTextSenha.getText().toString());

                    UsuarioController.autenticarUsuario(usuarioModel);
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

    private void adicionarPreferencesAluno(AlunoModel aluno){

        SharedPreferences preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        editor = preferences.edit();

        editor.putInt("idUsuario", aluno.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", aluno.getFkUsuario().getTipoUsuario());

        editor.putInt("status", aluno.getFkUsuario().getStatus());
        editor.putInt("idAluno", aluno.getIdAluno());

        editor.putBoolean("conected", true);

        editor.commit();

    }

    private void adicionarPreferencesProfissional(ProfissionalModel profissional){

        editor = preferences.edit();
        Log.e("a", "aaa");
        editor.putInt("idUsuario", profissional.getFkUsuario().getIdUsuario());
        editor.putString("tipoUsuario", profissional.getFkUsuario().getTipoUsuario());

        editor.putInt("status", profissional.getFkUsuario().getStatus());
        editor.putInt("idProfissional", profissional.getIdProfissional());

        editor.putBoolean("conected", true);

        editor.commit();

    }

    private boolean conferirShared(){

        boolean resultado = preferences.getBoolean("conected", false);

        if(resultado){
            return true;
        }else{
            return false;
        }

    }

    private String verificarTipoUsuario(String conteudo){

        int fim = conteudo.indexOf("@");

        String tipo = conteudo.substring(0, fim);

        return tipo;

    }

    private String criarJson(String conteudo){

        int inicio = conteudo.indexOf("@");

        String json = conteudo.substring(inicio+1);

        return json;

    }

    public void login(String conteudo){

        if (conteudo == null) {

            Log.e("CHEGOU ATE AQUI?", "taaq");

            Toast.makeText(LoginActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();

        } else {
            Log.e("CHEGOU ATE AQUI?", conteudo);


            String tipoUsuario = verificarTipoUsuario(conteudo);
            conteudo = criarJson(conteudo);



            if (tipoUsuario.equals("aluno")) {

                List<AlunoModel> alunosModel = AlunoJSONParser.parseDados(conteudo);
                AlunoModel alunoModel = alunosModel.get(0);

                adicionarPreferencesAluno(alunoModel);
                startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));
                finish();

            } else {

                List<ProfissionalModel> profissionaisModel = ProfissionalJSONParser.parseDados(conteudo);
                ProfissionalModel profissionalModel = profissionaisModel.get(0);

                if (profissionalModel.getFkUsuario().getStatus() == 0) {

                    adicionarPreferencesProfissional(profissionalModel);
                    startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Usuário desativado!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
