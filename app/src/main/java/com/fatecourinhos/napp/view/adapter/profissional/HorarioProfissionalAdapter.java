package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.HorarioProfissionalViewHolder;

import java.util.List;

public class HorarioProfissionalAdapter extends RecyclerView.Adapter<HorarioProfissionalViewHolder> {

    private List<Horario> horarios;
    private OnHorarioProfissionalnteractionListener listener;

    public HorarioProfissionalAdapter(List<Horario> horarios, OnHorarioProfissionalnteractionListener listener) {
        this.horarios = horarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HorarioProfissionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_horario_atendimento, parent, false);
        return new HorarioProfissionalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioProfissionalViewHolder holder, int position) {
        Horario horarioProfissional = horarios.get(position);
        holder.bindData(horarioProfissional, listener);
    }

    @Override
    public int getItemCount() {
        if (horarios != null)
            return horarios.size();
        else
            return 0;
    }

}
