package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.fatecourinhos.napp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroHorario extends AppCompatDialogFragment {

    AppCompatEditText editTextHorario;
    TimePickerDialog timePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.cadastro_activity_horario, null);

        builder.setView(view).setTitle("Horário de Atendimento");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        editTextHorario = (AppCompatEditText)view.findViewById(R.id.edit_text_hora);

        editTextHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(getActivity(), new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextHorario.setText(hourOfDay + " : " + minute);
                    }
                }, 0, 0, true);

                timePickerDialog.show();

            }//fim metodo onclick sobreescrito
        });//fim metodo set onclick listener


        return builder.create();

    }//fim oncreate

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}//fim classe
