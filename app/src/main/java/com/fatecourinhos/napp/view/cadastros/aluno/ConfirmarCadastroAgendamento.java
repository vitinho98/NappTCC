package com.fatecourinhos.napp.view.cadastros.aluno;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmarCadastroAgendamento extends AppCompatActivity {

    private Aluno aluno = new Aluno();
    private Profissional profissional = new Profissional();
    private Horario horario = new Horario();
    private Agendamento agendamento = new Agendamento();

    private TextView txtDataHora, txtNomeProfissional;
    private EditText editTextObs, editTextOutros;
    private Button btnConfirmar;
    private RadioGroup radioGroup;

    private SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM");
    private SimpleDateFormat horaFormater = new SimpleDateFormat("HH:mm");

    private boolean sucesso;
    private String conteudo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_cadastro_agendamento);
        getComponentes();

        if (getIntent().getExtras() != null) {

            aluno.setIdAluno(getIntent().getExtras().getInt("idAluno"));
            profissional.setNomeProfissional(getIntent().getExtras().getString("nomeProfissional"));

            Date d = new Date();
            d.setTime(getIntent().getExtras().getLong("dataHora", -1));

            horario.setData(d);
            horario.setIdHorarioProfissional(getIntent().getExtras().getInt("idHorario"));
            horario.setFkProfissional(profissional);

            agendamento.setFkAluno(aluno);
            agendamento.setFkHorario(horario);
            agendamento.setMotivoAgendamento(null);

            txtNomeProfissional.setText(profissional.getNomeProfissional());
            txtDataHora.setText(dataFormater.format(agendamento.getFkHorario().getData()) + " às " + horaFormater.format(agendamento.getFkHorario().getData()));

        }

    }

    private void getComponentes() {

        txtDataHora = findViewById(R.id.txt_data_hora);
        txtNomeProfissional = findViewById(R.id.txt_profissional);
        editTextObs = findViewById(R.id.edit_text_observacao);
        editTextOutros = findViewById(R.id.edit_text_outros);
        btnConfirmar = findViewById(R.id.btn_confirmar_agendamento);
        radioGroup = findViewById(R.id.radio_group_motivo);

        editTextOutros.setVisibility(EditText.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id = radioGroup.getCheckedRadioButtonId();

                switch (id) {
                    case R.id.radio_btn_psicologico :
                        agendamento.setMotivoAgendamento("Psicologico");
                        editTextOutros.setVisibility(EditText.INVISIBLE);
                        break;

                    case R.id.radio_btn_familiares :
                        agendamento.setMotivoAgendamento("Familiar");
                        editTextOutros.setVisibility(EditText.INVISIBLE);
                        break;

                    case R.id.radio_btn_pedagógico :
                        agendamento.setMotivoAgendamento("Pedagógico");
                        editTextOutros.setVisibility(EditText.INVISIBLE);
                        break;

                    case R.id.radio_btn_outros:
                        editTextOutros.setVisibility(EditText.VISIBLE);
                        break;

                }
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarForm())
                    cadastrarAgendamento(agendamento);
            }
        });

    }

    private boolean validarForm() {

        agendamento.setObservacao(editTextObs.getText().toString());

        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_btn_outros) {

            if (editTextOutros.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Descreva o motivo!", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                agendamento.setMotivoAgendamento(editTextOutros.getText().toString());
                return true;
            }

        } else if (agendamento.getMotivoAgendamento().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Selecione o motivo!", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            return true;
        }

    }

    private void cadastrarAgendamento(Agendamento agendamento) {

        String uri = "http://vitorsilva.xyz/napp/agendamento/cadastrarAgendamento.php";
        CadastrarAgendamento mytask = new CadastrarAgendamento();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setUrl(uri);
        requestHttp.setMetodo("GET");
        requestHttp.setParametro("idHorario", String.valueOf(agendamento.getFkHorario().getIdHorarioProfissional()));
        requestHttp.setParametro("idAluno", String.valueOf(agendamento.getFkAluno().getIdAluno()));
        requestHttp.setParametro("motivo", agendamento.getMotivoAgendamento());
        requestHttp.setParametro("observacao", agendamento.getObservacao());

        mytask.execute(requestHttp);

    }

    private class CadastrarAgendamento extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Agendamento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(),"Você já possui um agendamento com esse profissional!", Toast.LENGTH_SHORT).show();
        }

    }

}
