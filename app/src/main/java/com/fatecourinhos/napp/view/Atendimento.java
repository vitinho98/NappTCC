package com.fatecourinhos.napp.view;

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

public class Atendimento extends AppCompatDialogFragment {

    private Bundle bundle;
    private Button btnDiagnostico, btnPrognostico, btnEncaminhamento;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.atendimento, null);
        dialog.setView(view);

        bundle = new Bundle();
        bundle.putInt("idAgendamento", getArguments().getInt("idAgendamento"));

        btnDiagnostico = view.findViewById(R.id.btn_diagnostico);
        btnPrognostico = view.findViewById(R.id.btn_prognostico);
        btnEncaminhamento = view.findViewById(R.id.btn_encaminhamento);

        btnDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DefinirDiagnostico.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnPrognostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnEncaminhamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DefinirEncaminhamento.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return dialog.create();
    }

}
