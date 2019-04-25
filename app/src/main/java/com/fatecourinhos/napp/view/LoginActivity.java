package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.UsuarioController;
import com.fatecourinhos.napp.model.UsuarioModel;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText editTextLogin, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.edit_text_login);
        editTextSenha = findViewById(R.id.edit_text_senha);

        Button btn = (Button) findViewById(R.id.btn_entrar);
    }

    public void ir(View v){

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setLogin(editTextLogin.getText().toString());
        usuarioModel.setSenha(editTextSenha.getText().toString());

        UsuarioController.autenticarUsuario(usuarioModel);
        if(UsuarioController.usuarioModelList != null){

            //usuarioModel = UsuarioController.usuarioModelList.get(0);

            if(usuarioModel.getTipoUsuario().equals("aluno")){

                startActivity(new Intent(LoginActivity.this, MenuAlunoActivity.class));

            }else{

                if(usuarioModel.getStatus() == 0){

                    startActivity(new Intent(LoginActivity.this, MenuProfissionalActivity.class));

                }else{

                    Toast.makeText(LoginActivity.this,"Usuário desativado!",Toast.LENGTH_LONG).show();

                }

            }
        }else{
            Toast.makeText(LoginActivity.this,"Usuário não encontrado!",Toast.LENGTH_LONG).show();
        }

    }
}
