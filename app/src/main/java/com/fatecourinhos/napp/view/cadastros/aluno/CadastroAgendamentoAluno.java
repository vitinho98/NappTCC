package com.fatecourinhos.napp.view.cadastros.aluno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.HorarioProfissionalJSONParser;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.HorarioProfissionalAdapter;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.util.ArrayList;
import java.util.List;

public class CadastroAgendamentoAluno extends AppCompatActivity {

    private String conteudo;

    private Aluno aluno;
    private Profissional profissional;
    private Agendamento agendamento;

    private OnHorarioProfissionalnteractionListener listener;
    private HorarioProfissionalAdapter adapter;
    private SharedPreferences preferences;
    private ViewHolder viewHolder = new ViewHolder();

    private List<Horario> horarios;
    private List<Profissional> profissionais;
    private List<String> nomes = new ArrayList<>();

    private Spinner spinner;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_aluno);

        progressBar = findViewById(R.id.pgBarAgendaAluno);
        spinner = findViewById(R.id.spinner_profissional);
        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecionarDatas(profissionais.get(spinner.getSelectedItemPosition()).getIdProfissional());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        selecionarProfissionais();

        listener = new OnHorarioProfissionalnteractionListener() {
            @Override
            public void onListClick(Horario horario){

                agendamento = new Agendamento();
                aluno = new Aluno();

                if (preferences.contains("idAluno"))
                    aluno.setIdAluno(preferences.getInt("idAluno", 0));

                profissional = profissionais.get(spinner.getSelectedItemPosition());
                horario.setFkProfissional(profissional);

                agendamento.setFkHorario(horario);
                agendamento.setFkAluno(aluno);

                Intent intent = new Intent(CadastroAgendamentoAluno.this, ConfirmarCadastroAgendamento.class);
                intent.putExtra("idAluno", aluno.getIdAluno());
                intent.putExtra("idHorario", agendamento.getFkHorario().getIdHorarioProfissional());
                intent.putExtra("dataHora", agendamento.getFkHorario().getData().getTime());
                intent.putExtra("nomeProfissional", agendamento.getFkHorario().getFkProfissional().getNomeProfissional());

                startActivity(intent);
                finish();

            }

            @Override
            public void onDeleteClick(Horario agendaProfissional) {

            }
        };

        viewHolder.recyclerViewHorarios = findViewById(R.id.recycler_view_lista_datas_ag_aluno);
        viewHolder.recyclerViewHorarios.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void selecionarDatas(int id) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/selecionarDatas.php";
        SelecionarDatas mytask = new SelecionarDatas();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setUrl(uri);
        requestHttp.setMetodo("GET");
        requestHttp.setParametro("id", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class SelecionarDatas extends AsyncTask<RequestHttp, String, List<Horario>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<Horario> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            horarios = HorarioProfissionalJSONParser.parseDados(conteudo);
            return horarios;
        }

        @Override
        protected void onPostExecute(final List<Horario> agendasProfissional) {
            super.onPostExecute(agendasProfissional);

            adapter = new HorarioProfissionalAdapter(horarios, listener);
            viewHolder.recyclerViewHorarios.setAdapter(adapter);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

    }

    private void selecionarProfissionais() {

        String uri = "http://vitorsilva.xyz/napp/profissional/selecionarProfissionais.php";
        SelecionarProfissionais mytask = new SelecionarProfissionais();

        mytask.execute(uri);

    }

    private class SelecionarProfissionais extends AsyncTask<String, String, List<Profissional>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected List<Profissional> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            profissionais = ProfissionalJSONParser.parseDados(conteudo);
            return profissionais;
        }

        @Override
        protected void onPostExecute(List<Profissional> profissionais) {
            super.onPostExecute(profissionais);

            for (Profissional profissional : profissionais)
                nomes.add(profissional.getNomeProfissional());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nomes);
            spinner.setAdapter(adapter);

            progressBar.setVisibility(ProgressBar.INVISIBLE);

        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewHorarios;
    }

}
