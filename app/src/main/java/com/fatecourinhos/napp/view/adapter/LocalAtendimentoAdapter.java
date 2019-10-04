package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.LocalAtendimentoViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoAdapter extends RecyclerView.Adapter<LocalAtendimentoViewHolder> implements Filterable {

    private List<LocalAtendimento> locaisAtendimento;
    private List<LocalAtendimento> locaisAtendimentoCompleto;
    private OnLocalAtendimentoInteractionListener listener;

    public LocalAtendimentoAdapter(List<LocalAtendimento> locaisAtendimento, OnLocalAtendimentoInteractionListener listener) {
        this.locaisAtendimento = locaisAtendimento;
        this.locaisAtendimentoCompleto = new ArrayList<>(locaisAtendimento);
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
    public void onBindViewHolder(@NonNull LocalAtendimentoViewHolder holder, int position) {
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

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<LocalAtendimento> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(locaisAtendimentoCompleto);

            else
                for (LocalAtendimento localAtendimento : locaisAtendimentoCompleto)
                    if (localAtendimento.getNomeLocal().toLowerCase().contains(charSequence)
                            || localAtendimento.getNomeBloco().toLowerCase().contains(charSequence))
                        listaFiltrada.add(localAtendimento);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            locaisAtendimento.clear();
            locaisAtendimento.addAll((List<LocalAtendimento>) results.values);
            notifyDataSetChanged();
        }
    };

}