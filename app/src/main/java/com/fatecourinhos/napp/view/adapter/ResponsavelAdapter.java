package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.CampoAtuacaoViewHolder;
import com.fatecourinhos.napp.view.viewHolder.ResponsavelViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelAdapter extends RecyclerView.Adapter<ResponsavelViewHolder>{

    private List<Responsavel> responsaveis;
    private OnResponsavelInteractionListener listener;

    public ResponsavelAdapter(List<Responsavel> responsaveis, OnResponsavelInteractionListener listener){
        this.responsaveis=responsaveis;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ResponsavelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_responsavel, parent, false);
        return new ResponsavelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResponsavelViewHolder holder, final int position) {
        Responsavel responsavel = responsaveis.get(position);
        holder.bindData(responsavel, listener);
    }

    @Override
    public int getItemCount() {
        if(responsaveis != null)
            return responsaveis.size();
        else
            return 0;
    }

}