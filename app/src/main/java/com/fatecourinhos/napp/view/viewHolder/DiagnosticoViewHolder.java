package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;

public class DiagnosticoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textView;

    public DiagnosticoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.card_view_diagnostico);
        this.textView = itemView.findViewById(R.id.txt_diagnostico_lista);
    }

    public void bindData(final Diagnostico diagnostico, final OnDiagnosticoInteractionListener listener){
        textView.setText(diagnostico.getNomeDiagnostico());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(diagnostico);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(diagnostico);
                return true;
            }
        });
    }

}
