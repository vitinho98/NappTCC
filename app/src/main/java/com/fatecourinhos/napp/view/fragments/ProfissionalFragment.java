package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;


import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.ProfissionalJSONParser;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;

import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.view.listener.OnProfissionalInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Profissional> profissionais;

    //componentes da tela
    private SearchView searchView;
    private ProgressBar progressBar;
    private OnProfissionalInteractionListener listener;
    private ProfissionalAdapter profissionalAdapter;
    private ViewHolder viewHolder;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Profissionais do Núcleo");
        view = inflater.inflate(R.layout.fragment_profissional,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        progressBar = view.findViewById(R.id.progressBarProfissional);
        searchView = view.findViewById(R.id.searchViewProfissional);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                profissionalAdapter.getFilter().filter(s);
                return false;
            }
        });

        viewHolder.recyclerViewProfissionais = view.findViewById(R.id.recycler_view_profissional);
        viewHolder.recyclerViewProfissionais.setLayoutManager(new LinearLayoutManager(context));

        //adiciona eventos ao itens da lista
        listener = new OnProfissionalInteractionListener() {
            @Override
            public void onListClick(Profissional profissional) {

                Intent intent = new Intent(getActivity(), CadastroProfissional.class);

                intent.putExtra("idProfissional", profissional.getIdProfissional());
                intent.putExtra("nomeProfissional", profissional.getNomeProfissional());
                intent.putExtra("emailProfissional", profissional.getEmailProfissional());
                intent.putExtra("celularProfissional", profissional.getCelularProfissional());

                intent.putExtra("idUsuario", profissional.getFkUsuario().getIdUsuario());
                intent.putExtra("loginProfissional", profissional.getFkUsuario().getLogin());
                intent.putExtra("senhaProfissional", profissional.getFkUsuario().getSenha());
                intent.putExtra("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
                intent.putExtra("statusProfissional", profissional.getFkUsuario().getStatus());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(Profissional profissional) {

            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarProfissionais();
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
            progressBar.setVisibility(View.VISIBLE);
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

            profissionalAdapter = new ProfissionalAdapter(profissionais, listener);
            viewHolder.recyclerViewProfissionais.setAdapter(profissionalAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewProfissionais;
    }

}