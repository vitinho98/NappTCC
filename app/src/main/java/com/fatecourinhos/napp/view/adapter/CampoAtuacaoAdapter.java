package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.CampoAtuacaoViewHolder;

import java.util.List;

public class CampoAtuacaoAdapter extends RecyclerView.Adapter<CampoAtuacaoViewHolder>{

    private List<CampoAtuacao> camposAtuacao;
    private OnCampoAtuacaoInteractionListener listener;

    public CampoAtuacaoAdapter(List<CampoAtuacao> camposAtuacao, OnCampoAtuacaoInteractionListener listener){
        this.camposAtuacao = camposAtuacao;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CampoAtuacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_area_atuacao, parent, false);
        return new CampoAtuacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampoAtuacaoViewHolder holder, final int position) {
        CampoAtuacao campoAtuacao = this.camposAtuacao.get(position);
        holder.bindData(campoAtuacao, listener);
    }

    @Override
    public int getItemCount() {
        if (this.camposAtuacao == null)
            return 0;
        else
            return this.camposAtuacao.size();
    }

}