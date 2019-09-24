package com.fatecourinhos.napp.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.view.listener.OnAgendaProfissionalnteractionListener;

import java.text.SimpleDateFormat;

public class AgendaProfissionalViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewData, textViewHora;

    public AgendaProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_horario_atendimento);
        this.textViewData = itemView.findViewById(R.id.txt_data_lista);
        this.textViewHora = itemView.findViewById(R.id.txt_horario_lista);
    }

    public void bindData(final AgendaProfissional agendaProfissional, final OnAgendaProfissionalnteractionListener listener) {

        SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat horaFormater = new SimpleDateFormat("hh:mm");

        textViewData.setText(dataFormater.format(agendaProfissional.getData()));
        textViewHora.setText(horaFormater.format(agendaProfissional.getData()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(agendaProfissional);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(agendaProfissional);
                return true;
            }
        });
    }

}
