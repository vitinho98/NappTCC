package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.view.listener.OnAlunoInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.profissional.AlunoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoViewHolder> implements Filterable {

        private List<Aluno> alunos;
        private List<Aluno> alunosCompleto;
        private OnAlunoInteractionListener listener;

        public AlunoAdapter(List<Aluno> alunos, OnAlunoInteractionListener listener) {
            this.alunos = alunos;
            this.alunosCompleto = new ArrayList<>(alunos);
            this.listener = listener;
        }

        @NonNull
        @Override
        public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.view_holder_aluno, parent, false);
            return new AlunoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
            Aluno aluno = alunos.get(position);
            holder.bindData(aluno, listener);
        }

        @Override
        public int getItemCount() {
            if (alunos != null)
                return alunos.size();
            else
                return 0;
        }

        @Override
        public Filter getFilter() {
            return filtro;
        }

        private Filter filtro = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                List<Aluno> listaFiltrada = new ArrayList<>();

                if (charSequence == null || charSequence.length() == 0)
                    listaFiltrada.addAll(alunosCompleto);

                else
                    for (Aluno aluno : alunosCompleto)
                        if (aluno.getNomeAluno().toLowerCase().contains(charSequence))
                            listaFiltrada.add(aluno);

                FilterResults filterResults = new FilterResults();
                filterResults.values = listaFiltrada;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                alunos.clear();
                alunos.addAll((List<Aluno>) filterResults.values);
                notifyDataSetChanged();
            }
        };

}
