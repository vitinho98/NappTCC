package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.view.listener.OnAgendaProfissionalnteractionListener;
import com.fatecourinhos.napp.view.viewHolder.AgendaProfissionalViewHolder;
import com.fatecourinhos.napp.view.viewHolder.CampoAtuacaoViewHolder;

import java.util.List;

public class AgendaProfissionalAdapter extends RecyclerView.Adapter<AgendaProfissionalViewHolder> {

    private List<AgendaProfissional> agendaProfissional;
    private OnAgendaProfissionalnteractionListener listener;

    public AgendaProfissionalAdapter(List<AgendaProfissional> agendaProfissional, OnAgendaProfissionalnteractionListener listener){
        this.agendaProfissional = agendaProfissional;
        this.listener = listener;
    }
    @NonNull
    @Override
    public AgendaProfissionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_horario_atendimento, parent, false);
        return new AgendaProfissionalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaProfissionalViewHolder holder, int position) {
        AgendaProfissional agendaProfissional = this.agendaProfissional.get(position);
        holder.bindData(agendaProfissional, listener);
    }

    @Override
    public int getItemCount() {
        if (agendaProfissional == null)
            return 0;
        else
            return agendaProfissional.size();
    }

}
