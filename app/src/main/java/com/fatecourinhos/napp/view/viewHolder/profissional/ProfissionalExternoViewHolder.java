package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;

public class ProfissionalExternoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewNome, textViewCidade, textViewTelefone, textViewCampoAtuacao;

    public ProfissionalExternoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_profissional_externo);
        this.textViewNome = itemView.findViewById(R.id.txt_nome_profissional_externo_lista);
        this.textViewCidade = itemView.findViewById(R.id.txt_cidade_profissional_externo_lista);
        this.textViewTelefone = itemView.findViewById(R.id.txt_telefone_profissional_externo_lista);
        this.textViewCampoAtuacao = itemView.findViewById(R.id.txt_campo_atuacao_profissional_externo_lista);
    }

    public void bindData(final ProfissionalExterno profissionalExterno, final OnProfissionalExternoInteractionListener listener) {

        textViewNome.setText(profissionalExterno.getNomeProfissionalExterno());
        textViewCidade.setText(profissionalExterno.getCidadeProfissionalExterno());
        textViewTelefone.setText(profissionalExterno.getTelefoneProfissionalExterno());
        textViewCampoAtuacao.setText(
                profissionalExterno.getFkCampoAtuacao() != null ? profissionalExterno.getFkCampoAtuacao().getNomeCampoAtuacao() : "Nenhum");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(profissionalExterno);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(profissionalExterno);
                return true;
            }
        });
    }

}
