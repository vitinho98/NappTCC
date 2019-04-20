package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelAdapter extends RecyclerView.Adapter<ResponsavelAdapter.ViewHolder>{

    private List<ResponsavelModel> responsaveis;
    private Listener listener;

    public static interface Listener{
        public void onClick(ResponsavelModel profissional);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public ResponsavelAdapter(List<ResponsavelModel> responsaveis){
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
        TextView txtNome = (TextView)cardView.findViewById(R.id.txt_nome_responsavel_lista);
        TextView txtCidade = (TextView)cardView.findViewById(R.id.txt_email_responsavel_lista);
        TextView txtTelefone = (TextView)cardView.findViewById(R.id.txt_telefone_responsavel_lista);

        final ResponsavelModel responsavelModel = responsaveis.get(position);

        txtNome.setText(responsavelModel.getNomeResponsavel());
        txtCidade.setText(responsavelModel.getEmailResponsavel());
        txtTelefone.setText(responsavelModel.getCelularResponsavel());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(responsavelModel);
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