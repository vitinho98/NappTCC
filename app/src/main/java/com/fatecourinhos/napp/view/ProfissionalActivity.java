package com.fatecourinhos.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.HttpManager;
import com.fatecourinhos.napp.controller.RequestHttp;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.model.UsuarioModel;

public class ProfissionalActivity extends AppCompatActivity {

    final ProfissionalModel profissional = new ProfissionalModel();
    final UsuarioModel usuario = new UsuarioModel();
    private AppCompatEditText editTextNome, editTextCel, editTextEmail, editTextLogin, editTextSenha;
    private Spinner spinnerProf;
    private Button btn_cadastrar_profissional;
    private Switch switchProf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_cadastro);

        editTextNome = findViewById(R.id.edit_text_nome_profissional);
        editTextCel = findViewById(R.id.edit_text_telefone_profissional);
        editTextEmail = findViewById(R.id.edit_text_email_profissional);
        editTextLogin = findViewById(R.id.edit_text_login_profissional);
        editTextSenha = findViewById(R.id.edit_text_senha_profissional);
        spinnerProf = findViewById(R.id.spinnerProf);
        switchProf = findViewById(R.id.switchProf);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_prof_array, android.R.layout.simple_spinner_item);
        spinnerProf.setAdapter(adapter);

        btn_cadastrar_profissional = findViewById(R.id.btn_cadastrar_profissional);



        if((getIntent().getExtras()!=null)) {

            String nome = (String) getIntent().getExtras().get("nomeProfissional");

            editTextNome = findViewById(R.id.edit_text_nome_profissional);

            editTextNome.setText(nome);
        }

        btn_cadastrar_profissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarDados("http://192.168.0.11/testetcc/APIIncluirDados.php");

            }
        });


    }

    private void enviarDados(String uri){
        profissional.setNomeProfissional(editTextNome.getText().toString());
        profissional.setCelularProfissional(editTextCel.getText().toString());
        profissional.setEmailProfissional(editTextEmail.getText().toString());
        usuario.setLogin(editTextLogin.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());

        usuario.setTipoUsuario("Administrador");
        spinnerProf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
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

        usuario.setStatus(1);

        if(switchProf.isChecked()){
            usuario.setStatus(0);
        }else{
            usuario.setStatus(1);
        }

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("loginProfissional", usuario.getLogin());
        requestHttp.setParametro("senhaProfissional", usuario.getSenha());
        requestHttp.setParametro("tipoProfissional", usuario.getTipoUsuario());
        requestHttp.setParametro("statusProfissional", String.valueOf(usuario.getStatus()));

        MyTask task = new MyTask();
        task.execute(requestHttp);
    }


    private class MyTask extends AsyncTask<RequestHttp, String, String>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ProfissionalActivity.this, conteudo, Toast.LENGTH_LONG).show();
                }
            });
            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            //Intent intent = new Intent(ProfissionalActivity.this, MenuProfissionalActivity.class);
            //startActivity(intent);
            //finish();
        }
    }
}
