package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
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

    @NonNull
    @Override
    public ProfissionalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_profissional,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardView cardView = holder.cardView;
        ImageView imgFoto = (ImageView)cardView.findViewById(R.id.foto_profissional_lista);
        ImageView imgStatus = (ImageView)cardView.findViewById(R.id.img_status_profissional_lista);
        TextView txtNome = (TextView)cardView.findViewById(R.id.txt_nome_profissional_lista);

        ProfissionalModel profissional = profissionais.get(position);

        txtNome.setText(profissional.getNome());

        if(profissional.getStatus().equals("ativo")){
            imgStatus.setImageResource(R.drawable.ic_ativo);
        }else{
            imgStatus.setImageResource(R.drawable.ic_clear_black_24dp);
        }

        imgFoto.setImageResource(R.drawable.ic_profissional);


    }

    @Override
    public int getItemCount() {
        return profissionais.size();
    }


}
