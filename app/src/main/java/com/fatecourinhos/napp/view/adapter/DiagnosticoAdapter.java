package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.DiagnosticoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoAdapter extends RecyclerView.Adapter<DiagnosticoViewHolder> implements Filterable {

    private List<Diagnostico> diagnosticos;
    private List<Diagnostico> diagnosticosCompleto;
    private OnDiagnosticoInteractionListener listener;

    public DiagnosticoAdapter(List<Diagnostico> diagnosticos, OnDiagnosticoInteractionListener listener) {
        this.diagnosticos = diagnosticos;
        this.diagnosticosCompleto = new ArrayList<>(diagnosticos);
        this.listener = listener;
    }


    @NonNull
    @Override
    public DiagnosticoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_diagnostico, parent, false);
        return new DiagnosticoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosticoViewHolder holder, int position) {
        Diagnostico diagnostico = diagnosticos.get(position);
        holder.bindData(diagnostico, listener);
    }

    @Override
    public int getItemCount() {
        if (diagnosticos != null)
            return diagnosticos.size();
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

            List<Diagnostico> listaFiltrada = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                listaFiltrada.addAll(diagnosticosCompleto);

            else
                for (Diagnostico diagnostico : diagnosticosCompleto)
                    if (diagnostico.getNomeDiagnostico().toLowerCase().contains(charSequence))
                        listaFiltrada.add(diagnostico);

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            diagnosticos.clear();
            diagnosticos.addAll((List<Diagnostico>) results.values);
            notifyDataSetChanged();
        }
    };
}