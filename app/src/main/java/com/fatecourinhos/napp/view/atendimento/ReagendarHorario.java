package com.fatecourinhos.napp.view.atendimento;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.HorarioProfissionalJSONParser;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.HorarioProfissionalAdapter;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.util.List;

public class ReagendarHorario extends AppCompatActivity {

    private SharedPreferences preferences;
    private boolean sucesso;
    private String conteudo;
    private int idProfissional;
    private int idAgendamento;
    private List<Horario> horarios;

    private ProgressBar progressBar;
    private HorarioProfissionalAdapter horarioProfissionalAdapter;
    private OnHorarioProfissionalnteractionListener listener;
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagendar);

        progressBar = findViewById(R.id.progressBarReagendamento);
        viewHolder = new ViewHolder();
        viewHolder.recyclerViewAgendaProfissional = findViewById(R.id.recycler_view_reagendar);
        viewHolder.recyclerViewAgendaProfissional.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listener = new OnHorarioProfissionalnteractionListener() {
            @Override
            public void onListClick(final Horario horario) {
                new AlertDialog.Builder(ReagendarHorario.this)
                        .setNegativeButton("Não", null)
                        .setTitle("Reagendar Atendimento")
                        .setMessage("Deseja confirmar o reagendamento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                reagendar(horario.getIdHorarioProfissional());
                            }
                        })
                        .show();
            }

            @Override
            public void onDeleteClick(Horario horario) {

            }
        };

        idAgendamento = getIntent().getExtras().getInt("idAgendamento");
        preferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idProfissional"))
            idProfissional = preferences.getInt("idProfissional", 0);
        selecionarDatas(idProfissional);

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendaProfissional;
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
            progressBar.setVisibility(View.VISIBLE);
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
        protected void onPostExecute(List<Horario> horarios) {
            super.onPostExecute(horarios);

            horarioProfissionalAdapter = new HorarioProfissionalAdapter(horarios, listener);
            viewHolder.recyclerViewAgendaProfissional.setAdapter(horarioProfissionalAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }


    private void reagendar(int idHorario) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/reagendar.php";
        RequestHttp requestHttp = new RequestHttp();
        Reagendar mytask = new Reagendar();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(idAgendamento));
        requestHttp.setParametro("idHorario", String.valueOf(idHorario));
        System.out.println(idAgendamento + "    " + idHorario);

        mytask.execute(requestHttp);

    }

    private class Reagendar extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao alterar!", Toast.LENGTH_SHORT).show();
        }

    }

}
