package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;

public class LocalAtendimentoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewLocal, textViewBloco;

    public LocalAtendimentoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_local_atendimento);
        this.textViewLocal = itemView.findViewById(R.id.txt_local_atendimento_lista);
        this.textViewBloco = itemView.findViewById(R.id.txt_bloco_lista);
    }

    public void bindData(final LocalAtendimento localAtendimento, final OnLocalAtendimentoInteractionListener listener) {

        textViewBloco.setText(localAtendimento.getNomeBloco());
        textViewLocal.setText(localAtendimento.getNomeLocal());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(localAtendimento);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(localAtendimento);
                return true;
            }
        });
    }

}
