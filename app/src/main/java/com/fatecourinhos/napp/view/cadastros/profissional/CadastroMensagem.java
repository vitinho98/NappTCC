package com.fatecourinhos.napp.view.cadastros.profissional;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fatecourinhos.napp.R;

public class CadastroMensagem extends AppCompatActivity {

    private Button btnSelecionarAlunos;
    private EditText editTextMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensagem);

        btnSelecionarAlunos = findViewById(R.id.btn_selecionar_alunos);
        editTextMsg = findViewById(R.id.editTextMsg);

        btnSelecionarAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextMsg.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Insira a mensagem!", Toast.LENGTH_SHORT);
                else {
                    Intent intent = new Intent(CadastroMensagem.this, CadastroMensagem2.class);
                    intent.putExtra("msg", editTextMsg.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }

}
