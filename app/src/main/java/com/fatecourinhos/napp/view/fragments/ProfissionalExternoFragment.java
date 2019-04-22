package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.controller.ProfissionalExternoController;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoFragment extends Fragment{

    List<ProfissionalExternoModel> profissionaisExterno;
    ProfissionalExternoAdapter adapter;
    RecyclerView profissionalExternoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profissionalExternoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_profissional_externo,container,false);
        profissionalExternoRecycler.setLayoutManager(layoutManager);

        return profissionalExternoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais Externos");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        profissionaisExterno = ProfissionalExternoController.buscarProfissionaisExternos();

        adapter = new ProfissionalExternoAdapter(profissionaisExterno);

        profissionalExternoRecycler.setAdapter(adapter);

        adapter.setListener(new ProfissionalExternoAdapter.Listener() {
            @Override
            public void onClick(ProfissionalExternoModel profissionalExterno) {
                Intent intent = new Intent(getActivity(), CadastroProfissionalExterno.class);

                getActivity().startActivity(intent);
            }
        });
    }

}