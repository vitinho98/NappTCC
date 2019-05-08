package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;

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
        final AppCompatEditText editTextNomeArea = (AppCompatEditText) getActivity().findViewById(R.id.edit_text_nome_area);

        builder.setView(view).setTitle("Área de atuação");

        //caso seja clicado na lista
        if(getArguments() != null) {
            Bundle data = getArguments();

            editTextNomeArea.setText(data.getString("nomeCampoAtuacao"));

            final CampoAtuacaoModel campoAtuacao = new CampoAtuacaoModel();
            campoAtuacao.setIdCampoAtuacao(data.getInt("idCampoAtuacao"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeArea.getText().toString().isEmpty()){
                        Toast.makeText(getContext(),"Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();
                    }else {
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeArea.getText().toString());

                        if(CampoAtuacaoController.alterarCampoAtuacao(campoAtuacao)) {
                            Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else
                            Toast.makeText(getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else{

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeArea.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();
                    }else{
                        CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
                        campoAtuacaoModel.setNomeCampoAtuacao(editTextNomeArea.getText().toString());
                        if(CampoAtuacaoController.inserirCampoAtuacao(campoAtuacaoModel)) {
                            Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else
                            Toast.makeText(getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
