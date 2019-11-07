package com.fatecourinhos.napp.view.consultaraluno;

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
import com.fatecourinhos.napp.view.cadastros.aluno.CadastroAnamnese;

public class VerDadosAluno extends AppCompatDialogFragment {

    private Bundle bundle;
    private Button btnDadosPessoais, btnAnamnese, btnAtendimentos;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_dados_aluno, null);
        dialog.setView(view);

        bundle = new Bundle();
        bundle.putInt("idAluno", getArguments().getInt("idAluno"));

        btnDadosPessoais = view.findViewById(R.id.btn_dados_pessoais);
        btnAnamnese = view.findViewById(R.id.btn_anamnese);
        btnAtendimentos = view.findViewById(R.id.btn_atendimentos);

        btnDadosPessoais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DadosPessoaisAluno.class);
                intent.putExtras(bundle);
                startActivity(intent);
                dismiss();
            }
        });

        btnAtendimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AtendimentosAluno.class);
                intent.putExtras(bundle);
                startActivity(intent);
                dismiss();
            }
        });

        btnAnamnese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroAnamnese.class);
                intent.putExtras(bundle);
                startActivity(intent);
                dismiss();
            }
        });

        return dialog.create();
    }

}
