package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.ResponsavelViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelAdapter extends RecyclerView.Adapter<ResponsavelViewHolder> implements Filterable {

    private List<Responsavel> responsaveis;
    private List<Responsavel> responsaveisCompleta;
    private OnResponsavelInteractionListener listener;

    public ResponsavelAdapter(List<Responsavel> responsaveis, OnResponsavelInteractionListener listener) {
        this.responsaveis = responsaveis;
        this.responsaveisCompleta = new ArrayList<>(responsaveis);
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
    public void onBindViewHolder(@NonNull ResponsavelViewHolder holder, int position) {
        Responsavel responsavel = responsaveis.get(position);
        holder.bindData(responsavel, listener);
    }

    @Override
    public int getItemCount() {
        if (responsaveis != null)
            return responsaveis.size();
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

            List<Responsavel> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(responsaveisCompleta);

            else
                for (Responsavel responsavel : responsaveisCompleta)
                    if (responsavel.getNomeResponsavel().toLowerCase().contains(charSequence))
                        listaFiltrada.add(responsavel);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            responsaveis.clear();
            responsaveis.addAll((List<Responsavel>) results.values);
            notifyDataSetChanged();
        }
    };

}