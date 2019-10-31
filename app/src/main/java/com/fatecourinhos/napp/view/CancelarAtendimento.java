package com.fatecourinhos.napp.view;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class CancelarAtendimento extends AppCompatDialogFragment {

    private EditText editTextMotivoCancelamento;
    private Button btnConfirmar;
    private View view;
    private boolean sucesso;
    private String conteudo;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cancelar_atendimento, null);
        dialog.setView(view);

        btnConfirmar = view.findViewById(R.id.btn_confirmar_cancelamento);
        editTextMotivoCancelamento = view.findViewById(R.id.edit_text_motivo_cancelamento);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextMotivoCancelamento.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Insira o motivo do cancelamento!", Toast.LENGTH_SHORT).show();
                else
                    cancelarAtendimento(editTextMotivoCancelamento.getText().toString());

            }
        });

        return dialog.create();
    }

    private void cancelarAtendimento(String motivo) {

        String uri = "http://vitorsilva.xyz/napp/usuario/alterarSenha.php";
        RequestHttp requestHttp = new RequestHttp();
        CancelarAtendimentoo task = new CancelarAtendimentoo();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(getArguments().getInt("idAgendamento")));
        requestHttp.setParametro("motivoCancelamento", motivo);

        task.execute(requestHttp);

    }

    private class CancelarAtendimentoo extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(view.getContext(), "Atendimento cancelado!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(view.getContext(), "Erro ao cancelar o atendimento!", Toast.LENGTH_SHORT).show();
        }

    }

}
