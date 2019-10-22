package com.fatecourinhos.napp.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.RequestHttp;
import com.google.android.material.textfield.TextInputLayout;

public class AlterarSenha extends AppCompatDialogFragment {

    private View view;
    private String conteudo;
    private boolean sucesso;
    private SharedPreferences preferences;
    private int idUsuario;
    private AppCompatEditText editTextSenhaAtual, editTextNovaSenha, editTextNovaSenha2;
    private TextInputLayout textInputLayoutSenhaAtual, textInputLayoutSenhaNova, textInputLayoutSenhaNova2;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_trocar_senha, null);

        preferences = getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (preferences.contains("idUsuario"))
            idUsuario = preferences.getInt("idUsuario", 0);

        textInputLayoutSenhaAtual = view.findViewById(R.id.txt_layout_senha_atual);
        textInputLayoutSenhaNova = view.findViewById(R.id.txt_layout_senha_nova);
        textInputLayoutSenhaNova2 = view.findViewById(R.id.txt_layout_senha_nova2);

        editTextSenhaAtual = view.findViewById(R.id.edit_text_senha_atual);
        editTextNovaSenha = view.findViewById(R.id.edit_text_senha_nova);
        editTextNovaSenha2 = view.findViewById(R.id.edit_text_senha_nova2);

        dialog.setView(view).setTitle("Alterar Senha")

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                }).setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                conferirDados();
            }
        });

        return dialog.create();
    }

    private void conferirDados() {

        boolean bol = true;

        if (editTextSenhaAtual.getText().toString().isEmpty()) {

            textInputLayoutSenhaAtual.setErrorEnabled(true);
            textInputLayoutSenhaAtual.setError("Insira a senha atual");
            bol = false;

        } else
            textInputLayoutSenhaAtual.setErrorEnabled(false);

        if (editTextNovaSenha.getText().toString().isEmpty()) {

            textInputLayoutSenhaNova.setErrorEnabled(true);
            textInputLayoutSenhaNova.setError("Insira a nova senha");
            bol = false;

        } else
            textInputLayoutSenhaNova.setErrorEnabled(false);

        if (editTextNovaSenha2.getText().toString().isEmpty()) {

            textInputLayoutSenhaNova2.setErrorEnabled(true);
            textInputLayoutSenhaNova2.setError("Insira a nova senha");
            bol = false;

        } else
            textInputLayoutSenhaNova2.setErrorEnabled(false);

        if (bol)
            if (editTextNovaSenha2.getText().toString().equals(editTextNovaSenha.getText().toString()))
                alterarSenha(idUsuario, editTextSenhaAtual.getText().toString() ,editTextNovaSenha.getText().toString());
            else
                Toast.makeText(getContext(), "Senhas diferentes!", Toast.LENGTH_LONG).show();

    }

    private void alterarSenha(int id, String senhaAtual, String senhaNova) {

        String uri = "http://vitorsilva.xyz/napp/usuario/alterarSenha.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarSenhaUsuario task = new AlterarSenhaUsuario();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idUsuario", String.valueOf(id));
        requestHttp.setParametro("senhaAtual", senhaAtual);
        requestHttp.setParametro("senhaNova", senhaNova);

        task.execute(requestHttp);

    }

    private class AlterarSenhaUsuario extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(view.getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(view.getContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();
        }

    }

}
