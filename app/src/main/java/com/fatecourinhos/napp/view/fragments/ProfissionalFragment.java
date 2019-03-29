package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        RecyclerView profissionalRecycler = (RecyclerView)inflater.inflate(R.layout.profissional_fragment,container,false);

        List<ProfissionalModel> profissionais = new ArrayList<ProfissionalModel>();

        ProfissionalModel p1 = new ProfissionalModel("Rose", "ativo");
        ProfissionalModel p2 = new ProfissionalModel("Eunice", "inativo");

        profissionais.add(p1);
        profissionais.add(p2);

        ProfissionalAdapter adapter = new ProfissionalAdapter(profissionais);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profissionalRecycler.setLayoutManager(layoutManager);
        profissionalRecycler.setAdapter(adapter);

        return profissionalRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais do Núcleo");

    }

}