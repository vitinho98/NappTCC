package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.view.listener.OnProfissionalInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.ProfissionalViewHolder;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalAdapter extends RecyclerView.Adapter<ProfissionalViewHolder> implements Filterable {

    private List<Profissional> profissionais;
    private List<Profissional> profissionaisCompleto;
    private OnProfissionalInteractionListener listener;

    public ProfissionalAdapter(List<Profissional> profissionais, OnProfissionalInteractionListener listener) {
        this.profissionais = profissionais;
        this.profissionaisCompleto = new ArrayList<>(profissionais);
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

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Profissional> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(profissionaisCompleto);

            else
                for (Profissional profissional : profissionaisCompleto)
                    if (profissional.getNomeProfissional().toLowerCase().contains(charSequence))
                        listaFiltrada.add(profissional);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profissionais.clear();
            profissionais.addAll((List<Profissional>) results.values);
            notifyDataSetChanged();
        }
    };

}
