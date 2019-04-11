package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.HttpManager;
import com.fatecourinhos.napp.controller.ProfissionalJSONParser;
import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.view.ProfissionalActivity;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;

import com.fatecourinhos.napp.model.ProfissionalModel;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalFragment extends Fragment{

    List<ProfissionalModel> profissionais;
    ProfissionalAdapter adapter;
    RecyclerView profissionalRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profissionalRecycler = (RecyclerView)inflater.inflate(R.layout.profissional_fragment,container,false);

        profissionalRecycler.setLayoutManager(layoutManager);

        return profissionalRecycler;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais do Núcleo");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        profissionais = new ArrayList<>();

        buscarDados("http:vitorsilva.xyz/napp/APIConsultarDados.php");
    }

    private void buscarDados(String uri) {
        SelectProf mytask = new SelectProf();
        mytask.execute(uri);
    }

    private class SelectProf extends AsyncTask<String, String, List<ProfissionalModel>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ProfissionalModel> doInBackground(String... params) {
            final String conteudo = HttpManager.getDados(params[0]);
            profissionais = ProfissionalJSONParser.parseDados(conteudo);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getActivity(), conteudo, Toast.LENGTH_LONG).show();
                }
            });


            return profissionais;
        }

        @Override
        protected void onPostExecute(final List<ProfissionalModel> profissional) {
            super.onPostExecute(profissionais);
            adapter = new ProfissionalAdapter(profissionais);
            profissionalRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setListener(new ProfissionalAdapter.Listener() {
                @Override
                public void onClick(ProfissionalModel profissional) {
                    Intent intent = new Intent(getActivity(), ProfissionalActivity.class);
                    intent.putExtra("nomeProfissional", profissional.getNomeProfissional());
                    getActivity().startActivity(intent);
                }
            });
        }
    }
}