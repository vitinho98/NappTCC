package com.fatecourinhos.napp.view.fragments.aluno;

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
import com.fatecourinhos.napp.view.adapter.aluno.AgendamentoAlunoAdapter;
import com.fatecourinhos.napp.view.cadastros.aluno.CadastroAgendamentoAluno;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AgendamentoAlunoFragment extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_agendamento_aluno,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_agendamento_aluno);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CadastroAgendamentoAluno.class));
            }
        });

        viewHolder.recyclerViewAgendamento = view.findViewById(R.id.recycler_view_agendamentos_aluno);
        viewHolder.recyclerViewAgendamento.setLayoutManager(new LinearLayoutManager(context));

        listener = new OnAgendamentoInteractionListener() {
            @Override
            public void onListClick(Agendamento agendamento) {

            }

            @Override
            public void onDeleteClick(Agendamento agendamento) {

            }
        };

        preferences = getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idAluno"))
            id = preferences.getInt("idAluno", 0);

        selecionarAgendaAluno(id);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarAgendaAluno(id);
    }

    private void selecionarAgendaAluno(int id) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/selecionarAgendamentoAluno.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarAgendaAluno mytask = new SelecionarAgendaAluno();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAluno", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class SelecionarAgendaAluno extends AsyncTask<RequestHttp, String, List<Agendamento>> {

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
        protected void onPostExecute(List<Agendamento> agendamentos) {
            super.onPostExecute(agendamentos);

            agendamentoAlunoAdapter = new AgendamentoAlunoAdapter(agendamentos, listener);
            viewHolder.recyclerViewAgendamento.setAdapter(agendamentoAlunoAdapter);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendamento;
    }

}