package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.AlunoController;
import com.fatecourinhos.napp.model.AgendamentoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.MensagemModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.adapter.AgendamentoAlunoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroAgendamento;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoAlunoFragment extends Fragment{

    List<AgendamentoModel> agendamento;
    AgendamentoAlunoAdapter adapter;
    RecyclerView agendamentoAlunoRecycler;
    //SharedPreferences preferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View rootView = inflater.inflate(R.layout.fragment_agendamento_aluno,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        agendamentoAlunoRecycler = rootView.findViewById(R.id.listaagendamentoid);
        agendamentoAlunoRecycler.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Agendamento");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AlunoController alunoController = new AlunoController();

        agendamento = new ArrayList<>();//alunoController.selecionarAgendamento(preferences.getInt("idUsuario", 0));

        AgendamentoModel agendamentoModel = new AgendamentoModel();

        agendamentoModel.setDataAgendamento("12/2");
        agendamentoModel.setHoraAgendamento("2100");
        ProfissionalModel profissionalModel = new ProfissionalModel();
        profissionalModel.setNomeProfissional("prof");
        agendamentoModel.setFkProfissional(profissionalModel);

        LocalAtendimentoModel localAtendimentoModel = new LocalAtendimentoModel();
        localAtendimentoModel.setNomeLocal("nome");
        agendamentoModel.setFkLocalAtendimento(localAtendimentoModel);

        agendamento.add(agendamentoModel);

        adapter = new AgendamentoAlunoAdapter(agendamento);

        agendamentoAlunoRecycler.setAdapter(adapter);

        adapter.setListener(new AgendamentoAlunoAdapter.Listener() {
            @Override
            public void onClick(AgendamentoModel agendamentoModel) {

            }
        });

    }
}