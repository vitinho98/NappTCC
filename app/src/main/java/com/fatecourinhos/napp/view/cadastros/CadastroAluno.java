package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Usuario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.LoginActivity;

public class CadastroAluno extends AppCompatActivity {

    //variaveis gloabais
    private String conteudo;
    private boolean sucesso;
    private Usuario usuario;

    //componentes da tela
    private AppCompatEditText editTextCpf, editTextRa;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_aluno);

        usuario = new Usuario();
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

                if (conferirDados())
                    inserirAluno(usuario);
                else
                    Toast.makeText(CadastroAluno.this, "Insira todos os dados!", Toast.LENGTH_LONG).show();

            }
        });

    }

    private boolean conferirDados() {

        if (editTextCpf.getText().toString().isEmpty())
            return false;
        else if (editTextRa.getText().toString().isEmpty())
            return false;
        else
            return true;

    }

    private void inserirAluno(Usuario usuario) {

        String uri = "http://vitorsilva.xyz/napp/aluno/inserirAluno.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirAluno task = new InserirAluno();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("raAluno", usuario.getLogin());
        requestHttp.setParametro("cpfAluno",usuario.getSenha());
        requestHttp.setParametro("statusAluno",String.valueOf(usuario.getStatus()));
        requestHttp.setParametro("tipoAluno", usuario.getTipoUsuario());

        task.execute(requestHttp);

    }

    private class InserirAluno extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            try {

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
                Toast.makeText(CadastroAluno.this, R.string.msg_sucesso_cadastro, Toast.LENGTH_LONG).show();
                startActivity(new Intent(CadastroAluno.this, LoginActivity.class));
                finish();
            } else
                Toast.makeText(CadastroAluno.this, R.string.msg_erro_cadastro, Toast.LENGTH_LONG).show();

        }
    }

}
