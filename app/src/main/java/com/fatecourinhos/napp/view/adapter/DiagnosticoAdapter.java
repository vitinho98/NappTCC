package com.fatecourinhos.napp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.DiagnosticoModel;

import java.util.List;

public class DiagnosticoAdapter extends RecyclerView.Adapter<DiagnosticoAdapter.ViewHolder>{

    private List<DiagnosticoModel> diagnosticos;
    private Listener listener;

    public static interface Listener{
        public void onClick(DiagnosticoModel diagnostico);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {

            super(itemView);
            cardView = itemView;
        }
    }

    public DiagnosticoAdapter(List<DiagnosticoModel> diagnosticos){
        this.diagnosticos=diagnosticos;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public DiagnosticoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_diagnostico,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        TextView txtDiagnostico = (TextView) cardView.findViewById(R.id.txt_diagnostico_lista);

        final DiagnosticoModel diagnostico = diagnosticos.get(position);

        txtDiagnostico.setText(diagnostico.getNomeDiagnotico());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(diagnostico);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(diagnosticos != null)
            return diagnosticos.size();
        else
            return 0;
    }

}