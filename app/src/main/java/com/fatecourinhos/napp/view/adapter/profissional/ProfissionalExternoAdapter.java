package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.ProfissionalExternoViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoAdapter extends RecyclerView.Adapter<ProfissionalExternoViewHolder> implements Filterable {

    private List<ProfissionalExterno> profissionaisExterno;
    private List<ProfissionalExterno> profissionalExternosCompleto;
    private OnProfissionalExternoInteractionListener listener;

    public ProfissionalExternoAdapter(List<ProfissionalExterno> profissionaisExterno, OnProfissionalExternoInteractionListener listener) {
        this.listener = listener;
        this.profissionaisExterno = profissionaisExterno;
        this.profissionalExternosCompleto = new ArrayList<>(profissionaisExterno);
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

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<ProfissionalExterno> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(profissionalExternosCompleto);

            else
                for (ProfissionalExterno profissionalExterno : profissionalExternosCompleto)
                    if (profissionalExterno.getNomeProfissionalExterno().toLowerCase().contains(charSequence))
                        listaFiltrada.add(profissionalExterno);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profissionaisExterno.clear();
            profissionaisExterno.addAll((List<ProfissionalExterno>) results.values);
            notifyDataSetChanged();
        }
    };

}