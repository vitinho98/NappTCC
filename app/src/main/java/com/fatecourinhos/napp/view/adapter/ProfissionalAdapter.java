package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatecourinhos.napp.R;

import com.fatecourinhos.napp.model.ProfissionalModel;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalAdapter extends RecyclerView.Adapter<ProfissionalAdapter.ViewHolder>{

    private List<ProfissionalModel> profissionais;
    private Listener listener;

    public static interface Listener{
        public void onClick(ProfissionalModel profissional);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public ProfissionalAdapter(List<ProfissionalModel> profissionais){
        this.profissionais=profissionais;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfissionalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_profissional,parent,false);
        cv.setMinimumWidth(2000);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        ImageView imgFoto = (ImageView)cardView.findViewById(R.id.foto_profissional_lista);
        ImageView imgStatus = (ImageView)cardView.findViewById(R.id.img_status_profissional_lista);
        TextView txtNome = (TextView)cardView.findViewById(R.id.txt_nome_profissional_lista);

        final ProfissionalModel profissional = profissionais.get(position);

        txtNome.setText(profissional.getNomeProfissional());

        if(profissional.getFkUsuario().getStatus() == 1){
            imgStatus.setImageResource(R.drawable.ic_ativo);
        }else{
            imgStatus.setImageResource(R.drawable.ic_clear_black_24dp);
        }

        imgFoto.setImageResource(R.drawable.ic_profissional);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(profissional);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return profissionais.size();
    }


}
