package com.fatecourinhos.napp.view.fragments.aluno;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fatecourinhos.napp.R;

public class VisualizarEncaminhamentoAluno extends AppCompatActivity {

    private TextView texto, encaminhadoPor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encaminhamento_aluno);

        texto = findViewById(R.id.textViewTexto);
        encaminhadoPor = findViewById(R.id.textViewEncaminhadoPor);

    }

}
