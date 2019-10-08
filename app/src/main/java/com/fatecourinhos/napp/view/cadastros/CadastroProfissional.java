package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroProfissional extends AppCompatActivity {

    //variaveis gloabaais
    private Profissional profissional;
    private Usuario usuario;
    private String conteudo;
    private boolean sucesso;

    //componentes da tela
    private AppCompatEditText editTextNome, editTextCel, editTextEmail, editTextLogin, editTextSenha;
    private Spinner spinnerProf;
    private Button btnCadastrar;
    private Switch switchProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional);
        getComponentes();

        if (getIntent().getExtras() != null) {

            profissional.setNomeProfissional((String) getIntent().getExtras().get("nomeProfissional"));
            profissional.setCelularProfissional((String) getIntent().getExtras().get("celularProfissional"));
            profissional.setEmailProfissional((String) getIntent().getExtras().get("emailProfissional"));
            profissional.setIdProfissional(getIntent().getExtras().getInt("idProfissional"));

            usuario.setLogin((String) getIntent().getExtras().get("loginProfissional"));
            usuario.setSenha((String) getIntent().getExtras().get("senhaProfissional"));
            usuario.setTipoUsuario((String) getIntent().getExtras().get("tipoProfissional"));
            usuario.setStatus((Integer) getIntent().getExtras().get("statusProfissional"));

            profissional.setFkUsuario(usuario);

            editTextCel.setText(profissional.getCelularProfissional());
            editTextEmail.setText(profissional.getEmailProfissional());
            editTextNome.setText(profissional.getNomeProfissional());

            editTextLogin.setEnabled(false);
            editTextSenha.setEnabled(false);

            if (profissional.getFkUsuario().getTipoUsuario().equals("Administrador"))
                spinnerProf.setSelection(0);
            else
                spinnerProf.setSelection(1);

            try {

                if (profissional.getFkUsuario().getStatus() == 1)
                    switchProf.setChecked(false);
                else
                    switchProf.setChecked(true);

            } catch (Exception e) {
                switchProf.setChecked(false);
            }

            btnCadastrar.setText(R.string.btn_salvar);
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(false);
                }
            });

        } else {

            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enviarDados(true);
                }
            });

        }
    }

    private void getComponentes() {

        usuario = new Usuario();
        profissional = new Profissional();

        editTextNome = findViewById(R.id.edit_text_nome_profissional);
        editTextCel = findViewById(R.id.edit_text_celular_profissional);
        editTextEmail = findViewById(R.id.edit_text_email_profissional);
        editTextLogin = findViewById(R.id.edit_text_login_profissional);
        editTextSenha = findViewById(R.id.edit_text_senha_profissional);

        SimpleMaskFormatter maskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtwCelular = new MaskTextWatcher(editTextCel, maskCelular);
        editTextCel.addTextChangedListener(mtwCelular);

        btnCadastrar = findViewById(R.id.btn_salvar_profissional);
        switchProf = findViewById(R.id.switchStatus);

        spinnerProf = findViewById(R.id.spinnerTipoUsuario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_prof_array, android.R.layout.simple_spinner_item);
        spinnerProf.setAdapter(adapter);

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
                    default:
                        usuario.setTipoUsuario("Administrador");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                usuario.setTipoUsuario("Administrador");
            }

        });

    }

    private void enviarDados(boolean inserir) {

        profissional.setNomeProfissional(editTextNome.getText().toString());
        profissional.setCelularProfissional(editTextCel.getText().toString());
        profissional.setEmailProfissional(editTextEmail.getText().toString());

        usuario.setLogin(editTextLogin.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());

        if (switchProf.isChecked())
            usuario.setStatus(0);
        else
            usuario.setStatus(1);

        profissional.setFkUsuario(usuario);

        if (conferirDados(profissional)) {

            if (inserir)
                inserirProfissional(profissional);
            else
                alterarProfissional(profissional);

        } else
            Toast.makeText(this, "Insira todos os campos corretamente!", Toast.LENGTH_LONG).show();

    }

    public boolean conferirDados(Profissional profissional) {

        boolean retorno = true;

        if (profissional.getCelularProfissional().isEmpty())
            retorno = false;
        else if (profissional.getEmailProfissional().isEmpty())
            retorno = false;
        else if (profissional.getNomeProfissional().isEmpty())
            retorno = false;
        else if (profissional.getFkUsuario().getLogin().isEmpty())
            retorno = false;
        else if (profissional.getFkUsuario().getSenha().isEmpty())
            retorno = false;

        return retorno;

    }

    public void inserirProfissional(Profissional profissional) {

        String uri = "http://vitorsilva.xyz/napp/profissional/inserirProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirProfissional task = new InserirProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("loginProfissional", profissional.getFkUsuario().getLogin());
        requestHttp.setParametro("senhaProfissional", profissional.getFkUsuario().getSenha());
        requestHttp.setParametro("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
        requestHttp.setParametro("statusProfissional", String.valueOf(profissional.getFkUsuario().getStatus()));

        task.execute(requestHttp);

    }

    public void alterarProfissional(Profissional profissional) {

        String uri = "http://vitorsilva.xyz/napp/profissional/alterarProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarProfissional task = new AlterarProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idProfissional", String.valueOf(profissional.getIdProfissional()));
        requestHttp.setParametro("nomeProfissional", profissional.getNomeProfissional());
        requestHttp.setParametro("celProfissional", profissional.getCelularProfissional());
        requestHttp.setParametro("emailProfissional", profissional.getEmailProfissional());
        requestHttp.setParametro("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
        requestHttp.setParametro("statusProfissional", String.valueOf(profissional.getFkUsuario().getStatus()));
        requestHttp.setParametro("fkUsuario", String.valueOf(profissional.getFkUsuario().getIdUsuario()));

        task.execute(requestHttp);

    }

    private class InserirProfissional extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo =  HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

    private class AlterarProfissional extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();
        }

    }

}

