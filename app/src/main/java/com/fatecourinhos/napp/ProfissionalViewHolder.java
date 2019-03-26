package com.fatecourinhos.napp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgStatus;
    public ImageView imgEditar;
    public TextView nome;

    public ProfissionalViewHolder(@NonNull View itemView) {

        super(itemView);

        imgEditar = (ImageView) itemView.findViewById(R.id.img_editar_profissional_lista);
        imgStatus = (ImageView) itemView.findViewById(R.id.img_status_profissional_lista);
        nome = (TextView) itemView.findViewById(R.id.txt_nome_profissional_lista);

    }

}
