package com.fatecourinhos.napp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.DiagnosticoViewHolder;

import java.util.List;

public class DiagnosticoAdapter extends RecyclerView.Adapter<DiagnosticoViewHolder> {

    private List<Diagnostico> diagnosticos;
    private OnDiagnosticoInteractionListener listener;

    public DiagnosticoAdapter(List<Diagnostico> diagnosticos, OnDiagnosticoInteractionListener listener){
        this.diagnosticos = diagnosticos;
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
    public void onBindViewHolder(@NonNull DiagnosticoViewHolder holder, final int position) {

        Diagnostico diagnostico = this.diagnosticos.get(position);
        holder.bindData(diagnostico, listener);

    }

    @Override
    public int getItemCount() {
        if (diagnosticos != null)
            return diagnosticos.size();
        else
            return 0;
    }

}