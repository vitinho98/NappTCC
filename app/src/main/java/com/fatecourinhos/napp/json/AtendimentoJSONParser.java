package com.fatecourinhos.napp.json;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Atendimento;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.model.Profissional;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoJSONParser {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Atendimento> parseDados(String content) {

        try {

            String dia, mes, ano, hora, minuto;

            JSONArray jsonArray = new JSONArray(content);
            List<Atendimento> atendimentos = new ArrayList<>();

            for (int i=0; i<jsonArray.length(); i++) {

                Atendimento atendimento = new Atendimento();
                Agendamento agendamento = new Agendamento();
                Aluno aluno = new Aluno();
                Horario horario = new Horario();
                Profissional profissional = new Profissional();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //-------------------------------------------------------------------------------------------------------------------------//

                aluno.setNomeAluno(jsonObject.getString("nomeAluno"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                horario.setFkProfissional(profissional);

                //-------------------------------------------------------------------------------------------------------------------------//

                agendamento.setFkAluno(aluno);
                agendamento.setFkHorario(horario);

                //-------------------------------------------------------------------------------------------------------------------------//

                String data = jsonObject.getString("dataHora");
                ano = data.substring(0,4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
                hora = data.substring(11,13);
                minuto = data.substring(14,16);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minuto));

                atendimento.setIdAtendimento(jsonObject.getInt("idAtendimento"));
                atendimento.setDataHora(calendar.getTime());
                atendimento.setFkAgendamento(agendamento);

                //-------------------------------------------------------------------------------------------------------------------------//

                atendimentos.add(atendimento);
            }

            return atendimentos;

        } catch (Exception e) {
            return null;
        }

    }

}
