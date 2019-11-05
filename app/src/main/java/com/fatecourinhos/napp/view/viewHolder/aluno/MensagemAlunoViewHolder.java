package com.fatecourinhos.napp.view.viewHolder.aluno;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Mensagem;
import com.fatecourinhos.napp.view.listener.OnMensagemInteractionListener;

import java.text.SimpleDateFormat;

public class MensagemAlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewEnviadoPor, textViewDataHora, textViewMsg;

    public MensagemAlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view_mensagem_aluno);
        textViewDataHora = itemView.findViewById(R.id.txt_data_hora_mensagem_aluno);
        textViewEnviadoPor = itemView.findViewById(R.id.txt_enviado_por);
        textViewMsg = itemView.findViewById(R.id.txt_msg_aluno);
    }

    public void bindData(final Mensagem mensagem, final OnMensagemInteractionListener listener) {

        SimpleDateFormat dataHoraFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        textViewMsg.setText(mensagem.getMensagem());
        textViewEnviadoPor.setText("Enviado por: " + mensagem.getFkProfissional().getNomeProfissional());
        textViewDataHora.setText(dataHoraFormater.format(mensagem.getDataHora()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(mensagem);
            }
        });
    }

}
