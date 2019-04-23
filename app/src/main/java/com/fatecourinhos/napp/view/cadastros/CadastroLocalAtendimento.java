package com.fatecourinhos.napp.view.cadastros;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.fatecourinhos.napp.R;

public class CadastroLocalAtendimento extends AppCompatDialogFragment {

    public CadastroLocalAtendimento(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro_activity_local, null);

        builder.setView(view).setTitle("Local de Atendimento");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if(getArguments() != null){

            Bundle data = getArguments();

            AppCompatEditText editTextNomeBloco = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_bloco);
            editTextNomeBloco.setText(data.getString("nomeBloco"));

            AppCompatEditText editTextNomeLocal = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_sala);
            editTextNomeLocal.setText(data.getString("nomeLocal"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        }else{

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
