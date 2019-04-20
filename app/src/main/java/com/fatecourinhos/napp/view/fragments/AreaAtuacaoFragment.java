package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AreaAtuacaoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        RecyclerView localAtendimentoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_local_atendimento,container,false);

        final List<LocalAtendimentoModel> locaisAtendimento = new ArrayList<LocalAtendimentoModel>();


        LocalAtendimentoAdapter adapter = new LocalAtendimentoAdapter(locaisAtendimento);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        localAtendimentoRecycler.setLayoutManager(layoutManager);
        localAtendimentoRecycler.setAdapter(adapter);

        adapter.setListener(new LocalAtendimentoAdapter.Listener() {

            @Override
            public void onClick(LocalAtendimentoModel localAtendimento) {

            }
        });


        return localAtendimentoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Locais de Atendimento");

    }

}