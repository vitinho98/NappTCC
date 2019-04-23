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

public class CadastroAreaAtuacao extends AppCompatDialogFragment {

    public CadastroAreaAtuacao(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro_activity_area_atuacao, null);

        builder.setView(view).setTitle("Área de atuação");

        //caso seja clicado na lista
        if(getArguments() != null) {
            Bundle data = getArguments();
            AppCompatEditText editTextNomeArea = (AppCompatEditText) getActivity().findViewById(R.id.edit_text_nome_area);
            editTextNomeArea.setText(data.getString("nomeCampoAtuacao"));

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

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
