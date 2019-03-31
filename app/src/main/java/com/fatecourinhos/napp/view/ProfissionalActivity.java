package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.widget.EditText;

import com.fatecourinhos.napp.R;

public class ProfissionalActivity extends AppCompatActivity {

    private EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_cadastro);

        if((getIntent().getExtras()!=null)) {

            String nome = (String) getIntent().getExtras().get("nome");

            editTextNome = findViewById(R.id.edit_text_nome_profissional);

            editTextNome.setText(nome);
        }
    }
}
