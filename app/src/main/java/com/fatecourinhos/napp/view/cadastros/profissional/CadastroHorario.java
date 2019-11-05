package com.fatecourinhos.napp.view.cadastros.profissional;

import androidx.annotation.NonNull;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroHorario extends AppCompatActivity {

    //variaveis globais
    private SharedPreferences preferences;
    private Profissional profissional;
    private Horario horario;
    private String conteudo;
    private Calendar calendar;

    //componentes da tela
    private Button btnCadastrar;
    private CalendarView calendario;
    private AppCompatEditText editTextHorario;
    private TimePickerDialog timePickerDialog;

    //formatadores de data
    private SimpleDateFormat formatadorDataHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horario);
        getComponentes();
        setOnClicks();
    }

    private void getComponentes() {

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        formatadorDataHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        horario = new Horario();
        profissional = new Profissional();

        editTextHorario = findViewById(R.id.edit_text_hora);
        btnCadastrar = findViewById(R.id.btn_salvar_horario);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);

        calendario = findViewById(R.id.calendarView);
        calendario.setMinDate(System.currentTimeMillis());

    }

    private void setOnClicks() {

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (!editTextHorario.getText().toString().isEmpty()) {

                    if (preferences.contains("idProfissional"))
                        profissional.setIdProfissional(preferences.getInt("idProfissional", 0));

                    horario.setFkProfissional(profissional);
                    horario.setData(calendar.getTime());

                    inserirHorarioProfissional(horario);

                } else
                    Toast.makeText(getApplicationContext(), "Insira o horário!", Toast.LENGTH_LONG).show();
            }
        });

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
            }
        });

        editTextHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(CadastroHorario.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        editTextHorario.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                    }
                }, 0, 0, true);

                timePickerDialog.show();

            }
        });

    }

    private void inserirHorarioProfissional(Horario agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/horarioProfissional/inserirHorarioProfissional.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirHorarioProfissional task = new InserirHorarioProfissional();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("data", formatadorDataHora.format(agendaProfissional.getData()));
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        task.execute(requestHttp);

    }

    private class InserirHorarioProfissional extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (conteudo.contains("Sucesso")) {
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else if (conteudo.contains("Cadastrado"))
                Toast.makeText(getApplicationContext(), "Horário já cadatrado!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
        }

    }

}
