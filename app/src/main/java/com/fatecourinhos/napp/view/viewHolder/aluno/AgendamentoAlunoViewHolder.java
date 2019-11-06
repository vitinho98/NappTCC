package com.fatecourinhos.napp.view.viewHolder.aluno;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.view.listener.OnAgendamentoInteractionListener;

import java.text.SimpleDateFormat;

public class AgendamentoAlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private ImageView imageView;
    private TextView textViewProfissional, textViewHorario, textViewLocal, textViewBloco;

    public AgendamentoAlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view_ag_aluno);
        imageView = itemView.findViewById(R.id.img_cancelar_ag_aluno);
        textViewProfissional = itemView.findViewById(R.id.txt_nome_profissional_ag_aluno);
        textViewHorario = itemView.findViewById(R.id.txt_data_hora_ag_aluno);
        textViewLocal = itemView.findViewById(R.id.txt_local_ag_aluno);
        textViewBloco = itemView.findViewById(R.id.txt_bloco_ag_aluno);
    }

    public void bindData(final Agendamento agendamento, final OnAgendamentoInteractionListener listener) {

        SimpleDateFormat dataHoraFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        textViewProfissional.setText(agendamento.getFkHorario().getFkProfissional().getNomeProfissional());
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

        if (agendamento.getStatus().equals("Cancelado")) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(itemView.getContext());
                    dialog.setTitle("Motivo Cancelamento");
                    dialog.setMessage(agendamento.getMotivoCancelamento());
                    dialog.setNeutralButton("Ok", null);
                    dialog.show();
                }
            });
        } else if (!agendamento.getStatus().equals("Atendido")){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onListClick(agendamento);
                }
            });
        }

    }

}
