package com.fatecourinhos.napp.view.fragments.profissional;

import android.content.Context;
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
import com.fatecourinhos.napp.view.atendimento.ConfirmarAgendamentoProfissional;
import com.fatecourinhos.napp.view.adapter.profissional.AgendamentoProfissionaAdapter;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;

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
    private AgendamentoProfissionaAdapter agendamentoProfissionaAdapter;

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

                ConfirmarAgendamentoProfissional dialog = new ConfirmarAgendamentoProfissional();
                Bundle bundle = new Bundle();
                bundle.putInt("idAgendamento", agendamento.getIdAgendamento());
                bundle.putString("statusAgendamento", agendamento.getStatus());
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "CONFIRMAR AGENDAMENTO");

            }

            @Override
            public void onDeleteClick(Agendamento agendamento) {

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
        protected void onPostExecute(List<Agendamento> agendamentos) {
            super.onPostExecute(agendamentos);

            agendamentoProfissionaAdapter = new AgendamentoProfissionaAdapter(agendamentos, listener);
            viewHolder.recyclerViewAgendamento.setAdapter(agendamentoProfissionaAdapter);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendamento;
    }

}
