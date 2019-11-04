package com.fatecourinhos.napp.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.DiagnosticoJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.DiagnosticoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroMensagem2;
import com.fatecourinhos.napp.view.fragments.DiagnosticoFragment;

import java.util.ArrayList;
import java.util.List;

public class DefinirDiagnostico extends Activity {

    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private Button btnConfirmar;

    private CheckBox cb;
    private List<Diagnostico> diagnosticos;
    private List<Integer> ids = new ArrayList<>();

    private boolean sucesso;
    private View view;
    private String conteudo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.definir_diagnostico);

        progressBar = findViewById(R.id.progressBarDefinirDiagnostico);
        linearLayout = findViewById(R.id.layout_diagnosticos);
        btnConfirmar = findViewById(R.id.btn_confirmar_diagnostico);

        selecionarDiagnosticos();
    }

    private void selecionarDiagnosticos() {

        String uri = "http://vitorsilva.xyz/napp/diagnostico/selecionarDiagnosticos.php";
        SelecionarDiagnosticos task = new SelecionarDiagnosticos();

        task.execute(uri);

    }

    private class SelecionarDiagnosticos extends AsyncTask<String, String, List<Diagnostico>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Diagnostico> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            diagnosticos = DiagnosticoJSONParser.parseDados(conteudo);
            return diagnosticos;
        }

        @Override
        protected void onPostExecute(final List<Diagnostico> diagnosticos) {
            super.onPostExecute(diagnosticos);

            for (Diagnostico diagnostico : diagnosticos) {

                CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setText(diagnostico.getNomeDiagnostico());
                checkBox.setId(diagnostico.getIdDiagnostico());
                linearLayout.addView(checkBox);

            }

            progressBar.setVisibility(ProgressBar.INVISIBLE);

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ids.clear();

                    for (Diagnostico diagnostico : diagnosticos) {

                        cb = new CheckBox(getApplicationContext());
                        cb = findViewById(diagnostico.getIdDiagnostico());
                        if (cb.isChecked())
                            ids.add(cb.getId());
                    }

                    if (!ids.isEmpty())
                        definirDiagnostico();

                }
            });
        }

    }

    private void definirDiagnostico(){

        //String uri = "http://vitorsilva.xyz/napp/atendimento/inserirDiagnostico.php";
        String uri = "http://vitorsilva.xyz/napp/teste.php";
        RequestHttp requestHttp = new RequestHttp();
        DefinirDiagnosticoo task = new DefinirDiagnosticoo();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(getIntent().getExtras().getInt("idAgendamento")));
        requestHttp.setParametro("ids", String.valueOf(ids));
        System.out.println(String.valueOf(ids));

        task.execute(requestHttp);

    }

    private class DefinirDiagnosticoo extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

}
