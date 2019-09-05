package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroDiagnostico extends AppCompatDialogFragment {

    //componente da tela
    private AppCompatEditText editTextNomeDiagnostico;

    //variaveis globais
    private View view;
    private Diagnostico diagnostico;
    private String conteudo;
    private boolean sucesso;

    public CadastroDiagnostico() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cadastro_activity_diagnostico, null);
        editTextNomeDiagnostico = view.findViewById(R.id.edit_text_nome_diagnostico);
        builder.setView(view).setTitle("Diagnóstico");

        if (getArguments() != null){

            Bundle data = getArguments();

            editTextNomeDiagnostico.setText(data.getString("nomeDiagnostico"));
            diagnostico = new Diagnostico();
            diagnostico.setIdDiagnostico(data.getInt("idDiagnostico"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeDiagnostico.getText().toString().isEmpty())
                        Toast.makeText(getContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();

                    else {

                        diagnostico.setNomeDiagnostico(editTextNomeDiagnostico.getText().toString());
                        alterarDiagnostico(diagnostico);

                    }
                }
            });

        } else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeDiagnostico.getText().toString().isEmpty())
                        Toast.makeText(getContext(),"Insira o nome do diagnóstico", Toast.LENGTH_SHORT).show();

                    else {

                        diagnostico = new Diagnostico();
                        diagnostico.setNomeDiagnostico(editTextNomeDiagnostico.getText().toString());
                        inserirDiagnostico(diagnostico);

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

    private void inserirDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/inserirDiagnostico.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnostico());

        InserirDiagnostico task = new InserirDiagnostico();
        task.execute(requestHttp);

    }

    private void alterarDiagnostico(Diagnostico diagnostico){

        String uri = "http://vitorsilva.xyz/napp/diagnostico/alterarDiagnostico.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idDiagnostico", String.valueOf(diagnostico.getIdDiagnostico()));
        requestHttp.setParametro("nomeDiagnostico", diagnostico.getNomeDiagnostico());

        AlterarDiagnostico task = new AlterarDiagnostico();
        task.execute(requestHttp);

    }

    private class InserirDiagnostico extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(sucesso)
                Toast.makeText(view.getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();

        }
    }

    private class AlterarDiagnostico extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(sucesso)
                Toast.makeText(view.getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
        }
    }

}

