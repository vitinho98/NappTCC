package com.fatecourinhos.napp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

        View rootView = inflater.inflate(R.layout.profissional_fragment,container,false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_profissional);

        List<ProfissionalModel> profissionais = new ArrayList<>();
        ProfissionalModel p1 = new ProfissionalModel("teste1", "ativo");
        ProfissionalModel p2 = new ProfissionalModel("teste2", "inativo");

        profissionais.add(p1);
        profissionais.add(p2);

        recyclerView.setAdapter(new ProfissionalAdapter(profissionais, getContext()));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Menu1");

    }

}