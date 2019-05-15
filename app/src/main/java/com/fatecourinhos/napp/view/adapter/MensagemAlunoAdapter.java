package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.MensagemModel;

import java.util.List;

public class MensagemAlunoAdapter extends RecyclerView.Adapter<MensagemAlunoAdapter.ViewHolder>{

    private List<MensagemModel> mensagens;
    private Listener listener;

    public static interface Listener{
        public void onClick(MensagemModel mensagemModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public MensagemAlunoAdapter(List<MensagemModel> mensagens){
        this.mensagens=mensagens;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MensagemAlunoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_mensagens_aluno,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;

        TextView enviadorPor = (TextView) cardView.findViewById(R.id.txt_enviado_por);
        TextView txtNomeProfissional = (TextView) cardView.findViewById(R.id.txt_nome_profissional_enviou);
        TextView txtDataHora = (TextView) cardView.findViewById(R.id.txt_data_hora_mensagem_aluno);
        TextView txtMsg = (TextView) cardView.findViewById(R.id.txt_msg_aluno);

        final MensagemModel mensagemModel = mensagens.get(position);

        txtMsg.setText(mensagemModel.getMensagem());
        txtNomeProfissional.setText(mensagemModel.getFkProfissional().getNomeProfissional());
        txtDataHora.setText(mensagemModel.getData() + " " + mensagemModel.getHora());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(mensagemModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mensagens != null)
            return mensagens.size();
        else
            return 0;
    }

}