package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.LocalAtendimentoJSONParser;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroLocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoFragment extends Fragment{

    private String conteudo;
    private List<LocalAtendimento> locaisAtendimento;
    private OnLocalAtendimentoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private LocalAtendimentoAdapter localAtendimentoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Locais de Atendimento");
        view = inflater.inflate(R.layout.fragment_local_atendimento,container,false);
        context = view.getContext();

        viewHolder.recyclerViewLocaisAtendimento = view.findViewById(R.id.recycler_view_local_atendimento);
        viewHolder.recyclerViewLocaisAtendimento.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnLocalAtendimentoInteractionListener() {
            @Override
            public void onListClick(LocalAtendimento localAtendimento) {

                Bundle data = new Bundle();
                data.putInt("idLocalAtendimento", localAtendimento.getIdLocalAtendimento());
                data.putString("nomeLocal", localAtendimento.getNomeLocal());
                data.putString("nomeBloco", localAtendimento.getNomeBloco());

                CadastroLocalAtendimento cadastroLocalAtendimento = new CadastroLocalAtendimento();
                cadastroLocalAtendimento.setArguments(data);
                cadastroLocalAtendimento.show(getFragmentManager(), "LOCAL");

            }

            @Override
            public void onDeleteClick(LocalAtendimento localAtendimento) {

            }
        };

        selecionarLocalAtendimento();
        return view;

    }

    public void selecionarLocalAtendimento() {
        String uri = "http://vitorsilva.xyz/napp/localAtendimento/selecionarLocaisAtendimento.php";
        SelecionarLocaisAtendimento mytask = new SelecionarLocaisAtendimento();
        mytask.execute(uri);
    }

    private class SelecionarLocaisAtendimento extends AsyncTask<String, String, List<LocalAtendimento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LocalAtendimento> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            locaisAtendimento = LocalAtendimentoJSONParser.parseDados(conteudo);
            return locaisAtendimento;
        }

        @Override
        protected void onPostExecute(final List<LocalAtendimento> locaisAtendimento) {
            super.onPostExecute(locaisAtendimento);

            localAtendimentoAdapter = new LocalAtendimentoAdapter(locaisAtendimento, listener);
            viewHolder.recyclerViewLocaisAtendimento.setAdapter(localAtendimentoAdapter);
        }
    }

    private static class ViewHolder {
        RecyclerView recyclerViewLocaisAtendimento;
    }

}