package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroCampoAtuacao extends AppCompatDialogFragment {

    //componente da tela
    private AppCompatEditText editTextNomeCampo;

    //variaveis gloabais
    private View view;
    private CampoAtuacao campoAtuacao;
    private String conteudo;
    private boolean sucesso;

    public CadastroCampoAtuacao() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cadastro_activity_area_atuacao, null);
        editTextNomeCampo = view.findViewById(R.id.edit_text_nome_area);
        builder.setView(view).setTitle("Campo de atuação");

        if (getArguments() != null) {

            Bundle data = getArguments();

            editTextNomeCampo.setText(data.getString("nomeCampoAtuacao"));
            campoAtuacao = new CampoAtuacao();
            campoAtuacao.setIdCampoAtuacao(data.getInt("idCampoAtuacao"));

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeCampo.getText().toString().isEmpty())
                        Toast.makeText(getContext(),"Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();

                    else {

                        campoAtuacao.setNomeCampoAtuacao(editTextNomeCampo.getText().toString());
                        alterarCampoAtuacao(campoAtuacao);

                    }
                }
            });

        } else {

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (editTextNomeCampo.getText().toString().isEmpty())
                        Toast.makeText(getContext(), "Insira o nome do campo de atuação", Toast.LENGTH_SHORT).show();

                    else {

                        campoAtuacao = new CampoAtuacao();
                        campoAtuacao.setNomeCampoAtuacao(editTextNomeCampo.getText().toString());
                        inserirCampoAtuacao(campoAtuacao);
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

    private void inserirCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/inserirCampoAtuacao.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirCampoAtuacao task = new InserirCampoAtuacao();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        task.execute(requestHttp);

    }

    private void alterarCampoAtuacao(CampoAtuacao campoAtuacao) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/alterarCampoAtuacao.php";
        RequestHttp requestHttp = new RequestHttp();
        AlterarCampoAtuacao task = new AlterarCampoAtuacao();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idCampoAtuacao", String.valueOf(campoAtuacao.getIdCampoAtuacao()));
        requestHttp.setParametro("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

        task.execute(requestHttp);

    }

    private class InserirCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            try {

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

            if (sucesso)
                Toast.makeText(view.getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }
    }

    private class AlterarCampoAtuacao extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = HttpManager.getDados(params[0]);

            try {

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

            if (sucesso)
                Toast.makeText(view.getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(view.getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();
        }
    }

}
