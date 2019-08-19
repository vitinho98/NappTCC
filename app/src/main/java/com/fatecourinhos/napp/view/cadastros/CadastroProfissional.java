package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;

public class CadastroProfissional extends AppCompatActivity {

    final Profissional profissional = new Profissional();
    final Usuario usuario = new Usuario();
    final CampoAtuacaoModel campoAtuacao = new CampoAtuacaoModel();

    private AppCompatEditText editTextNome, editTextCel, editTextEmail, editTextLogin, editTextSenha;
    private ImageView foto;
    private Spinner spinnerProf;
    private Button btn_cadastrar_profissional;
    private Switch switchProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional);

        foto = findViewById(R.id.img_profissional);

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

        if ((getIntent().getExtras() != null)) {

            profissional.setNomeProfissional((String) getIntent().getExtras().get("nomeProfissional"));
            profissional.setCelularProfissional((String) getIntent().getExtras().get("celularProfissional"));
            profissional.setEmailProfissional((String) getIntent().getExtras().get("emailProfissional"));
            profissional.setIdProfissional(getIntent().getExtras().getInt("idProfissional"));

            campoAtuacao.setNomeCampoAtuacao((String) getIntent().getExtras().get("nomeCampoAtuacao"));
            campoAtuacao.setIdCampoAtuacao((Integer) getIntent().getExtras().get("idCampoAtuacao"));

            usuario.setLogin((String) getIntent().getExtras().get("loginProfissional"));
            usuario.setSenha((String) getIntent().getExtras().get("senhaProfissional"));
            usuario.setTipoUsuario((String) getIntent().getExtras().get("tipoProfissional"));
            usuario.setStatus((Integer) getIntent().getExtras().get("statusProfissional"));

            profissional.setFkUsuario(usuario);
            profissional.setCampoAtuacao(campoAtuacao);

            editTextCel.setText(profissional.getCelularProfissional());
            editTextEmail.setText(profissional.getEmailProfissional());
            editTextLogin.setText(profissional.getFkUsuario().getLogin());
            editTextSenha.setText(profissional.getFkUsuario().getSenha());
            editTextNome.setText(profissional.getNomeProfissional());

            if(profissional.getFkUsuario().getTipoUsuario().equals("Administrador")){
                spinnerProf.setSelection(0);
            }else{
                spinnerProf.setSelection(1);
            }

            try {

                if (profissional.getFkUsuario().getStatus() == 1) {
                    switchProf.setChecked(false);
                } else {
                    switchProf.setChecked(true);
                }

            }catch (Exception e){
                System.out.println(e.toString());
            }

            btn_cadastrar_profissional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(false);
                }
            });

        }else{

            btn_cadastrar_profissional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enviarDados(true);
                }
            });

        }
    }

    private void enviarDados(boolean inserir) {

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

        if(conferirDados(profissional)){

            if(inserir == true){

                if(ProfissionalController.inserirProfissional(profissional)) {
                    Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
                    limparDados();
                }else
                    Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_LONG).show();

            }else{

                if(ProfissionalController.alterarProfissional(profissional)){
                    Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_LONG).show();
                    limparDados();
                }else
                    Toast.makeText(this, "Erro ao alterar", Toast.LENGTH_LONG).show();
            }

        }else
            Toast.makeText(this, "Insira todos os campos corretamente!", Toast.LENGTH_LONG).show();

    }

    public boolean conferirDados(Profissional profissional){

        boolean retorno = true;


        return retorno;
    }

    public void limparDados(){

        editTextCel.setText(null);
        editTextEmail.setText(null);
        editTextLogin.setText(null);
        editTextSenha.setText(null);
        editTextNome.setText(null);

        foto.setImageBitmap(null);

    }
}

