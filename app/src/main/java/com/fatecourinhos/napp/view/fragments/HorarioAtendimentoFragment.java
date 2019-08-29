package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.AgendaProfissionalController;
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.view.adapter.HorarioAtendimentoAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorarioAtendimentoFragment extends Fragment{

    List<AgendaProfissional> agendaProfissional;
    HorarioAtendimentoAdapter adapter;
    RecyclerView horarioAtendimentoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        horarioAtendimentoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_horario_atendimento,container,false);
        horarioAtendimentoRecycler.setLayoutManager(layoutManager);

        return horarioAtendimentoRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Horários atendimento");

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AgendaProfissionalController agendaProfissionalController = new AgendaProfissionalController();

        agendaProfissional = agendaProfissionalController.selecionarAgendaProfissional();

        adapter = new HorarioAtendimentoAdapter(agendaProfissional);

        horarioAtendimentoRecycler.setAdapter(adapter);

        adapter.setListener(new HorarioAtendimentoAdapter.Listener() {
            @Override
            public void onClick(AgendaProfissional agendaProfissional) {

                Bundle data = new Bundle();
                data.putInt("idAgendaProfissional", agendaProfissional.getIdAgendaProfissional());
                data.putString("hora", agendaProfissional.getHora());
                data.putString("minutos", agendaProfissional.getMinutos());
                data.putString("diaDaSemana", agendaProfissional.getDiaDaSemana());
                data.putInt("idProfissional", agendaProfissional.getFkProfissional().getIdProfissional());

                CadastroHorarioooo cadastroHorarioooo = new CadastroHorarioooo();
                cadastroHorarioooo.setArguments(data);
                cadastroHorarioooo.show(getFragmentManager(), "HORARIO");

            }
        });
    }

}