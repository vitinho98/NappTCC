package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Agendamento;

public interface OnAgendamentoAlunoInteractionListener {
    void onListClick(Agendamento agendamento);
    void onDeleteClick(Agendamento agendamento);
}
