package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoAdapter extends RecyclerView.Adapter<ProfissionalExternoAdapter.ViewHolder>{

    private List<ProfissionalExternoModel> profissionaisExterno;
    private Listener listener;

    public static interface Listener{
        public void onClick(ProfissionalExternoModel profissional);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public ProfissionalExternoAdapter(List<ProfissionalExternoModel> profissionaisExterno){
        this.profissionaisExterno=profissionaisExterno;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfissionalExternoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_profissional_externo,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtNome = (TextView)cardView.findViewById(R.id.txt_nome_profissional_externo_lista);
        TextView txtCidade = (TextView)cardView.findViewById(R.id.txt_cidade_profissional_externo_lista);
        TextView txtTelefone = (TextView)cardView.findViewById(R.id.txt_telefone_profissional_externo_lista);

        final ProfissionalExternoModel profissionalExterno = profissionaisExterno.get(position);

        txtNome.setText(profissionalExterno.getNomeProfissionalExterno());
        txtCidade.setText(profissionalExterno.getCidadeProfissionalExterno());
        txtTelefone.setText(profissionalExterno.getTelefoneProfissionalExterno());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(profissionalExterno);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(profissionaisExterno != null)
            return profissionaisExterno.size();
        else
            return 0;
    }

}