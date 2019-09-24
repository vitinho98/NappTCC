package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroHorario extends AppCompatActivity {

    //variaveis globais
    private SharedPreferences preferences;
    private Profissional profissional;
    private Horario agendaProfissional;
    private String conteudo;
    private boolean sucesso;

    //componentes da tela
    private Button btnCadastrar;
    private CalendarView calendario;
    private AppCompatEditText editTextHorario;
    private TimePickerDialog timePickerDialog;

    //formatadores de data
    private SimpleDateFormat formatDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horario);
        getComponentes();

        if (getIntent().getExtras() != null) {

            agendaProfissional.setData((Date) getIntent().getExtras().get("dataHora"));
            calendario.setDate(agendaProfissional.getData().getTime());
            editTextHorario.setText(formatHora.format(agendaProfissional.getData()));

        } else {


        }

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (preferences.contains("idProfissional"))
                    profissional.setIdProfissional(preferences.getInt("idProfissional", 0));

                String data = formatData.format(new Date(calendario.getDate()));
                String hora = editTextHorario.getText().toString();

                try {

                    agendaProfissional.setFkProfissional(profissional);
                    agendaProfissional.setData(formatDataHora.parse(data + " " + hora));
                    System.out.println(formatDataHora.format(agendaProfissional.getData()));

                } catch (ParseException e) {
                    agendaProfissional.setData(null);
                }

                inserirAgendaProfissional(agendaProfissional);

            }
        });

        editTextHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(CadastroHorario.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextHorario.setText(String.format("%02d",hourOfDay) + ":" + String.format("%02d", minute));
                    }
                }, 23, 06, true);

                timePickerDialog.show();

            }
        });

    }

    private void getComponentes() {

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);

        editTextHorario = findViewById(R.id.edit_text_hora);
        btnCadastrar = findViewById(R.id.btn_salvar_horario);

        calendario = findViewById(R.id.calendarView);
        calendario.setMinDate(System.currentTimeMillis());

        agendaProfissional = new Horario();
        profissional = new Profissional();

    }

    public void inserirAgendaProfissional(Horario agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/inserirAgendaProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirAgendaProfissional task = new InserirAgendaProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("data", formatDataHora.format(agendaProfissional.getData()));
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        task.execute(requestHttp);

    }

    private class InserirAgendaProfissional extends AsyncTask<RequestHttp, String, String> {

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
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
        }

    }

}
