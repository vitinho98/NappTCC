package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;

import com.fatecourinhos.napp.model.ProfissionalModel;

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

        profissionalRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_profissional,container,false);
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

        ProfissionalController profissionalController = new ProfissionalController();

        profissionais = profissionalController.selecionarProfisisonais();

        adapter = new ProfissionalAdapter(profissionais);

        profissionalRecycler.setAdapter(adapter);

        adapter.setListener(new ProfissionalAdapter.Listener() {
            @Override
            public void onClick(ProfissionalModel profissional) {
                Intent intent = new Intent(getActivity(), CadastroProfissional.class);

                intent.putExtra("idProfissional", profissional.getIdProfissional());
                intent.putExtra("foto", profissional.getFoto());
                intent.putExtra("nomeProfissional", profissional.getNomeProfissional());
                intent.putExtra("emailProfissional", profissional.getEmailProfissional());
                intent.putExtra("celularProfissional", profissional.getCelularProfissional());

                intent.putExtra("idUsuario", profissional.getFkUsuario().getIdUsuario());
                intent.putExtra("loginProfissional", profissional.getFkUsuario().getLogin());
                intent.putExtra("senhaProfissional", profissional.getFkUsuario().getSenha());
                intent.putExtra("tipoProfissional", profissional.getFkUsuario().getTipoUsuario());
                intent.putExtra("statusProfissional", profissional.getFkUsuario().getStatus());

                intent.putExtra("campoAtuacao",profissional.getCampoAtuacao().getNomeCampoAtuacao());
                intent.putExtra("idCampoAtuacao", profissional.getCampoAtuacao().getIdCampoAtuacao());

                intent.putExtra("operacao", "alterar");

                getActivity().startActivity(intent);
            }
        });
    }

}