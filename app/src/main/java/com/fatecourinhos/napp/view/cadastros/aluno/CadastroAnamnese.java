package com.fatecourinhos.napp.view.cadastros.aluno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.json.AnamneseJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Anamnese;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.consultaraluno.DadosPessoaisAluno;

import java.util.List;

public class CadastroAnamnese extends AppCompatActivity {

    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9;
    private Button btnAnamnese;
    private ProgressBar progressBar;

    private SharedPreferences preferences;
    private List<Anamnese> anamneseList;
    private String conteudo;
    private boolean sucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anamnese);
        getComponentes();

        if (getIntent().getExtras() != null) {

            btnAnamnese.setEnabled(false);
            btnAnamnese.setVisibility(View.INVISIBLE);

            int idAluno = getIntent().getExtras().getInt("idAluno");
            selecionarAnamnese(idAluno);

        } else
            btnAnamnese.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id1 = rg1.getCheckedRadioButtonId();
                    int id2 = rg2.getCheckedRadioButtonId();
                    int id3 = rg3.getCheckedRadioButtonId();
                    int id4 = rg4.getCheckedRadioButtonId();
                    int id5 = rg5.getCheckedRadioButtonId();
                    int id6 = rg6.getCheckedRadioButtonId();
                    int id7 = rg7.getCheckedRadioButtonId();
                    int id8 = rg8.getCheckedRadioButtonId();
                    int id9 = rg9.getCheckedRadioButtonId();

                    rb1 = findViewById(id1);
                    rb2 = findViewById(id2);
                    rb3 = findViewById(id3);
                    rb4 = findViewById(id4);
                    rb5 = findViewById(id5);
                    rb6 = findViewById(id6);
                    rb7 = findViewById(id7);
                    rb8 = findViewById(id8);
                    rb9 = findViewById(id9);

                    Anamnese anamnese = new Anamnese();
                    Aluno aluno = new Aluno();

                    if (preferences.contains("idAluno")) {
                        aluno.setIdAluno(preferences.getInt("idAluno", 0));
                        anamnese.setFkAluno(aluno);
                    }

                    anamnese.setQuestao1(String.valueOf(rb1.getText()));
                    anamnese.setQuestao2(String.valueOf(rb2.getText()));
                    anamnese.setQuestao3(String.valueOf(rb3.getText()));
                    anamnese.setQuestao4(String.valueOf(rb4.getText()));
                    anamnese.setQuestao5(String.valueOf(rb5.getText()));
                    anamnese.setQuestao6(String.valueOf(rb6.getText()));
                    anamnese.setQuestao7(String.valueOf(rb7.getText()));
                    anamnese.setQuestao8(String.valueOf(rb8.getText()));
                    anamnese.setQuestao9(String.valueOf(rb9.getText()));

                    cadastrarAnamnese(anamnese);

                }
            });

    }

    private void mostrarAnamnese(Anamnese anamnese) {

        if (anamnese.getQuestao1().equals("Sim"))
            ((RadioButton)rg1.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao1().equals("Não"))
            ((RadioButton)rg1.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg1.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao2().equals("Sim"))
            ((RadioButton)rg2.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao2().equals("Não"))
            ((RadioButton)rg2.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg2.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao3().equals("Sim"))
            ((RadioButton)rg3.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao3().equals("Não"))
            ((RadioButton)rg3.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg3.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao4().equals("Sim"))
            ((RadioButton)rg4.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao4().equals("Não"))
            ((RadioButton)rg4.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg4.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao5().equals("Sim"))
            ((RadioButton)rg5.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao5().equals("Não"))
            ((RadioButton)rg5.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg5.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao6().equals("Sim"))
            ((RadioButton)rg6.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao6().equals("Não"))
            ((RadioButton)rg6.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg6.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao7().equals("Sim"))
            ((RadioButton)rg7.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao7().equals("Não"))
            ((RadioButton)rg7.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg7.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao8().equals("Sim"))
            ((RadioButton)rg8.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao8().equals("Não"))
            ((RadioButton)rg8.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg8.getChildAt(2)).setChecked(true);

        if (anamnese.getQuestao9().equals("Sim"))
            ((RadioButton)rg9.getChildAt(0)).setChecked(true);
        else if (anamnese.getQuestao9().equals("Não"))
            ((RadioButton)rg9.getChildAt(1)).setChecked(true);
        else
            ((RadioButton)rg9.getChildAt(2)).setChecked(true);


        desabilitarRadioButton();
        progressBar.setVisibility(View.INVISIBLE);

    }

    private void desabilitarRadioButton() {

        for (int i = 0; i < rg1.getChildCount(); i++)
            (rg1.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg2.getChildCount(); i++)
            (rg2.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg3.getChildCount(); i++)
            (rg3.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg4.getChildCount(); i++)
            (rg4.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg5.getChildCount(); i++)
            (rg5.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg6.getChildCount(); i++)
            (rg6.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg7.getChildCount(); i++)
            (rg7.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg8.getChildCount(); i++)
            (rg8.getChildAt(i)).setEnabled(false);

        for (int i = 0; i < rg9.getChildCount(); i++)
            (rg9.getChildAt(i)).setEnabled(false);

    }

    private void selecionarAnamnese(int idAluno) {

        String uri = "http://vitorsilva.xyz/napp/anamnese/selecionarAnamnese.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarAnamnese task = new SelecionarAnamnese();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAluno", String.valueOf(idAluno));

        task.execute(requestHttp);

    }

    private class SelecionarAnamnese extends AsyncTask<RequestHttp, String, List<Anamnese>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Anamnese> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            anamneseList = AnamneseJSONParser.parseDados(conteudo);
            return anamneseList;
        }

        @Override
        protected void onPostExecute(List<Anamnese> anamneseList) {
            super.onPostExecute(anamneseList);

            try {
                mostrarAnamnese(anamneseList.get(0));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro, tente novamente!", Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }

    private void getComponentes() {

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        btnAnamnese = findViewById(R.id.btn_anamnese);
        progressBar = findViewById(R.id.progressBarAnamnese);
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

        String uri = "http://vitorsilva.xyz/napp/anamnese/inserirAnamnese.php";
        CadastrarAnamnese mytask = new CadastrarAnamnese();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setUrl(uri);
        requestHttp.setMetodo("GET");
        requestHttp.setParametro("fkAluno",  String.valueOf(anamnese.getFkAluno().getIdAluno()));
        requestHttp.setParametro("Questao1", anamnese.getQuestao1());
        requestHttp.setParametro("Questao2", anamnese.getQuestao2());
        requestHttp.setParametro("Questao3", anamnese.getQuestao3());
        requestHttp.setParametro("Questao4", anamnese.getQuestao4());
        requestHttp.setParametro("Questao5", anamnese.getQuestao5());
        requestHttp.setParametro("Questao6", anamnese.getQuestao6());
        requestHttp.setParametro("Questao7", anamnese.getQuestao7());
        requestHttp.setParametro("Questao8", anamnese.getQuestao8());
        requestHttp.setParametro("Questao9", anamnese.getQuestao9());

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
                Log.e("TESTE - ", conteudo);
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
