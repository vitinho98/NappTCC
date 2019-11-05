package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.view.listener.OnHorarioProfissionalnteractionListener;

import java.text.SimpleDateFormat;

public class HorarioProfissionalViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textViewData, textViewHora;

    public HorarioProfissionalViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cardView = itemView.findViewById(R.id.card_view_horario_atendimento);
        this.textViewData = itemView.findViewById(R.id.txt_data_lista);
        this.textViewHora = itemView.findViewById(R.id.txt_horario_lista);
    }

    public void bindData(final Horario horario, final OnHorarioProfissionalnteractionListener listener) {

        SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat horaFormater = new SimpleDateFormat("HH:mm");

        textViewData.setText(dataFormater.format(horario.getData()));
        textViewHora.setText(horaFormater.format(horario.getData()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(horario);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeleteClick(horario);
                return true;
            }
        });
    }

}
