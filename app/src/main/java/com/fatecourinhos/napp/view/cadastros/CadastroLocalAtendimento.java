package com.fatecourinhos.napp.view.cadastros;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CadastroLocalAtendimento extends AppCompatDialogFragment {

    //componentes da tela
    private AppCompatEditText editTextNomeBloco;
    private AppCompatEditText editTextNomeLocal;

    //variaveis gloabais
    private View view;
    private LocalAtendimento localAtendimento;
    private String conteudo;
    private boolean sucesso;

    public CadastroLocalAtendimento() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cadastro_activity_local, null);
        editTextNomeBloco = view.findViewById(R.id.edit_text_nome_bloco);
        editTextNomeLocal = view.findViewById(R.id.edit_text_nome_sala);
        builder.setView(view).setTitle("Local de Atendimento");

        if (getArguments() != null) {

            Bundle data = getArguments();

            localAtendimento = new LocalAtendimento();
            localAtendimento.setIdLocalAtendimento(data.getInt("idLocalAtendimento"));
            editTextNomeBloco.setText(data.getString("nomeBloco"));
            editTextNomeLocal.setText(data.getString("nomeLocal"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeBloco.getText().toString().isEmpty() || editTextNomeLocal.getText().toString().isEmpty())
                        Toast.makeText(getContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();

                    else {

                        localAtendimento.setNomeBloco(editTextNomeBloco.getText().toString());
                        localAtendimento.setNomeLocal(editTextNomeLocal.getText().toString());
                        alterarLocalAtendimento(localAtendimento);

                    }
                }
            });

        } else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeBloco.getText().toString().isEmpty() || editTextNomeLocal.getText().toString().isEmpty())
                        Toast.makeText(getContext(), "Insira todos os campos", Toast.LENGTH_SHORT).show();

                    else {

                        localAtendimento = new LocalAtendimento();
                        localAtendimento.setNomeLocal(editTextNomeLocal.getText().toString());
                        localAtendimento.setNomeBloco(editTextNomeBloco.getText().toString());
                        inserirLocalAtendimento(localAtendimento);

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

    private void inserirLocalAtendimento(LocalAtendimento localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/inserirLocalAtendimento.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        InserirLocalAtendimento task = new InserirLocalAtendimento();
        task.execute(requestHttp);

    }

    private void alterarLocalAtendimento(LocalAtendimento localAtendimento) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/alterarLocalAtendimento.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idLocalAtendimento", String.valueOf(localAtendimento.getIdLocalAtendimento()));
        requestHttp.setParametro("nomeBloco", localAtendimento.getNomeBloco());
        requestHttp.setParametro("nomeLocal", localAtendimento.getNomeLocal());

        AlterarLocalAtendimento task = new AlterarLocalAtendimento();
        task.execute(requestHttp);

    }

    private class InserirLocalAtendimento extends AsyncTask<RequestHttp, String, String> {

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

            if (sucesso)
                Toast.makeText(view.getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();

        }
    }

    private class AlterarLocalAtendimento extends AsyncTask<RequestHttp, String, String> {
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

            if (sucesso)
                Toast.makeText(view.getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();

        }
    }

}
