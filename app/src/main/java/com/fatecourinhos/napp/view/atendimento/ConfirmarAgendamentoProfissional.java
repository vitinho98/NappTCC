package com.fatecourinhos.napp.view.atendimento;

import android.app.Dialog;
import android.content.Intent;
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
    private Button btnLocal, btnCancelar, btnAtendimento, btnReagendar;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_confirmar_agendamento_profissional, null);
        dialog.setView(view);

        bundle = new Bundle();
        bundle.putInt("idAgendamento", getArguments().getInt("idAgendamento"));

        btnReagendar = view.findViewById(R.id.btn_reagendar);
        btnCancelar = view.findViewById(R.id.btn_cancelar_agendamento_prof);
        btnLocal = view.findViewById(R.id.btn_definir_local);
        btnAtendimento = view.findViewById(R.id.btn_atendimento);

        btnAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealizarAtendimento atendimento = new RealizarAtendimento();
                atendimento.setArguments(bundle);
                atendimento.show(getFragmentManager(), "ATENDIMENTO");
                dismiss();
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefinirLocalAtendimento definirLocalAtendimento = new DefinirLocalAtendimento();
                definirLocalAtendimento.setArguments(bundle);
                definirLocalAtendimento.show(getFragmentManager(), "DEFINIR LOCAL ATENDIMENTO");
                dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelarAgendamento cancelarAtendimento = new CancelarAgendamento();
                cancelarAtendimento.setArguments(bundle);
                cancelarAtendimento.show(getFragmentManager(), "CANCELAR ATENDIMENTO");
                dismiss();
            }
        });

        btnReagendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReagendarHorario.class);
                intent.putExtras(bundle);
                startActivity(intent);
                dismiss();
            }
        });

        return dialog.create();
    }

}
