package com.fatecourinhos.napp.view.cadastros;

import android.os.Bundle;
import android.widget.Button;

import com.fatecourinhos.napp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroResponsavel extends AppCompatActivity {

    private AppCompatEditText editTextNomeResponsavel, editTextEmailResponsavel, editTextCelularResponsavel, editTextTelefoneResponsavel;
    private Button btn_cadastrar_responsavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_responsavel);

        editTextCelularResponsavel = (AppCompatEditText)findViewById(R.id.edit_text_celular_responsavel);
        editTextEmailResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_email_responsavel);
        editTextNomeResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_nome_responsavel);
        editTextTelefoneResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_telefone_responsavel);

        btn_cadastrar_responsavel = (Button) findViewById(R.id.btn_salvar_responsavel);

        if(getIntent().getExtras() != null){

            editTextTelefoneResponsavel.setText(getIntent().getExtras().getString("telefoneResponsavel"));
            editTextNomeResponsavel.setText(getIntent().getExtras().getString("nomeResponsavel"));
            editTextCelularResponsavel.setText(getIntent().getExtras().getString("celularResponsavel"));
            editTextEmailResponsavel.setText(getIntent().getExtras().getString("emailResponsavel"));

        }

    }
}
