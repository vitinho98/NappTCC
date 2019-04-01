package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HorarioAtendimentoAdapter extends RecyclerView.Adapter<HorarioAtendimentoAdapter.ViewHolder>{

    private List<AgendaProfissionalModel> agendaProfisisonais;
    private Listener listener;

    public static interface Listener{
        public void onClick(AgendaProfissionalModel agendaProfissionais);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public HorarioAtendimentoAdapter(List<AgendaProfissionalModel> agendaProfisisonais){
        this.agendaProfisisonais=agendaProfisisonais;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public HorarioAtendimentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_horario_atendimento,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtDia = (TextView)cardView.findViewById(R.id.txt_dia_atendimento);
        TextView txtHora = (TextView)cardView.findViewById(R.id.txt_horario_atendimento);


        final AgendaProfissionalModel agendaProfissional = agendaProfisisonais.get(position);

        txtDia.setText(agendaProfissional.getDiaDaSemana());
        txtHora.setText(agendaProfissional.getHorario());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(agendaProfissional);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return agendaProfisisonais.size();
    }


}
