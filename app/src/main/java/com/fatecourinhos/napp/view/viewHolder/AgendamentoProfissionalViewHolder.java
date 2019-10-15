package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;

import java.text.SimpleDateFormat;

public class AgendamentoProfissionalViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewAluno, textViewHorario, textViewLocal, textViewBloco;

    public AgendamentoProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.img_cancelar_ag_prof);
        textViewAluno = itemView.findViewById(R.id.txt_nome_aluno_ag_prof);
        textViewHorario = itemView.findViewById(R.id.txt_data_hora_ag_prof);
        textViewLocal = itemView.findViewById(R.id.txt_local_ag_prof);
        textViewBloco = itemView.findViewById(R.id.txt_bloco_ag_prof);
    }

    public void bindData(final Agendamento agendamento, final OnAgendamentoInteractionListener listener) {

        SimpleDateFormat dataHoraFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        textViewAluno.setText(agendamento.getFkHorario().getFkProfissional().getNomeProfissional());
        textViewHorario.setText(dataHoraFormater.format(agendamento.getFkHorario().getData()));
        textViewLocal.setText(agendamento.getFkLocalAtendimento().getNomeLocal() != null ?
                "Local: " + agendamento.getFkLocalAtendimento().getNomeLocal() :
                "A definir");
        textViewBloco.setText(agendamento.getFkLocalAtendimento().getNomeBloco() != null ?
                "Bloco: " + agendamento.getFkLocalAtendimento().getNomeBloco() :
                "A definir");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(agendamento);
            }
        });

    }


}
