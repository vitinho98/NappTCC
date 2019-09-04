package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;

public class CampoAtuacaoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textView;

    public CampoAtuacaoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.card_view_area_atuacao);
        this.textView = itemView.findViewById(R.id.txt_area_lista);
    }

    public void bindData(CampoAtuacao campoAtuacao){
        textView.setText(campoAtuacao.getNomeCampoAtuacao());
    }

}
