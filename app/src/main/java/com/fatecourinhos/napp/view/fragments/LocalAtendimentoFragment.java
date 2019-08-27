package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.LocalAtendimentoController;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroLocalAtendimento;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoFragment extends Fragment{

    List<LocalAtendimento> locaisAtendimento;
    LocalAtendimentoAdapter adapter;
    RecyclerView localAtendimentoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        localAtendimentoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_local_atendimento,container,false);
        localAtendimentoRecycler.setLayoutManager(layoutManager);

        return localAtendimentoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Locais de Atendimento");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LocalAtendimentoController localAtendimentoController = new LocalAtendimentoController();

        locaisAtendimento = localAtendimentoController.selecionarLocalAtendimento();

        adapter = new LocalAtendimentoAdapter(locaisAtendimento);

        localAtendimentoRecycler.setAdapter(adapter);

        adapter.setListener(new LocalAtendimentoAdapter.Listener() {
            @Override
            public void onClick(LocalAtendimento localAtendimento) {

                Bundle data = new Bundle();
                data.putInt("idLocalAtendimento", localAtendimento.getIdLocalAtendimento());
                data.putString("nomeLocal", localAtendimento.getNomeLocal());
                data.putString("nomeBloco", localAtendimento.getNomeBloco());

                CadastroLocalAtendimento cadastroLocalAtendimento = new CadastroLocalAtendimento();
                cadastroLocalAtendimento.setArguments(data);
                cadastroLocalAtendimento.show(getFragmentManager(), "LOCAL");

            }
        });
    }

}