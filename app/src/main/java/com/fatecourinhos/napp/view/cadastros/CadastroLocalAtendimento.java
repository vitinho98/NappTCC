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
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.LocalAtendimentoController;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;

public class CadastroLocalAtendimento extends AppCompatDialogFragment {

    public CadastroLocalAtendimento(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.cadastro_activity_local, null);
        final AppCompatEditText editTextNomeBloco = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_bloco);
        final AppCompatEditText editTextNomeLocal = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_sala);

        builder.setView(view).setTitle("Local de Atendimento");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        if(getArguments() != null){

            Bundle data = getArguments();

            final LocalAtendimentoModel localAtendimentoModel = new LocalAtendimentoModel();
            localAtendimentoModel.setIdLocalAtendimento(data.getInt("idLocalAtendimento"));

            editTextNomeBloco.setText(data.getString("nomeBloco"));
            editTextNomeLocal.setText(data.getString("nomeLocal"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeBloco.getText().toString().isEmpty() && editTextNomeLocal.getText().toString().isEmpty()){

                        Toast.makeText(getContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();

                    }else{

                        localAtendimentoModel.setNomeBloco(editTextNomeBloco.getText().toString());
                        localAtendimentoModel.setNomeLocal(editTextNomeLocal.getText().toString());

                        if(LocalAtendimentoController.alterarLocalAtendimento(localAtendimentoModel)){

                            Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }else{

                            Toast.makeText(getContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });

        }else{

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeBloco.getText().toString().isEmpty() && editTextNomeLocal.getText().toString().isEmpty()) {

                        Toast.makeText(getContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();

                    }else{

                        LocalAtendimentoModel localAtendimento = new LocalAtendimentoModel();
                        localAtendimento.setNomeLocal(editTextNomeLocal.getText().toString());
                        localAtendimento.setNomeBloco(editTextNomeBloco.getText().toString());

                        if(LocalAtendimentoController.inserirLocalAtendimento(localAtendimento)){

                            Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }else{

                            Toast.makeText(getContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();

                        }
                    }
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
