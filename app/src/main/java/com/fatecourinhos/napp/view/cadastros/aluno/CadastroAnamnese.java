package com.fatecourinhos.napp.view.cadastros.aluno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Anamnese;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CadastroAnamnese extends AppCompatActivity {

    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9;
    private Button btnAnamnese;

    private SharedPreferences preferences;
    private String conteudo;
    private boolean sucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anamnese);
        getComponentes();

        if (getIntent().getExtras() != null) {

            int idAluno = getIntent().getExtras().getInt("idAluno");
            btnAnamnese.setEnabled(false);
            btnAnamnese.setVisibility(View.INVISIBLE);

        } else
            btnAnamnese.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Anamnese anamnese = new Anamnese();
                    Aluno aluno = new Aluno();

                    if (preferences.contains("idAluno")) {
                        aluno.setIdAluno(preferences.getInt("idAluno", 0));
                        anamnese.setFkAluno(aluno);
                    }

                    anamnese.setQuestao1(rg1.getCheckedRadioButtonId());
                    anamnese.setQuestao2(rg2.getCheckedRadioButtonId());
                    anamnese.setQuestao3(rg3.getCheckedRadioButtonId());
                    anamnese.setQuestao4(rg4.getCheckedRadioButtonId());
                    anamnese.setQuestao5(rg5.getCheckedRadioButtonId());
                    anamnese.setQuestao6(rg6.getCheckedRadioButtonId());
                    anamnese.setQuestao7(rg7.getCheckedRadioButtonId());
                    anamnese.setQuestao8(rg8.getCheckedRadioButtonId());
                    anamnese.setQuestao9(rg9.getCheckedRadioButtonId());

                    cadastrarAnamnese(anamnese);
                }
            });

    }

    private void getComponentes() {

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        btnAnamnese = findViewById(R.id.btn_anamnese);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);
        rg4 = findViewById(R.id.rg4);
        rg5 = findViewById(R.id.rg5);
        rg6 = findViewById(R.id.rg6);
        rg7 = findViewById(R.id.rg7);
        rg8 = findViewById(R.id.rg8);
        rg9 = findViewById(R.id.rg9);

    }

    private void cadastrarAnamnese(Anamnese anamnese){

        String uri = "http://vitorsilva.xyz/napp/agendamento/cadastrarAnamnese.php";
        CadastrarAnamnese mytask = new CadastrarAnamnese();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setUrl(uri);
        requestHttp.setMetodo("GET");
        requestHttp.setParametro("fkAluno",  String.valueOf(anamnese.getFkAluno().getIdAluno()));
        requestHttp.setParametro("Questao1", String.valueOf(anamnese.getQuestao1()));
        requestHttp.setParametro("Questao2", String.valueOf(anamnese.getQuestao2()));
        requestHttp.setParametro("Questao3", String.valueOf(anamnese.getQuestao3()));
        requestHttp.setParametro("Questao4", String.valueOf(anamnese.getQuestao4()));
        requestHttp.setParametro("Questao5", String.valueOf(anamnese.getQuestao5()));
        requestHttp.setParametro("Questao6", String.valueOf(anamnese.getQuestao6()));
        requestHttp.setParametro("Questao7", String.valueOf(anamnese.getQuestao7()));
        requestHttp.setParametro("Questao8", String.valueOf(anamnese.getQuestao8()));
        requestHttp.setParametro("Questao9", String.valueOf(anamnese.getQuestao9()));

        mytask.execute(requestHttp);

    }

    private class CadastrarAnamnese extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
        }

    }

}
