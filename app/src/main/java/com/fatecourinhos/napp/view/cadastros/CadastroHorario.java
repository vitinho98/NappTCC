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
import com.fatecourinhos.napp.model.AgendaProfissional;
import com.fatecourinhos.napp.model.Profissional;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroHorario extends AppCompatActivity {

    SharedPreferences preferences;
    AgendaProfissional agendaProfissional;
    String conteudo;
    boolean sucesso;
    Button btnCadastrar;
    int idProfissional;
    Profissional profissional;
    CalendarView calendario;
    AppCompatEditText editTextHorario;
    TimePickerDialog timePickerDialog;
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horario);

        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        editTextHorario = findViewById(R.id.edit_text_hora);
        btnCadastrar = findViewById(R.id.btn_salvar_horario);
        calendario = findViewById(R.id.calendarView);
        calendario.setMinDate(System.currentTimeMillis());
        agendaProfissional = new AgendaProfissional();

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(preferences.contains("idProfissional"))
                   idProfissional = preferences.getInt("idProfissional", 0);

                profissional.setIdProfissional(idProfissional);

                agendaProfissional.setFkProfissional(profissional);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String data = simpleDateFormat.format(new Date(calendario.getDate()));
                String hora = editTextHorario.getText().toString();

                try {

                    agendaProfissional.setData(simpleDateFormat1.parse(data + " " + hora));
                    System.out.println(simpleDateFormat1.format(agendaProfissional.getData()));

                } catch (ParseException e) {
                    e.printStackTrace();
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
                }, 0, 0, true);

                timePickerDialog.show();

            }
        });

    }

    public void inserirAgendaProfissional(AgendaProfissional agendaProfissional) {

        String uri = "http://vitorsilva.xyz/napp/agendaProfissional/inserirAgendaProfissional.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("data", simpleDateFormat1.format(agendaProfissional.getData()));
        requestHttp.setParametro("idProfissional", String.valueOf(agendaProfissional.getFkProfissional().getIdProfissional()));

        InserirAgendaProfissional task = new InserirAgendaProfissional();
        task.execute(requestHttp);

    }

    private class InserirAgendaProfissional extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            conteudo = HttpManager.getDados(params[0]);

            if (conteudo.contains("Sucesso"))
                sucesso = true;
            else
                sucesso = false;

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(sucesso)
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
        }
    }
}
