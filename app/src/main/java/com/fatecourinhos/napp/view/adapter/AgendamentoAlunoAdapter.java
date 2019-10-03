package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.view.listener.OnAgendamentoAlunoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.AgendamentoAlunoViewHolder;

import java.util.List;

public class AgendamentoAlunoAdapter extends RecyclerView.Adapter<AgendamentoAlunoViewHolder>{

    private List<Agendamento> agendamentos;
    private OnAgendamentoAlunoInteractionListener listener;

    public AgendamentoAlunoAdapter(List<Agendamento> agendamentos, OnAgendamentoAlunoInteractionListener listener){
        this.listener = listener;
        this.agendamentos = agendamentos;
    }

    @NonNull
    @Override
    public AgendamentoAlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_agendamento_aluno, parent, false);
        return new AgendamentoAlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoAlunoViewHolder holder, int position) {
        Agendamento agendamento = agendamentos.get(position);
        holder.bindData(agendamento, listener);
    }

    @Override
    public int getItemCount() {
        if (agendamentos != null)
            return agendamentos.size();
        else
            return 0;
    }

}