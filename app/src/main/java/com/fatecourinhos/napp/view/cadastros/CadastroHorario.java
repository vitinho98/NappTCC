package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.fatecourinhos.napp.R;

public class CadastroHorario extends AppCompatActivity {

    AppCompatEditText editTextHorario;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horario);

        editTextHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(CadastroHorario.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextHorario.setText(hourOfDay + " : " + minute);
                    }
                }, 0, 0, true);

                timePickerDialog.show();

            }//fim metodo onclick sobreescrito
        });//fim metodo set onclick listener
    }
}
