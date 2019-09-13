package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;

public class ResponsavelViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewNome, textViewEmail, textViewTelefone;

    public ResponsavelViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_responsavel);
        this.textViewNome = itemView.findViewById(R.id.txt_nome_responsavel_lista);
        this.textViewEmail = itemView.findViewById(R.id.txt_email_responsavel_lista);
        this.textViewTelefone = itemView.findViewById(R.id.txt_telefone_responsavel_lista);
    }

    public void bindData(final Responsavel responsavel, final OnResponsavelInteractionListener listener) {

        textViewNome.setText(responsavel.getNomeResponsavel());
        textViewTelefone.setText(responsavel.getTelefoneResponsavel());
        textViewEmail.setText(responsavel.getEmailResponsavel());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(responsavel);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(responsavel);
                return true;
            }
        });
    }

}
