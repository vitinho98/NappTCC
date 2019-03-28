package com.fatecourinhos.napp.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.ProfissionalModel;
import com.fatecourinhos.napp.ProfissionalViewHolder;
import com.fatecourinhos.napp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ProfissionalModel> profissionais;

    public ProfissionalAdapter(List<ProfissionalModel> profissionais, Context context){

        this.profissionais = profissionais;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_profissional, parent, false);

        ProfissionalViewHolder profissionalViewHolder = new ProfissionalViewHolder(view);

        return profissionalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ProfissionalViewHolder profissionalViewHolder = (ProfissionalViewHolder) holder;

        ProfissionalModel profissional  = profissionais.get(position) ;

        ((ProfissionalViewHolder) holder).nome.setText(profissional.getNome());

        if(profissional.getStatus().equals("ativo")) {
            ((ProfissionalViewHolder) holder).imgStatus.setImageResource(R.drawable.ic_ativo);
        }
        else {
            ((ProfissionalViewHolder) holder).imgStatus.setImageResource(R.drawable.ic_clear_black_24dp);
        }

        ((ProfissionalViewHolder) holder).imgFoto.setImageResource(R.drawable.ic_edit_black_24dp);

    }

    @Override
    public int getItemCount() {
        return profissionais.size();
    }
}
