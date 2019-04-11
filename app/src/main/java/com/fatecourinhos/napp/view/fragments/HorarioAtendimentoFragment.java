package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.view.adapter.HorarioAtendimentoAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorarioAtendimentoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        RecyclerView horarioAtendimentoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_horario_atendimento,container,false);

        final List<AgendaProfissionalModel> agendaProfissionais = new ArrayList<AgendaProfissionalModel>();

        HorarioAtendimentoAdapter adapter = new HorarioAtendimentoAdapter(agendaProfissionais);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        horarioAtendimentoRecycler.setLayoutManager(layoutManager);
        horarioAtendimentoRecycler.setAdapter(adapter);

        adapter.setListener(new HorarioAtendimentoAdapter.Listener() {

            @Override
            public void onClick(AgendaProfissionalModel agendaProfissional) {

            }
        });


        return horarioAtendimentoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Horários atendimento");

    }

}