package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;

public class AgendamentoAlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewProfissional, textViewHorario, textViewLocal;

    public AgendamentoAlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewProfissional = itemView.findViewById(R.id.txt_nome_profissional_ag_aluno);
        textViewHorario = itemView.findViewById(R.id.txt_data_hora_ag_aluno);
        textViewLocal = itemView.findViewById(R.id.txt_local_ag_aluno);
    }
}
