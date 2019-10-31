package com.fatecourinhos.napp.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.fatecourinhos.napp.R;

public class ConfirmarAgendamentoProfissional extends AppCompatDialogFragment {

    private Bundle bundle;
    private Button btnLocal, btnCancelar, btnAtendimento;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.confirmar_agendamento_profissional, null);
        dialog.setView(view);

        bundle = new Bundle();
        bundle.putInt("idAgendamento", getArguments().getInt("idAgendamento"));

        btnCancelar = view.findViewById(R.id.btn_cancelar_agendamento_prof);
        btnLocal = view.findViewById(R.id.btn_definir_local);
        btnAtendimento = view.findViewById(R.id.btn_atendimento);

        btnAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Atendimento atendimento = new Atendimento();
                atendimento.setArguments(bundle);
                atendimento.show(getFragmentManager(), "ATENDIMENTO");
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefinirLocalAtendimento definirLocalAtendimento = new DefinirLocalAtendimento();
                definirLocalAtendimento.setArguments(bundle);
                definirLocalAtendimento.show(getFragmentManager(), "DEFINIR LOCAL ATENDIMENTO");
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelarAtendimento cancelarAtendimento = new CancelarAtendimento();
                cancelarAtendimento.setArguments(bundle);
                cancelarAtendimento.show(getFragmentManager(), "CANCELAR ATENDIMENTO");
            }
        });

        return dialog.create();
    }

}
