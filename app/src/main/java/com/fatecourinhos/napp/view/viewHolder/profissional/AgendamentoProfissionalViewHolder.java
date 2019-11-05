package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;

import java.text.SimpleDateFormat;

public class AgendamentoProfissionalViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private ImageView imageView;
    private TextView textViewAluno, textViewHorario, textViewLocal, textViewBloco;

    public AgendamentoProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view_ag_prof);
        imageView = itemView.findViewById(R.id.img_cancelar_ag_prof);
        textViewAluno = itemView.findViewById(R.id.txt_nome_aluno_ag_prof);
        textViewHorario = itemView.findViewById(R.id.txt_data_hora_ag_prof);
        textViewLocal = itemView.findViewById(R.id.txt_local_ag_prof);
        textViewBloco = itemView.findViewById(R.id.txt_bloco_ag_prof);
    }

    public void bindData(final Agendamento agendamento, final OnAgendamentoInteractionListener listener) {

        SimpleDateFormat dataHoraFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        textViewAluno.setText(agendamento.getFkAluno().getNomeAluno());
        textViewHorario.setText(dataHoraFormater.format(agendamento.getFkHorario().getData()));
        textViewLocal.setText(agendamento.getFkLocalAtendimento().getNomeLocal() != null ?
                "Local: " + agendamento.getFkLocalAtendimento().getNomeLocal() :
                "Local: A definir");
        textViewBloco.setText(agendamento.getFkLocalAtendimento().getNomeBloco() != null ?
                "Bloco: " + agendamento.getFkLocalAtendimento().getNomeBloco() :
                "Bloco: A definir");

        if(agendamento.getStatus().equals("Confirmado"))
            imageView.setImageResource(R.drawable.ic_agendado);
        else if(agendamento.getStatus().equals("Cancelado"))
            imageView.setImageResource(R.drawable.ic_cancelado);
        else if(agendamento.getStatus().equals("Atendido"))
            imageView.setImageResource(R.drawable.ic_atendido);
        else
            imageView.setImageResource(R.drawable.ic_pendente);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!agendamento.getStatus().equals("Cancelado"))
                    listener.onListClick(agendamento);
            }
        });

    }

}
