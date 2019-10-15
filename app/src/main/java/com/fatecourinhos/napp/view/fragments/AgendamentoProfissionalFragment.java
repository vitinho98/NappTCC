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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AgendamentoJSONParser;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.AgendamentoAlunoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroAgendamentoAluno;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AgendamentoProfissionalFragment extends Fragment {

    private SharedPreferences preferences;
    private String conteudo;
    private int id = 0;
    private OnAgendamentoInteractionListener listener;
    private ViewHolder viewHolder;
    private View view;
    private Context context;
    private List<Agendamento> agendamentos;
    private AgendamentoAlunoAdapter agendamentoAlunoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Agendamentos");
        view = inflater.inflate(R.layout.fragment_agendamento_profissional,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        viewHolder.recyclerViewAgendamento = view.findViewById(R.id.recycler_view_agendamentos_prof);
        viewHolder.recyclerViewAgendamento.setLayoutManager(new LinearLayoutManager(context));

        listener = new OnAgendamentoInteractionListener() {
            @Override
            public void onListClick(Agendamento agendamento) {

            }

            @Override
            public void onDeleteClick(Agendamento agendamento) {

                new AlertDialog.Builder(context)
                        .setTitle("Cancelar agendamento")
                        .setMessage("Deseja realmente cancelar o agendamento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //excluirLocalAtendimento(localAtendimento.getIdLocalAtendimento());
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
        selecionarAgendaProfissional(id);
    }

    private void selecionarAgendaProfissional(int id) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/selecionarAgendamentoProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarAgendaProfissional mytask = new SelecionarAgendaProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idProfissional", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class SelecionarAgendaProfissional extends AsyncTask<RequestHttp, String, List<Agendamento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<Agendamento> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            agendamentos = AgendamentoJSONParser.parseDados(conteudo);
            return agendamentos;
        }

        @Override
        protected void onPostExecute(final List<Agendamento> agendamentos) {
            super.onPostExecute(agendamentos);

            agendamentoAlunoAdapter = new AgendamentoAlunoAdapter(agendamentos, listener);
            viewHolder.recyclerViewAgendamento.setAdapter(agendamentoAlunoAdapter);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendamento;
    }

}
