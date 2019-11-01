package com.fatecourinhos.napp.json;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fatecourinhos.napp.model.Agendamento;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Horario;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.model.Profissional;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoJSONParser {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Agendamento> parseDados(String content) {

        try {

            String dia, mes, ano, hora, minuto;

            JSONArray jsonArray = new JSONArray(content);
            List<Agendamento> agendamentos = new ArrayList<>();

            for (int i=0; i<jsonArray.length(); i++) {

                LocalAtendimento localAtendimento = new LocalAtendimento();
                Agendamento agendamento = new Agendamento();
                Aluno aluno = new Aluno();
                Horario horario = new Horario();
                Profissional profissional = new Profissional();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //-------------------------------------------------------------------------------------------------------------------------//

                try {

                    localAtendimento.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                    localAtendimento.setNomeBloco(jsonObject.getString("nomeBloco"));
                    localAtendimento.setNomeLocal(jsonObject.getString("nomeLocal"));
                    agendamento.setFkLocalAtendimento(localAtendimento);

                } catch (Exception e) {
                    localAtendimento.setIdLocalAtendimento(null);
                    localAtendimento.setNomeBloco(null);
                    localAtendimento.setNomeLocal(null);
                    agendamento.setFkLocalAtendimento(localAtendimento);
                }

                //-------------------------------------------------------------------------------------------------------------------------//

                aluno.setIdAluno(jsonObject.getInt("idAluno"));
                aluno.setNomeAluno(jsonObject.getString("nomeAluno"));
                agendamento.setFkAluno(aluno);

                //-------------------------------------------------------------------------------------------------------------------------//

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));

                //-------------------------------------------------------------------------------------------------------------------------//

                String data = jsonObject.getString("dataHora");
                ano = data.substring(0,4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
                hora = data.substring(11,13);
                minuto = data.substring(14,16);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minuto));

                horario.setIdHorarioProfissional(jsonObject.getInt("idHorarioProfissional"));
                horario.setData(calendar.getTime());
                horario.setFkProfissional(profissional);

                //-------------------------------------------------------------------------------------------------------------------------//

                agendamento.setFkHorario(horario);
                agendamento.setIdAgendamento(jsonObject.getInt("idAgendamento"));
                agendamento.setStatus(jsonObject.getString("status"));

                try {

                    agendamento.setMotivoAgendamento(jsonObject.getString("motivoAgendamento"));
                    agendamento.setMotivoCancelamento(jsonObject.getString("motivoCancelamento"));
                    agendamento.setObservacao(jsonObject.getString("observacao"));

                } catch (Exception e) {
                    System.out.println("tnc");
                }

                agendamentos.add(agendamento);
            }

            return agendamentos;

        } catch (Exception e) {
            return null;
        }
    }

}
