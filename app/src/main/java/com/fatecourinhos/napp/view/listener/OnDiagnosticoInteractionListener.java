package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Diagnostico;

public interface OnDiagnosticoInteractionListener {
    void onListClick(Diagnostico diagnostico);
    void onDeleteClick(Diagnostico diagnostico);
}
