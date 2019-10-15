package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Agendamento;

public interface OnAgendamentoInteractionListener {
    void onListClick(Agendamento agendamento);
    void onDeleteClick(Agendamento agendamento);
}
