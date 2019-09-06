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
    private TextView textViewNome, textViewEmail;

    public ProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.card_view_profissional);
        this.textViewNome = itemView.findViewById(R.id.txt_nome_profissional_cab);
        this.textViewEmail = itemView.findViewById(R.id.txt_email_profissional);
    }

    public void bindData(final Profissional profissional, final OnProfissionalInteractionListener listener){
        textViewNome.setText(profissional.getNomeProfissional());
        textViewEmail.setText(profissional.getEmailProfissional());

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
