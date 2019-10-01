package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.view.listener.OnProfissionalInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.ProfissionalViewHolder;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalAdapter extends RecyclerView.Adapter<ProfissionalViewHolder> {

    private List<Profissional> profissionais;
    private OnProfissionalInteractionListener listener;

    public ProfissionalAdapter(List<Profissional> profissionais, OnProfissionalInteractionListener listener) {
        this.profissionais = profissionais;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ProfissionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_profissional, parent, false);
        return new ProfissionalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfissionalViewHolder holder, int position) {
        Profissional profissional = profissionais.get(position);
        holder.bindData(profissional, listener);
    }

    @Override
    public int getItemCount() {
        if (profissionais != null)
            return profissionais.size();
        else
            return 0;
    }

}
