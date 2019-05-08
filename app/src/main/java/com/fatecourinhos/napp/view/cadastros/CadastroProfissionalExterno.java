package com.fatecourinhos.napp.view.cadastros;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.fatecourinhos.napp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroProfissionalExterno extends AppCompatActivity {

    private AppCompatEditText editTextNomeProfissionalExterno, editTextTelefoneProfissionalExterno, editTextCelularProfissionalExterno,
    editTextBairro, editTextNumero, editTextCidadeProfissionalExterno, editTextEndereco, editTextEmailProfissionalExterno;

    private Spinner spinnerTipo, spinnerResponsavel;

    private Button btn_cadastrar_responsavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional_externo);

        editTextBairro = (AppCompatEditText) findViewById(R.id.edit_text_bairro_profissional_externo);
        editTextCelularProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_celular_profissional_externo);
        editTextCidadeProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_cidade_profissional_externo);
        editTextEmailProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_email_profissional_externo);
        editTextEndereco = (AppCompatEditText) findViewById(R.id.edit_text_endereco_profissional_externo);
        editTextTelefoneProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_telefone_profissional_externo);
        editTextNomeProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_nome_profissional_externo);
        editTextNumero = (AppCompatEditText) findViewById(R.id.edit_text_numero_profissional_externo);

        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo);
        spinnerResponsavel = (Spinner) findViewById(R.id.spinnerResponsavel);

        btn_cadastrar_responsavel = (Button) findViewById(R.id.btn_salvar_responsavel);

        if(getIntent().getExtras() != null){

            editTextNumero.setText(getIntent().getExtras().getString("numero"));
            editTextNomeProfissionalExterno.setText(getIntent().getExtras().getString("nomeProfissionalExternos"));
            editTextTelefoneProfissionalExterno.setText(getIntent().getExtras().getString("telefoneProfissionalExterno"));
            editTextEndereco.setText(getIntent().getExtras().getString("endereco"));
            editTextBairro.setText(getIntent().getExtras().getString("bairro"));
            editTextCidadeProfissionalExterno.setText(getIntent().getExtras().getString("cidadeProfissionalExterno"));
            editTextCelularProfissionalExterno.setText(getIntent().getExtras().getString("celularProfissionalExterno"));
            editTextEmailProfissionalExterno.setText(getIntent().getExtras().getString("emailProfissionalExterno"));

        }
    }
}
