package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendaProfissional;

import java.sql.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HorarioAtendimentoAdapter extends RecyclerView.Adapter<HorarioAtendimentoAdapter.ViewHolder>{

    private List<AgendaProfissional> agendaProfisisonais;
    private Listener listener;

    public static interface Listener{
        public void onClick(AgendaProfissional agendaProfissionais);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public HorarioAtendimentoAdapter(List<AgendaProfissional> agendaProfisisonais){
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
        TextView txtDia = (TextView)cardView.findViewById(R.id.txt_dia_atendimento_lista);
        TextView txtHora = (TextView)cardView.findViewById(R.id.txt_horario_atendimento_lista);


        final AgendaProfissional agendaProfissional = agendaProfisisonais.get(position);

        //TODO
        //txtDia.setText(Date.valueOf(agendaProfissional.getData()));
        //txtHora.setText(agendaProfissional.getData() + ":" + agendaProfissional.getMinutos());

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
        if(agendaProfisisonais != null)
            return agendaProfisisonais.size();
        else
            return 0;
    }

}