package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoAdapter extends RecyclerView.Adapter<LocalAtendimentoAdapter.ViewHolder>{

    private List<LocalAtendimentoModel> locaisAtendimento;
    private Listener listener;

    public static interface Listener{
        public void onClick(LocalAtendimentoModel localAtendimento);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public LocalAtendimentoAdapter(List<LocalAtendimentoModel> locaisAtendimento){
        this.locaisAtendimento=locaisAtendimento;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public LocalAtendimentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_local_atendimento,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtBloco = (TextView)cardView.findViewById(R.id.txt_bloco_local_atendimento_lista);
        TextView txtLocal = (TextView)cardView.findViewById(R.id.txt_local_atendimento_lista);


        final LocalAtendimentoModel localAtendimento = locaisAtendimento.get(position);

        txtLocal.setText(localAtendimento.getNomeLocal());
        txtBloco.setText(localAtendimento.getNomeBloco());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(localAtendimento);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return locaisAtendimento.size();
    }


}
