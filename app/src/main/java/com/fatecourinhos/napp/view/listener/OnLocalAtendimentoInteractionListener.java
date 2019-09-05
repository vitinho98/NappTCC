package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.LocalAtendimento;

public interface OnLocalAtendimentoInteractionListener {
    void onListClick(LocalAtendimento localAtendimento);
    void onDeleteClick(LocalAtendimento localAtendimento);
}
