package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;

public class CampoAtuacaoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textView;

    public CampoAtuacaoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_campo_atuacao);
        this.textView = itemView.findViewById(R.id.txt_campo_lista);
    }

    public void bindData(final CampoAtuacao campoAtuacao, final OnCampoAtuacaoInteractionListener listener) {

        textView.setText(campoAtuacao.getNomeCampoAtuacao());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(campoAtuacao);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(campoAtuacao);
                return true;
            }
        });
    }

}
