package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.ProfissionalExternoViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoAdapter extends RecyclerView.Adapter<ProfissionalExternoViewHolder> {

    private List<ProfissionalExterno> profissionaisExterno;
    private OnProfissionalExternoInteractionListener listener;

    public ProfissionalExternoAdapter(List<ProfissionalExterno> profissionaisExterno, OnProfissionalExternoInteractionListener listener) {
        this.listener = listener;
        this.profissionaisExterno = profissionaisExterno;
    }

    @NonNull
    @Override
    public ProfissionalExternoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_profissional_externo, parent, false);
        return new ProfissionalExternoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfissionalExternoViewHolder holder, int position) {
        ProfissionalExterno profissionalExterno = profissionaisExterno.get(position);
        holder.bindData(profissionalExterno, listener);
    }

    @Override
    public int getItemCount() {
        if (profissionaisExterno != null)
            return profissionaisExterno.size();
        else
            return 0;
    }

}