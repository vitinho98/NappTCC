package com.fatecourinhos.napp.view.cadastros;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.HorarioProfissionalJSONParser;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.HorarioProfissionalAdapter;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.util.ArrayList;
import java.util.List;

public class CadastroAgendamento extends AppCompatActivity {

    OnHorarioProfissionalnteractionListener listener;
    HorarioProfissionalAdapter adapter;
    ViewHolder viewHolder = new ViewHolder();
    List<Horario> agendaProfissional;
    Spinner spinner;
    String conteudo;
    List<Profissional> profissionais = new ArrayList<>();
    List<String> nomes = new ArrayList<>();
    ProgressBar pgBarAgendaAluno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_aluno);
        pgBarAgendaAluno = findViewById(R.id.pgBarAgendaAluno);

        listener = new OnHorarioProfissionalnteractionListener() {
            @Override
            public void onListClick(Horario agendaProfissional){
                pgBarAgendaAluno.setVisibility(View.VISIBLE);
                Profissional profissional = new Profissional();
                //PEGAR O ID E CADASTRAR O HORARIO NO BAAANCO
                Toast.makeText(CadastroAgendamento.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDeleteClick(Horario agendaProfissional) {

            }
        };

        viewHolder.recyclerViewHorarios = findViewById(R.id.recycler_view_lista_datas_ag_aluno);
        viewHolder.recyclerViewHorarios.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        spinner = findViewById(R.id.spinner_profissional);
        selecionarProfissionais();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecionarDatas(profissionais.get(spinner.getSelectedItemPosition()).getIdProfissional());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<Horario> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            agendaProfissional = HorarioProfissionalJSONParser.parseDados(conteudo);
            return agendaProfissional;
        }

        @Override
        protected void onPostExecute(final List<Horario> agendasProfissional) {
            super.onPostExecute(agendasProfissional);

            adapter = new HorarioProfissionalAdapter(agendaProfissional, listener);
            viewHolder.recyclerViewHorarios.setAdapter(adapter);
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

        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewHorarios;
    }

}
