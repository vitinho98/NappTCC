package com.fatecourinhos.napp.view.adapter.aluno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Mensagem;
import com.fatecourinhos.napp.view.listener.OnMensagemInteractionListener;
import com.fatecourinhos.napp.view.viewHolder.aluno.MensagemAlunoViewHolder;

import java.util.List;

public class MensagemAlunoAdapter extends RecyclerView.Adapter<MensagemAlunoViewHolder> {

    private List<Mensagem> mensagens;
    private OnMensagemInteractionListener listener;

    public MensagemAlunoAdapter(List<Mensagem> mensagens, OnMensagemInteractionListener listener){
        this.mensagens = mensagens;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MensagemAlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder_mensagens_aluno, parent, false);
        return new MensagemAlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MensagemAlunoViewHolder holder, int position) {
        Mensagem mensagem = mensagens.get(position);
        holder.bindData(mensagem, listener);

    }

    @Override
    public int getItemCount() {
        if (mensagens != null)
            return mensagens.size();
        else
            return 0;
    }

}
