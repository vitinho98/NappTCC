package com.fatecourinhos.napp.view.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.HorarioProfissionalJSONParser;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.HorarioProfissionalAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroHorario;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorarioProfissionalFragment extends Fragment {

    //variaveis globais
    private SharedPreferences preferences;
    private String conteudo;
    private int id;
    private List<Horario> horarios;

    //componentes da tela
    private ProgressBar progressBar;
    private HorarioProfissionalAdapter horarioProfissionalAdapter;
    private OnHorarioProfissionalnteractionListener listener;
    private ViewHolder viewHolder;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Horários atendimento");
        view = inflater.inflate(R.layout.fragment_horario_atendimento,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        progressBar = view.findViewById(R.id.progressBarHorario);

        viewHolder.recyclerViewAgendaProfissional = view.findViewById(R.id.recycler_view_horario_atendimento);
        viewHolder.recyclerViewAgendaProfissional.setLayoutManager(new LinearLayoutManager(context));

        //adiciona eventos aos itens da lista
        listener = new OnHorarioProfissionalnteractionListener() {
            @Override
            public void onListClick(Horario horario) {

                Intent intent = new Intent(getActivity(), CadastroHorario.class);
                intent.putExtra("idHorario", horario.getIdAgendaProfissional());
                intent.putExtra("dataHora", horario.getData());

                startActivity(intent);
            }

            @Override
            public void onDeleteClick(final Horario horario) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover horário")
                        .setMessage("Deseja remover o horário de atendimento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirHorarioProfissional(horario.getIdAgendaProfissional());
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
            }
        };

        preferences = getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idProfissional"))
            id = preferences.getInt("idProfissional", 0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarHorarioProfissional(id);
    }

    private void selecionarHorarioProfissional(int id) {

        String uri = "http://vitorsilva.xyz/napp/horarioProfissional/selecionarHorarioProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarHorarioProfissional mytask = new SelecionarHorarioProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idProfissional", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class SelecionarHorarioProfissional extends AsyncTask<RequestHttp, String, List<Horario>> {

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
        protected void onPostExecute(List<Horario> agendasProfissional) {
            super.onPostExecute(agendasProfissional);

            horarioProfissionalAdapter = new HorarioProfissionalAdapter(horarios, listener);
            viewHolder.recyclerViewAgendaProfissional.setAdapter(horarioProfissionalAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    private void excluirHorarioProfissional(int id) {

        String uri = "http://vitorsilva.xyz/napp/horarioProfissional/excluirHorarioProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        ExcluirHorarioProfissional mytask = new ExcluirHorarioProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idHorario", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class ExcluirHorarioProfissional extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (conteudo.contains("Sucesso")) {
                Toast.makeText(view.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                selecionarHorarioProfissional(id);
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendaProfissional;
    }

}