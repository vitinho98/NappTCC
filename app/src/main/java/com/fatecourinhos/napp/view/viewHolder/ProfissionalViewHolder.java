package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.view.listener.OnProfissionalInteractionListener;

public class ProfissionalViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewNome, textViewEmail, textViewStatus;

    public ProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_profissional);
        this.textViewNome = itemView.findViewById(R.id.txt_header);
        this.textViewEmail = itemView.findViewById(R.id.txt_email_profissional_lista);
        this.textViewStatus = itemView.findViewById(R.id.txt_status_profissional_lista);
    }

    public void bindData(final Profissional profissional, final OnProfissionalInteractionListener listener) {

        textViewNome.setText(profissional.getNomeProfissional());
        textViewEmail.setText(profissional.getEmailProfissional());
        textViewStatus.setText(profissional.getFkUsuario().getStatus() == 0 ? "Ativo" : "Inativo");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(profissional);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(profissional);
                return true;
            }
        });
    }

}
