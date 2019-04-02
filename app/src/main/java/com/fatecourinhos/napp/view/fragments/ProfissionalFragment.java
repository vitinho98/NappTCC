package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

    List<ProfissionalModel> profissionalList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        buscarDados("http://192.168.0.43/testetcc/APIConsultarDados.php");

        RecyclerView profissionalRecycler = (RecyclerView)inflater.inflate(R.layout.profissional_fragment,container,false);


        ProfissionalAdapter adapter = new ProfissionalAdapter(profissionalList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profissionalRecycler.setLayoutManager(layoutManager);
        profissionalRecycler.setAdapter(adapter);

        adapter.setListener(new ProfissionalAdapter.Listener() {
            @Override
            public void onClick(ProfissionalModel profissional) {
                Intent intent = new Intent(getActivity(), ProfissionalActivity.class);
                intent.putExtra("nomeProfissional", profissional.getNomeProfissional());
                getActivity().startActivity(intent);
            }
        });

        return profissionalRecycler;

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais do Núcleo");

    }

    private void buscarDados(String uri){
        MyTask mytask = new MyTask();
        mytask.execute(uri);
    }

    private class MyTask extends AsyncTask<String, String, List<ProfissionalModel>>{

        @Override
        protected List<ProfissionalModel> doInBackground(String... params) {

            String conteudo = HttpManager.getDados(params[0]);
            profissionalList = ProfissionalJSONParser.parseDados(conteudo);

            return profissionalList;
        }

        @Override
        protected void onPostExecute(List<ProfissionalModel> profissionalModels) {
            //atualizarView();
        }
    }


}