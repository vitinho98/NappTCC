package com.fatecourinhos.napp.view.consultaraluno;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.AtendimentoDetalheJSONParser;
import com.fatecourinhos.napp.model.AtendimentoDetalhe;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class VisualizarAtendimento extends AppCompatActivity {

    private TextView txtPrognostico, txtDiagnostico, txtEncaminhamento, txtObsAtendimento;
    private String conteudo, prognostico;
    private AtendimentoDetalhe atendimentoDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_atendimento);
        getComponentes();

        if (getIntent().getExtras() != null)
            selecionarDetalhesAtendimento(getIntent().getExtras().getInt("idAtendimento"));

    }

    private void getComponentes() {
        txtDiagnostico = findViewById(R.id.textViewDiagnostico);
        txtPrognostico = findViewById(R.id.textViewPrognostico);
        txtEncaminhamento = findViewById(R.id.textViewEncaminhamento);
        txtObsAtendimento = findViewById(R.id.textViewObsAtendimento);
    }

    private void selecionarDetalhesAtendimento(int id) {

        String uri = "http://vitorsilva.xyz/napp/atendimento/selecionarDetalhesAtendimento.php";
        RequestHttp requestHttp = new RequestHttp();
        SelecionarDetalhesAtendimento mytask = new SelecionarDetalhesAtendimento();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idAtendimento", String.valueOf(id));

        mytask.execute(requestHttp);

    }

    private class SelecionarDetalhesAtendimento extends AsyncTask<RequestHttp, String, AtendimentoDetalhe> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected AtendimentoDetalhe doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
                System.out.println(conteudo);
            } catch (Exception e) {
                conteudo = null;
            }

            atendimentoDetalhe = AtendimentoDetalheJSONParser.parseDados(conteudo);
            return atendimentoDetalhe;
        }

        @Override
        protected void onPostExecute(AtendimentoDetalhe atendimentoDetalhe) {
            super.onPostExecute(atendimentoDetalhe);

            if (!atendimentoDetalhe.getDiagnosticos().equals("null"))
                txtDiagnostico.setText("Diagnóstico(s): " + atendimentoDetalhe.getDiagnosticos());
            else
                txtDiagnostico.setText("Não houve diagnóstico nesse atendimento.");

            if (!atendimentoDetalhe.getProfsExternos().equals("null"))
                txtEncaminhamento.setText("Encaminhamento para: " + atendimentoDetalhe.getProfsExternos());
            else
                txtEncaminhamento.setText("Não houve encaminhamento nesse atendimento.");

            if (!atendimentoDetalhe.getObsAtendimento().equals("null"))
                txtObsAtendimento.setText("Observações do atendimento: " + atendimentoDetalhe.getObsAtendimento());
            else
                txtObsAtendimento.setText("Não foi adicionada nenhuma observação nesse atendimento.");

            if (atendimentoDetalhe.getFkPrognostico().getOpcao1() == 0)
                prognostico = "Prognóstico:\nAtendimento semanal para orientação. \n";

            if (atendimentoDetalhe.getFkPrognostico().getOpcao2() == 0)
                prognostico += "Atendimento quinzenal para orientação. \n";

            if (atendimentoDetalhe.getFkPrognostico().getOpcao3() == 0)
                prognostico += "Encaminhamento para serviço especializados. \n";

            if (atendimentoDetalhe.getFkPrognostico().getOpcao4() == 0)
                prognostico += "Contato com professores/administração. \n";

            if (atendimentoDetalhe.getFkPrognostico().getOpcao5() == 0)
                prognostico += "Contato com familiares. \n";

            if (!atendimentoDetalhe.getFkPrognostico().getObs().equals("null"))
                prognostico += "Observações do prognóstico: " + atendimentoDetalhe.getFkPrognostico().getObs();
            else
                prognostico += "Não foi adicionada nenhuma observação nesse prognóstico.";

            txtPrognostico.setText(prognostico);

        }

    }

}
