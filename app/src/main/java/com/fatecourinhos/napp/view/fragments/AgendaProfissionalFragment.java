package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.AgendaProfissionalController;
import com.fatecourinhos.napp.json.AgendaProfissionalJSONParser;
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.AgendaProfissionalAdapter;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroHorario;
import com.fatecourinhos.napp.view.listener.OnAgendaProfissionalnteractionListener;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaProfissionalFragment extends Fragment{

    private SharedPreferences preferences;

    private List<AgendaProfissional> agendaProfissional;
    private AgendaProfissionalAdapter agendaProfissionalAdapter;
    private OnAgendaProfissionalnteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        getActivity().setTitle("Horários atendimento");
        view = inflater.inflate(R.layout.fragment_horario_atendimento,container,false);
        context = view.getContext();
        viewHolder.recyclerViewAgendaProfissional = view.findViewById(R.id.recycler_view_horario_atendimento);
        viewHolder.recyclerViewAgendaProfissional.setLayoutManager(new LinearLayoutManager(context));
        int id = 0;
        preferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idProfissional"))
            id = preferences.getInt("idProfissional", 0);
        System.out.println(id);

        selecionarAgendaProfissional(id);

        return view;
    }

    private void selecionarAgendaProfissional(int id) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/selecionarAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idProfissional", String.valueOf(id));

        SelecionarAgendaProfissional mytask = new SelecionarAgendaProfissional();
        mytask.execute(requestHttp);

    }

    private class SelecionarAgendaProfissional extends AsyncTask<RequestHttp, String, List<AgendaProfissional>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<AgendaProfissional> doInBackground(RequestHttp... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            Log.e("cont", conteudo);
            agendaProfissional = AgendaProfissionalJSONParser.parseDados(conteudo);

            return agendaProfissional;
        }

        @Override
        protected void onPostExecute(final List<AgendaProfissional> agendasProfissional) {
            super.onPostExecute(agendasProfissional);
            agendaProfissionalAdapter = new AgendaProfissionalAdapter(agendaProfissional, listener);
            viewHolder.recyclerViewAgendaProfissional.setAdapter(agendaProfissionalAdapter);

        }
    }

    private static class ViewHolder {
        RecyclerView recyclerViewAgendaProfissional;
    }

}