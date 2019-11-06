package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Encaminhamento;

public interface OnEncaminhamentoInteractionListener {
    void onListClick(Encaminhamento encaminhamento);
    void onDeleteClick(Encaminhamento encaminhamento);
}
