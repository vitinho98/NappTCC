package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Responsavel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelAdapter extends RecyclerView.Adapter<ResponsavelAdapter.ViewHolder>{

    private List<Responsavel> responsaveis;
    private Listener listener;

    public static interface Listener{
        public void onClick(Responsavel profissional);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public ResponsavelAdapter(List<Responsavel> responsaveis){
        this.responsaveis=responsaveis;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResponsavelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_responsavel,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtNome = cardView.findViewById(R.id.txt_nome_responsavel_lista);
        TextView txtCidade = cardView.findViewById(R.id.txt_email_responsavel_lista);
        TextView txtTelefone = cardView.findViewById(R.id.txt_telefone_responsavel_lista);

        final Responsavel responsavel = responsaveis.get(position);

        txtNome.setText(responsavel.getNomeResponsavel());
        txtCidade.setText(responsavel.getEmailResponsavel());
        txtTelefone.setText(responsavel.getCelularResponsavel());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(responsavel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(responsaveis != null)
            return responsaveis.size();
        else
            return 0;
    }

}