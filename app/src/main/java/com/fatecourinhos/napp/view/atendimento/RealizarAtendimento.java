package com.fatecourinhos.napp.view.atendimento;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class RealizarAtendimento extends AppCompatDialogFragment {

    private boolean sucesso;
    private String conteudo;

    private Bundle bundle;
    private Button btnDiagnostico, btnPrognostico, btnEncaminhamento, btnConfirmarAtendimento;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_atendimento, null);
        dialog.setView(view);

        bundle = new Bundle();
        bundle.putInt("idAgendamento", getArguments().getInt("idAgendamento"));

        btnConfirmarAtendimento = view.findViewById(R.id.btn_confirmar_atendimento);
        btnDiagnostico = view.findViewById(R.id.btn_diagnostico);
        btnPrognostico = view.findViewById(R.id.btn_prognostico);
        btnEncaminhamento = view.findViewById(R.id.btn_encaminhamento);

        btnDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DefinirDiagnostico.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnPrognostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DefinirPrognostico.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnEncaminhamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DefinirEncaminhamento.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnConfirmarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Confirmar atendimento");
                dialog.setMessage("Deseja confirmar o atendimento?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmarAtendimento(getArguments().getInt("idAgendamento"));
                    }
                });
                dialog.setNegativeButton("Não", null);

                dialog.show();
            }
        });

        return dialog.create();
    }

    private void confirmarAtendimento(int id){

        String uri = "http://vitorsilva.xyz/napp/atendimento/confirmarAtendimento.php";
        RequestHttp requestHttp = new RequestHttp();
        ConfirmarAtendimento task = new ConfirmarAtendimento();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(id));
        System.out.println(id);

        task.execute(requestHttp);

    }

    private class ConfirmarAtendimento extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);
                System.out.println(conteudo);
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
                Toast.makeText(view.getContext(), "Atendimento registrado!", Toast.LENGTH_SHORT).show();
                dismiss();
            } else
                Toast.makeText(view.getContext(),"Erro ao registrar o atendimento!", Toast.LENGTH_SHORT).show();
        }

    }

}
