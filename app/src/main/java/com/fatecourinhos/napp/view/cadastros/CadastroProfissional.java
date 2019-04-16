package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

public class CadastroProfissional extends AppCompatActivity {

    final ProfissionalModel profissional = new ProfissionalModel();
    final UsuarioModel usuario = new UsuarioModel();
    private AppCompatEditText editTextNome, editTextCel, editTextEmail, editTextLogin, editTextSenha;
    private Spinner spinnerProf;
    private Button btn_cadastrar_profissional;
    private Switch switchProf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional);

        editTextNome = findViewById(R.id.edit_text_nome_profissional);
        editTextCel = findViewById(R.id.edit_text_celular_profissional);
        editTextEmail = findViewById(R.id.edit_text_email_profissional);
        editTextLogin = findViewById(R.id.edit_text_login_profissional);
        editTextSenha = findViewById(R.id.edit_text_senha_profissional);

        spinnerProf = findViewById(R.id.spinnerTipoUsuario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_prof_array, android.R.layout.simple_spinner_item);
        spinnerProf.setAdapter(adapter);


        switchProf = findViewById(R.id.switchStatus);

        btn_cadastrar_profissional = findViewById(R.id.btn_salvar_profissional);
        /*
        if ((getIntent().getExtras() != null)) {

            String nome = (String) getIntent().getExtras().get("nomeProfissional");
            String celular = (String) getIntent().getExtras().get("celularProfissional");
            String email = (String) getIntent().getExtras().get("emailProfissional");
            String login = (String) getIntent().getExtras().get("loginProfissional");
            String senha = (String) getIntent().getExtras().get("senhaProfissional");
            String tipo = (String) getIntent().getExtras().get("tipoProfissional");
            String status = (String) getIntent().getExtras().get("statusProfissional");

            editTextCel.setText(celular);
            editTextEmail.setText(email);
            editTextLogin.setText(login);
            editTextSenha.setText(senha);
            editTextNome.setText(nome);

            if(tipo.equals("Administrador")){
                spinnerProf.setSelection(0);
            }else{
                spinnerProf.setSelection(1);
            }

            if(Integer.parseInt(status) == 1){
                switchProf.setChecked(false);
            }else{
                switchProf.setChecked(true);
            }
        }
        */

        btn_cadastrar_profissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDados();
            }
        });

    }

    private void enviarDados() {
        profissional.setNomeProfissional(editTextNome.getText().toString());
        profissional.setCelularProfissional(editTextCel.getText().toString());
        profissional.setEmailProfissional(editTextEmail.getText().toString());

        usuario.setLogin(editTextLogin.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());

        spinnerProf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        usuario.setTipoUsuario("Administrador");
                        break;
                    case 1:
                        usuario.setTipoUsuario("Profissional");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                usuario.setTipoUsuario("Administrador");
            }

        });

        if (switchProf.isChecked()) {
            usuario.setStatus(0);
        } else {
            usuario.setStatus(1);
        }

        profissional.setFkUsuario(usuario);

        if(ProfissionalController.conferirDados(profissional)){
            if(ProfissionalController.inserir(profissional))
                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG);
            else
                Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_LONG);
        }else
            Toast.makeText(this, "Insira todos os campos!", Toast.LENGTH_LONG);

    }

}

