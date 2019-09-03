package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ResponsavelController;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.view.cadastros.CadastroResponsavel;
import com.fatecourinhos.napp.view.adapter.ResponsavelAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelFragment extends Fragment{

    List<Responsavel> responsaveis;
    ResponsavelAdapter adapter;
    RecyclerView responsavelRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        responsavelRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_responsavel,container,false);
        responsavelRecycler.setLayoutManager(layoutManager);

        return responsavelRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Responsaveis");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ResponsavelController responsavelController = new ResponsavelController();

        responsaveis = responsavelController.selecionarResponsaveis();

        adapter = new ResponsavelAdapter(responsaveis);

        responsavelRecycler.setAdapter(adapter);

        adapter.setListener(new ResponsavelAdapter.Listener() {
            @Override
            public void onClick(Responsavel responsavel) {
                Intent intent = new Intent(getActivity(), CadastroResponsavel.class);

                intent.putExtra("idResponsavel", responsavel.getIdResponsavel());
                intent.putExtra("nomeResponsavel", responsavel.getNomeResponsavel());
                intent.putExtra("emailResponsavel", responsavel.getEmailResponsavel());
                intent.putExtra("celularResponsavel", responsavel.getCelularResponsavel());
                intent.putExtra("telefoneResponsavel", responsavel.getTelefoneResponsavel());

                getActivity().startActivity(intent);
            }
        });
    }
}