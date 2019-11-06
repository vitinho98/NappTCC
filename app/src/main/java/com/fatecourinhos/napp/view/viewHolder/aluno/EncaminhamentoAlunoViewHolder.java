package com.fatecourinhos.napp.view.viewHolder.aluno;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Encaminhamento;
import com.fatecourinhos.napp.view.listener.OnEncaminhamentoInteractionListener;

public class EncaminhamentoAlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewEncaminhadoPor;

    public EncaminhamentoAlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view_encaminhamento_aluno);
        textViewEncaminhadoPor = itemView.findViewById(R.id.txt_encaminhado_por);
    }

    public void bindData(final Encaminhamento encaminhamento, final OnEncaminhamentoInteractionListener listener) {

        textViewEncaminhadoPor.setText("Enviado por: " + encaminhamento.getFkProfissional().getNomeProfissional());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(encaminhamento);
            }
        });
    }

}
