package com.fatecourinhos.napp.view;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.LocalAtendimentoJSONParser;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.ArrayList;
import java.util.List;

public class DefinirLocalAtendimento extends AppCompatDialogFragment {

    private ArrayAdapter<String> adapterLocal;
    private List<String> nomesLocais = new ArrayList<>();
    private List<LocalAtendimento> locaisAtendimento = new ArrayList<>();

    private boolean sucesso;
    private String conteudo;
    private Spinner spinner;
    private Button btnConfirmar;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.denifir_local, null);
        dialog.setView(view);

        btnConfirmar = view.findViewById(R.id.btn_confirmar_local_atendimento);
        spinner = view.findViewById(R.id.spinnerLocalAtendimento);

        selecionarLocalAtendimento();
        return dialog.create();
    }

    private void selecionarLocalAtendimento() {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/selecionarLocaisAtendimento.php";
        SelecionarLocaisAtendimento task = new SelecionarLocaisAtendimento();

        task.execute(uri);

    }

    private class SelecionarLocaisAtendimento extends AsyncTask<String, String, List<LocalAtendimento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LocalAtendimento> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            locaisAtendimento = LocalAtendimentoJSONParser.parseDados(conteudo);
            return locaisAtendimento;
        }

        @Override
        protected void onPostExecute(final List<LocalAtendimento> locaisAtendimento) {
            super.onPostExecute(locaisAtendimento);

            for (LocalAtendimento localAtendimento : locaisAtendimento)
                nomesLocais.add(localAtendimento.getNomeLocal());

            adapterLocal = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, nomesLocais);
            spinner.setAdapter(adapterLocal);

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (LocalAtendimento localAtendimento : locaisAtendimento)
                        if (spinner.getSelectedItem().equals(localAtendimento.getNomeLocal())) {
                            definirLocalAtendimento(localAtendimento.getIdLocalAtendimento());
                            break;
                        }
                }
            });

        }

    }

    private void definirLocalAtendimento(int idLocal) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/definirLocalAtendimento.php";
        RequestHttp requestHttp = new RequestHttp();
        DefinirLocalAtendimentoo task = new DefinirLocalAtendimentoo();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(getArguments().getInt("idAgendamento")));
        requestHttp.setParametro("idLocal", String.valueOf(idLocal));

        task.execute(requestHttp);

    }

    private class DefinirLocalAtendimentoo extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(view.getContext(), "Local de atendimento definido!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(view.getContext(), "Erro ao definir o local de atendimento!", Toast.LENGTH_SHORT).show();
        }

    }

}