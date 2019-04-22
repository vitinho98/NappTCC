package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroAreaAtuacao;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AreaAtuacaoFragment extends Fragment{

    List<CampoAtuacaoModel> camposAtuacao;
    CampoAtuacaoAdapter adapter;
    RecyclerView campoAtuacaoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        campoAtuacaoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        campoAtuacaoRecycler.setLayoutManager(layoutManager);

        return campoAtuacaoRecycler;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Campos de Atuação");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        camposAtuacao = CampoAtuacaoController.buscarCamposAtuacao();

        adapter = new CampoAtuacaoAdapter(camposAtuacao);

        campoAtuacaoRecycler.setAdapter(adapter);

        adapter.setListener(new CampoAtuacaoAdapter.Listener() {
            @Override
            public void onClick(CampoAtuacaoModel campoAtuacao) {
                Intent intent = new Intent(getActivity(), CadastroAreaAtuacao.class);

                getActivity().startActivity(intent);
            }
        });
    }

}