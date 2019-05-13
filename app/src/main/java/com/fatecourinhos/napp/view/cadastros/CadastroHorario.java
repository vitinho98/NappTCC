package com.fatecourinhos.napp.view.cadastros;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.AgendaProfissionalController;
import com.fatecourinhos.napp.model.AgendaProfissionalModel;
import com.fatecourinhos.napp.model.ProfissionalModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroHorario extends AppCompatDialogFragment {

    AppCompatEditText editTextHorario;
    Spinner spinnerDiasDaSemana;
    TimePickerDialog timePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.cadastro_activity_horario, null);
        editTextHorario = (AppCompatEditText)view.findViewById(R.id.edit_text_hora);
        spinnerDiasDaSemana = (Spinner)view.findViewById(R.id.spinnerDiaDaSemana);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.dia_semana_array, android.R.layout.simple_spinner_item);
        spinnerDiasDaSemana.setAdapter(adapter);

        builder.setView(view).setTitle("Horário de Atendimento");

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        if(getArguments() != null){

            Bundle data = getArguments();

            final AgendaProfissionalModel agendaProfissional = new AgendaProfissionalModel();
            agendaProfissional.setDiaDaSemana(data.getString("diaDaSemana"));
            agendaProfissional.setHora(data.getString("horario"));
            agendaProfissional.setMinutos(data.getString("minutos"));
            agendaProfissional.setIdAgendaProfissional(data.getInt("idAgendaProfissional"));

            ProfissionalModel profissionalModel = new ProfissionalModel();
            profissionalModel.setIdProfissional(data.getInt("idProfissional"));
            agendaProfissional.setFkProfissional(profissionalModel);

            int posicao = 0;

            if(agendaProfissional.getDiaDaSemana().equals("Segunda-feira"))
                posicao = 0;
            if(agendaProfissional.getDiaDaSemana().equals("Terça-feira"))
                posicao = 1;
            if(agendaProfissional.getDiaDaSemana().equals("Quarta-feira"))
                posicao = 2;
            if(agendaProfissional.getDiaDaSemana().equals("Quinta-feira"))
                posicao = 3;
            if(agendaProfissional.getDiaDaSemana().equals("Sexta-feira"))
                posicao = 4;
            if(agendaProfissional.getDiaDaSemana().equals("Sábado"))
                posicao = 5;

            spinnerDiasDaSemana.setSelection(posicao);

            editTextHorario.setText(agendaProfissional.getHora() + ":" + agendaProfissional.getMinutos());

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

            builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextHorario.getText().toString().isEmpty()){

                        Toast.makeText(getContext(),"Insira o horário", Toast.LENGTH_SHORT).show();

                    }else{

                        int posicao = editTextHorario.getText().toString().indexOf(":");

                        agendaProfissional.setDiaDaSemana((String) spinnerDiasDaSemana.getSelectedItem());
                        agendaProfissional.setHora(editTextHorario.getText().toString().substring(0, posicao));
                        agendaProfissional.setMinutos(editTextHorario.getText().toString().substring(posicao+1));

                        if(AgendaProfissionalController.alterarAgendaProfissional(agendaProfissional)){

                            Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }else

                            Toast.makeText(getContext(),"Erro ao alterar", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }else {

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

            builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(editTextHorario.getText().toString().isEmpty()){

                        Toast.makeText(getContext(),"Insira o horário", Toast.LENGTH_SHORT).show();

                    }else{

                        AgendaProfissionalModel agendaProfissionalModel = new AgendaProfissionalModel();

                        int posicao = editTextHorario.getText().toString().indexOf(":");

                        agendaProfissionalModel.setDiaDaSemana((String) spinnerDiasDaSemana.getSelectedItem());
                        agendaProfissionalModel.setHora(editTextHorario.getText().toString().substring(0, posicao));
                        agendaProfissionalModel.setMinutos(editTextHorario.getText().toString().substring(posicao+1));

                        if(AgendaProfissionalController.inserirAgendaProfissional(agendaProfissionalModel)){

                            Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }else

                            Toast.makeText(getContext(),"Erro ao cadastrar", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

        return builder.create();

    }//fim oncreate

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}//fim classe