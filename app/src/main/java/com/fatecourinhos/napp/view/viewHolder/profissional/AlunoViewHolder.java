package com.fatecourinhos.napp.view.viewHolder.profissional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.view.listener.OnAlunoInteractionListener;

public class AlunoViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView textView;

    public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.card_view_aluno);
            this.textView = itemView.findViewById(R.id.txt_nome_aluno_view_holder);
        }

        public void bindData(final Aluno aluno, final OnAlunoInteractionListener listener) {

            textView.setText(aluno.getNomeAluno());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onListClick(aluno);
                }
            });

        }

}
