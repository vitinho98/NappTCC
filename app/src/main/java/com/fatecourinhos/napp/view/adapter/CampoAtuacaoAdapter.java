package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;

import java.util.List;

public class CampoAtuacaoAdapter extends RecyclerView.Adapter<CampoAtuacaoAdapter.ViewHolder>{

    private List<CampoAtuacao> camposAtuacao;
    private Listener listener;

    public static interface Listener{
        public void onClick(CampoAtuacao camposAtuacao);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public CampoAtuacaoAdapter(List<CampoAtuacao> camposAtuacao){
        this.camposAtuacao=camposAtuacao;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public CampoAtuacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_area_atuacao,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtCampoAtuacao = (TextView) cardView.findViewById(R.id.txt_area_lista);

        final CampoAtuacao campoAtuacao = camposAtuacao.get(position);

        txtCampoAtuacao.setText(campoAtuacao.getNomeCampoAtuacao());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(campoAtuacao);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(camposAtuacao != null)
            return camposAtuacao.size();
        else
            return 0;
    }

}