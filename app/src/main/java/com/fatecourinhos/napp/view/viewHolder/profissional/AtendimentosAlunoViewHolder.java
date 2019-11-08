package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Atendimento;
import com.fatecourinhos.napp.view.listener.OnAtendimentosAlunoInteractionListener;

import java.text.SimpleDateFormat;

public class AtendimentosAlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewQuemAtendeu, textViewData;

    public AtendimentosAlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_atendimento_aluno);
        this.textViewQuemAtendeu = itemView.findViewById(R.id.txt_quem_atendeu);
        this.textViewData = itemView.findViewById(R.id.txt_data_atendimento);
    }

    public void bindData(final Atendimento atendimento, final OnAtendimentosAlunoInteractionListener listener) {

        SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        textViewQuemAtendeu.setText(atendimento.getFkAgendamento().getFkHorario().getFkProfissional().getNomeProfissional());
        textViewData.setText(dataFormater.format(atendimento.getDataHora()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(atendimento);
            }
        });

    }

}
