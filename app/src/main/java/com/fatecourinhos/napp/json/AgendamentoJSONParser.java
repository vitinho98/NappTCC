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

            LocalAtendimento localAtendimento = new LocalAtendimento();
            Agendamento agendamento = new Agendamento();
            Aluno aluno = new Aluno();
            Horario horario = new Horario();
            Profissional profissional = new Profissional();

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                localAtendimento.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                localAtendimento.setNomeBloco(jsonObject.getString("nomeBloco"));
                localAtendimento.setNomeLocal(jsonObject.getString("nomeLocal"));
                agendamento.setFkLocalAtendimento(localAtendimento);

                //-------------------------------------------------------------------------------------------------------------------------//

                aluno.setIdAluno(jsonObject.getInt("idAluno"));
                aluno.setNomeAluno(jsonObject.getString("nomeAluno"));
                aluno.setAnoEntrada(jsonObject.getString("anoEntrada"));
                aluno.setCelularAluno(jsonObject.getString("celularAluno"));
                aluno.setSexo(jsonObject.getString("sexo"));
                aluno.setCidadeAluno(jsonObject.getString("cidadeAluno"));
                aluno.setCpf(jsonObject.getString("cpf"));
                aluno.setDataNascimento(jsonObject.getString("dataNascimento"));
                aluno.setEmailAluno(jsonObject.getString("emailAluno"));
                aluno.setCurso(jsonObject.getString("curso"));
                aluno.setSemestre(jsonObject.getInt("semestre"));
                aluno.setRa(jsonObject.getString("ra"));
                aluno.setEstadoCivil(jsonObject.getString("estadoCivil"));
                agendamento.setFkAluno(aluno);

                //-------------------------------------------------------------------------------------------------------------------------//

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));

                String data = jsonObject.getString("dataHora");
                ano = data.substring(0,4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
                hora = data.substring(11,13);
                minuto = data.substring(14,16);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minuto));

                horario.setIdAgendaProfissional(jsonObject.getInt("idAgendaProfissional"));
                horario.setData(calendar.getTime());
                horario.setFkProfissional(profissional);
                agendamento.setFkHorario(horario);

                //-------------------------------------------------------------------------------------------------------------------------//

                agendamentos.add(agendamento);
            }

            return agendamentos;

        } catch (Exception e) {
            return null;
        }
    }

}
