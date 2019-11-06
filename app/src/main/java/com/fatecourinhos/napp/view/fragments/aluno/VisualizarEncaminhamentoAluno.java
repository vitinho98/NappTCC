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

        encaminhadoPor.setText("Encaminhado por: " + getIntent().getExtras().getString("nomeProfissional"));

        String profissional = "Olá, gostaria de sugerir a você o(a) profissional " + getIntent().getExtras().getString("nomeProfissionalExterno");

        try {
            profissional += ", da área de " + getIntent().getExtras().getString("nomeC") + ".\n\n";
        } catch (Exception e) {

        }

        String contato = "Aqui estão os meios de contato: " + "\n";

        try {
            contato += "Telefone: " + getIntent().getExtras().getString("telefoneProfissionalExterno") + "\n";
        } catch (Exception e) {

        }

        try {
            contato += "Email: " + getIntent().getExtras().getString("emailProfissionalExterno") + "\n";
        } catch (Exception e) {

        }

        try {
            contato += "Celular: " + getIntent().getExtras().getString("celularProfissionalExterno") + "\n";
        } catch (Exception e) {

        }

        String localizacao = "\nO profissional encontra-se em: " + "\n";

        try {
            localizacao += "Cidade: " + getIntent().getExtras().getString("cidadeProfissionalExterno") + "\n";
        } catch (Exception e) {

        }

        try {
            localizacao += "Endereço: " + getIntent().getExtras().getString("endereco") + "\n";
        } catch (Exception e) {

        }

        try {
            localizacao += "Numero: " + getIntent().getExtras().getString("numero") + "\n";
        } catch (Exception e) {

        }

        try {
            localizacao += "Bairro: " + getIntent().getExtras().getString("bairro") + "\n";
        } catch (Exception e) {

        }

        String responsavel;

        try {

            responsavel = "\nO responsável é o(a) " + getIntent().getExtras().getString("nomeResponsavel") + ".\n";

            responsavel += "Contato: " + "\n";
            responsavel += "Celular: " + getIntent().getExtras().getString("celularResponsavel") + "\n";
            responsavel += "Email: " + getIntent().getExtras().getString("emailResponsavel") + "\n";
            responsavel += "Telefone: " + getIntent().getExtras().getString("telefoneResponsavel") + "\n";

            texto.setText(profissional + contato + localizacao + responsavel);

        } catch (Exception e) {
            texto.setText(profissional + contato + localizacao);
        }

    }

}
