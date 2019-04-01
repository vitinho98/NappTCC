package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.ProfissionalExternoActivity;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        RecyclerView profissionalExternoRecycler = (RecyclerView)inflater.inflate(R.layout.profissional_externo_fragment,container,false);

        final List<ProfissionalExternoModel> profissionaisExterno = new ArrayList<ProfissionalExternoModel>();


        ProfissionalExternoAdapter adapter = new ProfissionalExternoAdapter(profissionaisExterno);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profissionalExternoRecycler.setLayoutManager(layoutManager);
        profissionalExternoRecycler.setAdapter(adapter);

        adapter.setListener(new ProfissionalExternoAdapter.Listener() {
            @Override
            public void onClick(ProfissionalExternoModel profissionalExterno) {
                Intent intent = new Intent(getActivity(), ProfissionalExternoActivity.class);
                intent.putExtra("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
                getActivity().startActivity(intent);
            }

        });


        return profissionalExternoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais Externos");

    }

}