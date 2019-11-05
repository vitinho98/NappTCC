package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.AgendamentoProfissionalViewHolder;

import java.util.List;

public class AgendamentoProfissionaAdapter extends RecyclerView.Adapter<AgendamentoProfissionalViewHolder>{

    private List<Agendamento> agendamentos;
    private OnAgendamentoInteractionListener listener;

    public AgendamentoProfissionaAdapter(List<Agendamento> agendamentos, OnAgendamentoInteractionListener listener){
        this.listener = listener;
        this.agendamentos = agendamentos;
    }

    @NonNull
    @Override
    public AgendamentoProfissionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_agendamento_profissional, parent, false);
        return new AgendamentoProfissionalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoProfissionalViewHolder holder, int position) {
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
