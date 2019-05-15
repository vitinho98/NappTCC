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
import com.fatecourinhos.napp.model.MensagemModel;
import com.fatecourinhos.napp.view.adapter.AgendamentoAlunoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroAgendamento;
import com.fatecourinhos.napp.view.cadastros.Mensagens;

import java.util.List;

public class MensagemAlunoFragment extends Fragment{

    List<MensagemModel> mensagens;
    AgendamentoAlunoAdapter adapter;
    RecyclerView mensagensAlunoRecycler;
    SharedPreferences preferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mensagensAlunoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_mensagens,container,false);
        mensagensAlunoRecycler.setLayoutManager(layoutManager);

        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Mensagens");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AlunoController alunoController = new AlunoController();

        mensagens = alunoController.selecionarAgendamento(preferences.getInt("idUsuario", 0));

        adapter = new AgendamentoAlunoAdapter(mensagens);

        mensagensAlunoRecycler.setAdapter(adapter);

        adapter.setListener(new AgendamentoAlunoAdapter.Listener() {
            @Override
            public void onClick(MensagemModel agendamentoModel) {
                Intent intent = new Intent(getActivity(), Mensagens.class);

                startActivity(intent);
            }
        });
    }
}