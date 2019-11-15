package com.fatecourinhos.napp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RelatoriosFragment extends Fragment {

    private View view;
    private Context context;
    private Button btnProfissional, btnAluno, btnFeedback;

    public RelatoriosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_relatorios, container, false);

        btnProfissional = view.findViewById(R.id.btn_relatorio_profissional);
        btnAluno = view.findViewById(R.id.btn_relatorio_aluno);
        btnFeedback = view.findViewById(R.id.btn_relatorio_feedback);

        btnProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Relatorio.class);
                intent.putExtra("tipo", 1);

                startActivity(intent);
            }
        });

        btnAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Relatorio.class);
                intent.putExtra("tipo", 2);

                startActivity(intent);
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Relatorio.class);
                intent.putExtra("tipo", 3);

                startActivity(intent);
            }
        });



        return view;
    }

}
