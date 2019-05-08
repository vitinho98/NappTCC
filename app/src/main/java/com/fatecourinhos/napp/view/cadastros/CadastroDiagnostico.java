package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.controller.DiagnosticoController;
import com.fatecourinhos.napp.model.DiagnosticoModel;

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
        final AppCompatEditText editTextNomeDiagnostico = (AppCompatEditText)getActivity().findViewById(R.id.edit_text_nome_diagnostico);

        builder.setView(view).setTitle("Diagnóstico");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        if(getArguments() != null){

            Bundle data = getArguments();
            editTextNomeDiagnostico.setText(data.getString("nomeDiagnostico"));

            final DiagnosticoModel diagnostico = new DiagnosticoModel();
            diagnostico.setIdDiagostico(data.getInt("idDiagnostico"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeDiagnostico.getText().toString().isEmpty()){
                        Toast.makeText(getContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();
                    }else{
                        diagnostico.setNomeDiagnotico(editTextNomeDiagnostico.getText().toString());

                        if(DiagnosticoController.alterarDiagnostico(diagnostico)) {
                            Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else
                            Toast.makeText(getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(editTextNomeDiagnostico.getText().toString().isEmpty()){
                        Toast.makeText(getContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();
                    }else{
                        DiagnosticoModel diagnosticoModel = new DiagnosticoModel();
                        diagnosticoModel.setNomeDiagnotico(editTextNomeDiagnostico.getText().toString());

                        if(DiagnosticoController.inserirDiagnostico(diagnosticoModel)) {
                            Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else
                            Toast.makeText(getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
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
