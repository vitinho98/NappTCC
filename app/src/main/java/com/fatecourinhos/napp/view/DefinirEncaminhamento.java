package com.fatecourinhos.napp.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.ProfissionalExternoJSONParser;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;
import com.fatecourinhos.napp.view.fragments.ProfissionalExternoFragment;

import java.util.ArrayList;
import java.util.List;

public class DefinirEncaminhamento extends Activity {

    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private Button btnConfirmar;

    private CheckBox cb;
    private List<ProfissionalExterno> profissionaisExterno;
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

        selecionarProfissionaisExternos();
    }

    public void selecionarProfissionaisExternos() {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/selecionarProfExterno.php";
        SelecionarProfissionaisExternos mytask = new SelecionarProfissionaisExternos();

        mytask.execute(uri);

    }

    private class SelecionarProfissionaisExternos extends AsyncTask<String, String, List<ProfissionalExterno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<ProfissionalExterno> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            profissionaisExterno = ProfissionalExternoJSONParser.parseDados(conteudo);
            return profissionaisExterno;
        }

        @Override
        protected void onPostExecute(final List<ProfissionalExterno> profissionaisExternos) {
            super.onPostExecute(profissionaisExternos);

            for (ProfissionalExterno profissionalExterno : profissionaisExternos) {

                CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setText(profissionalExterno.getNomeProfissionalExterno());
                checkBox.setId(profissionalExterno.getIdProfissionalExterno());
                linearLayout.addView(checkBox);

            }

            progressBar.setVisibility(View.INVISIBLE);

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (ProfissionalExterno profissionalExterno : profissionaisExternos) {
                        cb = new CheckBox(getApplicationContext());
                        cb = findViewById(profissionalExterno.getIdProfissionalExterno());
                        if (cb.isChecked())
                            ids.add(cb.getId());
                    }

                    if (!ids.isEmpty())
                        definirEncaminhamento();

                }
            });

        }

    }

    private void definirEncaminhamento(){

        String uri = "http://vitorsilva.xyz/napp/atendimento/definirEncaminhamento.php";
        RequestHttp requestHttp = new RequestHttp();
        DefinirEncaminhamentoo task = new DefinirEncaminhamentoo();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAgendamento", String.valueOf(getIntent().getExtras().getInt("idAgendamento")));
        requestHttp.setParametro("ids", String.valueOf(ids));

        task.execute(requestHttp);

    }

    private class DefinirEncaminhamentoo extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

}
