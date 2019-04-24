package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.AgendamentoModel;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;

import java.util.List;

public class AgendamentoAlunoAdapter extends RecyclerView.Adapter<AgendamentoAlunoAdapter.ViewHolder>{

    private List<AgendamentoModel> agendamento;
    private Listener listener;

    public static interface Listener{
        public void onClick(AgendamentoModel agendamentoModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public AgendamentoAlunoAdapter(List<AgendamentoModel> agendamento){
        this.agendamento=agendamento;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgendamentoAlunoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_agendamento_aluno,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtNomeProfissional = (TextView) cardView.findViewById(R.id.txt_nome_profissional_ag_aluno);
        TextView txtDataHora = (TextView) cardView.findViewById(R.id.txt_data_hora_ag_aluno);

        final AgendamentoModel agendamentoModel = agendamento.get(position);

        txtNomeProfissional.setText(agendamentoModel.getFkProfissional().getNomeProfissional());
        txtDataHora.setText((CharSequence) agendamentoModel.getDataHoraAgendamento());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(agendamentoModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(agendamento != null)
            return agendamento.size();
        else
            return 0;
    }

}