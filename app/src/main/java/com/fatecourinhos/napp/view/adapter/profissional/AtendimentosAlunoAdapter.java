package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Atendimento;
import com.fatecourinhos.napp.view.listener.OnAtendimentosAlunoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.AtendimentosAlunoViewHolder;

import java.util.List;

public class AtendimentosAlunoAdapter extends RecyclerView.Adapter<AtendimentosAlunoViewHolder> {

    private List<Atendimento> atendimentos;
    private OnAtendimentosAlunoInteractionListener listener;

    public AtendimentosAlunoAdapter(List<Atendimento> atendimentos, OnAtendimentosAlunoInteractionListener listener) {
        this.atendimentos = atendimentos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AtendimentosAlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_atendimentos_aluno, parent, false);
        return new AtendimentosAlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtendimentosAlunoViewHolder holder, int position) {
        Atendimento atendimento = atendimentos.get(position);
        holder.bindData(atendimento, listener);
    }

    @Override
    public int getItemCount() {
        if (atendimentos != null)
            return atendimentos.size();
        else
            return 0;
    }

}
