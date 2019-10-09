package com.fatecourinhos.napp.view.cadastros;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.HorarioProfissionalAdapter;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CadastroAgendamentoAluno extends AppCompatActivity {
    boolean sucesso;
    Aluno aluno;
    Profissional profissional;
    Agendamento agendamento;
    OnHorarioProfissionalnteractionListener listener;
    HorarioProfissionalAdapter adapter;
    private SharedPreferences preferences;
    ViewHolder viewHolder = new ViewHolder();

    String conteudo;
    SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM");
    SimpleDateFormat horaFormater = new SimpleDateFormat("HH:mm");

    List<Horario> agendaProfissional;
    List<Profissional> profissionais ;
    List<String> nomes = new ArrayList<>();

    Spinner spinner;
    ProgressBar progressBar;

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

                profissional = profissionais.get(spinner.getSelectedItemPosition());
                horario.setFkProfissional(profissional);

                if (preferences.contains("idAluno"))
                    aluno.setIdAluno(preferences.getInt("idAluno", 0));

                agendamento.setFkHorario(horario);
                agendamento.setFkAluno(aluno);

                new AlertDialog.Builder(CadastroAgendamentoAluno.this)
                        .setTitle("Confirmar agendamento")
                        .setMessage("Deseja confirmar o agendamento com " + agendamento.getFkHorario().getFkProfissional().getNomeProfissional() +
                                    " no dia " + dataFormater.format(horario.getData()) + " às " + horaFormater.format(horario.getData()) + "?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cadastrarAgendamento(agendamento);
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();

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

    private void cadastrarAgendamento(Agendamento agendamento) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/cadastrarAgendamento.php";
        CadastrarAgendamento mytask = new CadastrarAgendamento();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setUrl(uri);
        requestHttp.setMetodo("GET");
        requestHttp.setParametro("idHorario", String.valueOf(agendamento.getFkHorario().getIdHorarioProfissional()));
        requestHttp.setParametro("idAluno", String.valueOf(agendamento.getFkAluno().getIdAluno()));

        mytask.execute(requestHttp);

    }

    private class CadastrarAgendamento extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Agendamento cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao cadastrar o agendamento", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewHorarios;
    }

}
