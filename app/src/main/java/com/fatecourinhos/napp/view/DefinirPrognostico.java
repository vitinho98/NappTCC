package com.fatecourinhos.napp.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class DefinirPrognostico extends Activity {

    private CheckBox cbAtendimento7dias, cbAtendimento15dias, cbEncaminhamento, cbContatoFamiliar, cbContatoProfessor;
    private Button btnConfirmar;
    private EditText editTextObs;

    private int opcao1, opcao2, opcao3, opcao4, opcao5;

    private String conteudo;
    private boolean sucesso;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prognostico);

        cbAtendimento7dias = findViewById(R.id.cbatendimento7dias);
        cbAtendimento15dias = findViewById(R.id.cbatendimento15dias);
        cbEncaminhamento = findViewById(R.id.cbencaminhamento);
        cbContatoFamiliar = findViewById(R.id.cbcontatofamiliar);
        cbContatoProfessor = findViewById(R.id.cbcontatoprofessor);

        editTextObs = findViewById(R.id.edittextobsprognostico);
        btnConfirmar = findViewById(R.id.btn_confirmar_prognostico);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbAtendimento7dias.isChecked())
                    opcao1 = 0;
                else
                    opcao1 = 1;

                if (cbAtendimento15dias.isChecked())
                    opcao2 = 0;
                else
                    opcao2 = 1;

                if (cbEncaminhamento.isChecked())
                    opcao3 = 0;
                else
                    opcao3 = 1;

                if (cbContatoProfessor.isChecked())
                    opcao4 = 0;
                else
                    opcao4 = 1;

                if (cbContatoFamiliar.isChecked())
                    opcao5 = 0;
                else
                    opcao5 = 1;

                inserirPrognostico();

            }
        });

    }

    private void inserirPrognostico() {

        String uri = "http://vitorsilva.xyz/napp/atendimento/inserirPrognostico.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirPrognostico task = new InserirPrognostico();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(getIntent().getExtras().getInt("idAgendamento")));
        requestHttp.setParametro("opcao1", String.valueOf(opcao1));
        requestHttp.setParametro("opcao2", String.valueOf(opcao2));
        requestHttp.setParametro("opcao3", String.valueOf(opcao3));
        requestHttp.setParametro("opcao4", String.valueOf(opcao4));
        requestHttp.setParametro("opcao5", String.valueOf(opcao5));
        requestHttp.setParametro("obs", editTextObs.getText().toString());

        task.execute(requestHttp);

    }

    private class InserirPrognostico extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

}
