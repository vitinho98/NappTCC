package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fatecourinhos.napp.R;

public class CadastroAluno extends AppCompatActivity {

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
                
            }
        });

    }

}
