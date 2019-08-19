package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.AlunoController;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.view.LoginActivity;

public class CadastroAluno extends AppCompatActivity {

    Usuario usuario = new Usuario();
    AppCompatEditText editTextCpf, editTextRa;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_aluno);

        editTextCpf = findViewById(R.id.edit_text_cpf_aluno);
        editTextRa = findViewById(R.id.edit_text_ra_aluno);

        btnCadastrar = findViewById(R.id.btn_cadastrar_aluno);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario.setLogin(editTextRa.getText().toString());
                usuario.setSenha(editTextCpf.getText().toString());
                usuario.setTipoUsuario("Aluno");
                usuario.setStatus(0);

                if(AlunoController.inserirAluno(usuario)){
                    Toast.makeText(CadastroAluno.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CadastroAluno.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(CadastroAluno.this, "Erro ao inserir", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
