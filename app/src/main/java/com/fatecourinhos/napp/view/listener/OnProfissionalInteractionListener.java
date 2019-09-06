package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.Profissional;

public interface OnProfissionalInteractionListener {
    void onListClick(Profissional profissional);
    void onDeleteClick(Profissional profissional);
}
