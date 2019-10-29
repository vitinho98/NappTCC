package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Mensagem;

public interface OnMensagemInteractionListener {
    void onListClick(Mensagem mensagem);
    void onDeleteClick(Mensagem mensagem);
}
