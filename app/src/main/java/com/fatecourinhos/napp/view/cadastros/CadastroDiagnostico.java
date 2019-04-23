package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.fatecourinhos.napp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroDiagnostico extends AppCompatDialogFragment {

    public CadastroDiagnostico(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro_activity_diagnostico, null);

        builder.setView(view).setTitle("Diagnóstico");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if(getArguments() != null){

            Bundle data = getArguments();
            AppCompatEditText editTextNomeDiagnostico = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_diagnostico);
            editTextNomeDiagnostico.setText(data.getString("nomeDiagnostico"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        }else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        return builder.create();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
