package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.LocalAtendimentoViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoAdapter extends RecyclerView.Adapter<LocalAtendimentoViewHolder> {

    private List<LocalAtendimento> locaisAtendimento;
    private OnLocalAtendimentoInteractionListener listener;

    public LocalAtendimentoAdapter(List<LocalAtendimento> locaisAtendimento, OnLocalAtendimentoInteractionListener listener) {
        this.locaisAtendimento = locaisAtendimento;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LocalAtendimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_local_atendimento, parent, false);
        return new LocalAtendimentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAtendimentoViewHolder holder, final int position) {
        LocalAtendimento localAtendimento = locaisAtendimento.get(position);
        holder.bindData(localAtendimento, listener);
    }

    @Override
    public int getItemCount() {
        if (locaisAtendimento != null)
            return locaisAtendimento.size();
        else
            return 0;
    }

}