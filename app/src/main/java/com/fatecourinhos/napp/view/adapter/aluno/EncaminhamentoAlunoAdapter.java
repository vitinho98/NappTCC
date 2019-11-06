package com.fatecourinhos.napp.view.adapter.aluno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Encaminhamento;
import com.fatecourinhos.napp.view.listener.OnEncaminhamentoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.aluno.EncaminhamentoAlunoViewHolder;

import java.util.List;

public class EncaminhamentoAlunoAdapter extends RecyclerView.Adapter<EncaminhamentoAlunoViewHolder> {

    private List<Encaminhamento> encaminhamentos;
    private OnEncaminhamentoInteractionListener listener;

    public EncaminhamentoAlunoAdapter(List<Encaminhamento> encaminhamentos, OnEncaminhamentoInteractionListener listener){
        this.encaminhamentos = encaminhamentos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EncaminhamentoAlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_encaminhamento, parent, false);
        return new EncaminhamentoAlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EncaminhamentoAlunoViewHolder holder, int position) {
        Encaminhamento encaminhamento = encaminhamentos.get(position);
        holder.bindData(encaminhamento, listener);

    }

    @Override
    public int getItemCount() {
        if (encaminhamentos != null)
            return encaminhamentos.size();
        else
            return 0;
    }

}
