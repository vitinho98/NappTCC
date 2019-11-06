package com.fatecourinhos.napp.view.fragments.aluno;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fatecourinhos.napp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VisualizarMensagemAluno extends AppCompatActivity {

    private TextView enviadoPor, dataHora, mensagem;
    private SimpleDateFormat dataHoraFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem_aluno);

        enviadoPor = findViewById(R.id.textViewEnviadoPor);
        dataHora = findViewById(R.id.textViewDataHoraEnvio);
        mensagem = findViewById(R.id.mensagem);

        if (getIntent().getExtras() != null) {

            Date d = new Date();
            d.setTime(getIntent().getExtras().getLong("dataHora", -1));

            dataHora.setText(dataHoraFormater.format(d.getTime()));
            enviadoPor.setText("Enviado por: " + getIntent().getExtras().getString("nomeProfissional"));
            mensagem.setText(getIntent().getExtras().getString("mensagem"));
        }

    }

}
