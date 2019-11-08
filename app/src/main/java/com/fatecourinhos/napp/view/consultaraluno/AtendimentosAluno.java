package com.fatecourinhos.napp.view.consultaraluno;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AtendimentoJSONParser;
import com.fatecourinhos.napp.model.Atendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.AtendimentosAlunoAdapter;
import com.fatecourinhos.napp.view.listener.OnAtendimentosAlunoInteractionListener;

import java.util.List;

public class AtendimentosAluno extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;

    private String conteudo;
    private List<Atendimento> atendimentos;

    private OnAtendimentosAlunoInteractionListener listener;
    private AtendimentosAlunoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimentos_aluno);

        textView = findViewById(R.id.textViewNomeAluno);
        progressBar = findViewById(R.id.progressBarAtendimentosAluno);
        recyclerView = findViewById(R.id.recycler_view_atendimentos_aluno);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listener = new OnAtendimentosAlunoInteractionListener() {
            @Override
            public void onListClick(Atendimento atendimento) {
                Intent intent = new Intent(AtendimentosAluno.this, VisualizarAtendimento.class);
                intent.putExtra("idAtendimento", atendimento.getIdAtendimento());
                startActivity(intent);
            }
        };

        if (getIntent().getExtras() != null) {
            selecionarAtendimentos(getIntent().getExtras().getInt("idAluno"));
        }

    }

    private void selecionarAtendimentos(int id) {

        String uri = "http://vitorsilva.xyz/napp/atendimento/selecionarAtendimentos.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarAtendimentos task = new SelecionarAtendimentos();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAluno", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class SelecionarAtendimentos extends AsyncTask<RequestHttp, String, List<Atendimento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<Atendimento> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            atendimentos = AtendimentoJSONParser.parseDados(conteudo);
            return atendimentos;
        }

        @Override
        protected void onPostExecute(List<Atendimento> atendimentos) {
            super.onPostExecute(atendimentos);

            try {
                textView.setText(atendimentos.get(0).getFkAgendamento().getFkAluno().getNomeAluno());
            } catch (Exception e) {

            }

            adapter = new AtendimentosAlunoAdapter(atendimentos, listener);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

}
