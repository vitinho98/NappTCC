package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroCampoAtuacao extends AppCompatDialogFragment {

    AppCompatEditText editTextNomeArea;
    CampoAtuacao campoAtuacao;
    boolean sucesso;

    public CadastroCampoAtuacao(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro_activity_area_atuacao, null);
        editTextNomeArea = getActivity().findViewById(R.id.edit_text_nome_area);

        builder.setView(view).setTitle("Campo de atuação");

        //caso seja clicado na lista
        if (getArguments() != null) {

            Bundle data = getArguments();

            editTextNomeArea.setText(data.getString("nomeCampoAtuacao"));
            campoAtuacao = new CampoAtuacao();
            campoAtuacao.setIdCampoAtuacao(data.getInt("idCampoAtuacao"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeArea.getText().toString().isEmpty())
                        Toast.makeText(getContext(),"Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();

                    else {
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeArea.getText().toString());
                        alterarCampoAtuacao(campoAtuacao, dialog);
                    }
                }
            });

        } else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextNomeArea.getText().toString().isEmpty())
                        Toast.makeText(getContext(), "Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();

                    else {

                        campoAtuacao = new CampoAtuacao();
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeArea.getText().toString());

                        if(CampoAtuacaoController.inserirCampoAtuacao(campoAtuacao)) {

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

    public void inserirCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/inserirCampoAtuacao.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        InserirCampoAtuacao task = new InserirCampoAtuacao();
        task.execute(requestHttp);

    }

    public void alterarCampoAtuacao(CampoAtuacao campoAtuacao, DialogInterface dialog) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/alterarCampoAtuacao.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idCampoAtuacao", String.valueOf(campoAtuacao.getIdCampoAtuacao()));
        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        AlterarCampoAtuacao task = new AlterarCampoAtuacao();
        task.execute(requestHttp);

    }

    private class InserirCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private class AlterarCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = HttpManager.getDados(params[0]);

            if(conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (sucesso) {
                Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else
                Toast.makeText(getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
        }
    }

}
