package com.fatecourinhos.napp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgFoto;
    public ImageView imgStatus;
    public TextView nome;

    public ProfissionalViewHolder(@NonNull View itemView) {

        super(itemView);

        imgFoto = (ImageView) itemView.findViewById(R.id.foto);
        imgStatus = (ImageView) itemView.findViewById(R.id.img_status_profissional);
        nome = (TextView) itemView.findViewById(R.id.txt_nome_profissional_lista);

    }

}
