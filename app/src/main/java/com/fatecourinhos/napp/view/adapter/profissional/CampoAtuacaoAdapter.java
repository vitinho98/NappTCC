package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.CampoAtuacaoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CampoAtuacaoAdapter extends RecyclerView.Adapter<CampoAtuacaoViewHolder> implements Filterable {

    private List<CampoAtuacao> camposAtuacao;
    private List<CampoAtuacao> camposAtuacaoCompleta;
    private OnCampoAtuacaoInteractionListener listener;

    public CampoAtuacaoAdapter(List<CampoAtuacao> camposAtuacao, OnCampoAtuacaoInteractionListener listener) {
        this.camposAtuacao = camposAtuacao;
        this.camposAtuacaoCompleta = new ArrayList<>(camposAtuacao);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CampoAtuacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_campo_atuacao, parent, false);
        return new CampoAtuacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampoAtuacaoViewHolder holder, int position) {
        CampoAtuacao campoAtuacao = camposAtuacao.get(position);
        holder.bindData(campoAtuacao, listener);
    }

    @Override
    public int getItemCount() {
        if (camposAtuacao != null)
            return camposAtuacao.size();
        else
            return 0;
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CampoAtuacao> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(camposAtuacaoCompleta);

            else
                for (CampoAtuacao campoAtuacao : camposAtuacaoCompleta)
                    if (campoAtuacao.getNomeCampoAtuacao().toLowerCase().contains(charSequence))
                        listaFiltrada.add(campoAtuacao);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            camposAtuacao.clear();
            camposAtuacao.addAll((List<CampoAtuacao>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}