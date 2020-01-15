package com.fatecourinhos.napp.view.consultaraluno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import java.util.List;

public class DadosPessoaisAluno extends AppCompatActivity {

    private EditText nome, celular, email, semestre, curso, dataNascimento, ra, anoEntrada, sexo, cidade, estadoCivil;
    private ProgressBar progressBar;

    private String conteudo;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais_aluno);
        getComponentes();

        if (getIntent().getExtras() != null)
            selecionarAluno(getIntent().getExtras().getInt("idAluno"));

    }

    private void getComponentes() {

        nome = findViewById(R.id.editTextNomeAluno);
        celular = findViewById(R.id.editTextCelularAluno);
        email = findViewById(R.id.editTextEmailAluno);
        semestre = findViewById(R.id.editTextSemestre);
        curso = findViewById(R.id.editTextCurso);
        dataNascimento = findViewById(R.id.editTextDataNascimento);
        ra = findViewById(R.id.editTextRaAluno);
        anoEntrada = findViewById(R.id.editTextAnoEntrada);
        sexo = findViewById(R.id.editTextSexo);
        cidade = findViewById(R.id.editTextCidade);
        estadoCivil = findViewById(R.id.editTextEstadoCivil);
        progressBar = findViewById(R.id.progressBarDadosAluno);

        nome.setEnabled(false);
        celular.setEnabled(false);
        email.setEnabled(false);
        semestre.setEnabled(false);
        curso.setEnabled(false);
        dataNascimento.setEnabled(false);
        ra.setEnabled(false);
        anoEntrada.setEnabled(false);
        sexo.setEnabled(false);
        cidade.setEnabled(false);
        estadoCivil.setEnabled(false);

    }

    private void setarComponentes(Aluno aluno) {

        nome.setText(aluno.getNomeAluno());
        celular.setText("43984672245");
        email.setText(aluno.getEmailAluno());
        semestre.setText(Integer.toString(aluno.getSemestre()));
        curso.setText(aluno.getCurso().contains("Desenvolvimento") ? "ADS" : aluno.getCurso());
        dataNascimento.setText("08/11/1998");
        ra.setText(aluno.getRa());
        anoEntrada.setText(aluno.getAnoEntrada());
        sexo.setText(aluno.getSexo());
        cidade.setText(aluno.getCidadeAluno());
        estadoCivil.setText(aluno.getEstadoCivil());

        progressBar.setVisibility(View.INVISIBLE);

    }

    private void selecionarAluno(int id) {

        String uri = "http://vitorsilva.xyz/napp/aluno/selecionarAlunoCompleto.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarAluno task = new SelecionarAluno();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAluno", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class SelecionarAluno extends AsyncTask<RequestHttp, String, List<Aluno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Aluno> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            alunos = AlunoJSONParser.parseDados(conteudo);
            return alunos;
        }

        @Override
        protected void onPostExecute(List<Aluno> alunos) {
            super.onPostExecute(alunos);

            try {
                setarComponentes(alunos.get(0));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro, tente novamente!", Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }

}
