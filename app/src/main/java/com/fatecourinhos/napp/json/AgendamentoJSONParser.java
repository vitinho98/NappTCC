package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AgendamentoModel;
import com.fatecourinhos.napp.model.AlunoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoJSONParser {

    public static List<AgendamentoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<AgendamentoModel> agendamento = new ArrayList<>();

            LocalAtendimentoModel localAtendimento = new LocalAtendimentoModel();
            AgendamentoModel objAgendamento = new AgendamentoModel();
            AlunoModel aluno = new AlunoModel();
            ProfissionalModel profissional = new ProfissionalModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                objAgendamento.setDataAgendamento(jsonObject.getString("dataAgendamento"));
                objAgendamento.setHoraAgendamento(jsonObject.getString("horaAgendamento"));
                objAgendamento.setDataRegistro(jsonObject.getString("dataRegistro"));
                objAgendamento.setHoraRegistro(jsonObject.getString("horaRegistro"));
                objAgendamento.setIdAgendamento(jsonObject.getInt("idAgendamento"));
                objAgendamento.setStatus(jsonObject.getInt("status"));
                objAgendamento.setObservacao(jsonObject.getString("observacao"));

                localAtendimento.setIdLocalAtendimento(jsonObject.getInt("idLocalAtendimento"));
                localAtendimento.setNomeBloco(jsonObject.getString("nomeBloco"));
                localAtendimento.setNomeLocal(jsonObject.getString("nomeLocal"));
                objAgendamento.setFkLocalAtendimento(localAtendimento);

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
                aluno.setEmpregado(jsonObject.getBoolean("empregado"));
                aluno.setSemestre(jsonObject.getInt("semestre"));
                aluno.setRa(jsonObject.getString("ra"));
                aluno.setEstadoCivil(jsonObject.getString("estadoCivil"));
                objAgendamento.setFkAluno(aluno);

                profissional.setIdProfissional(jsonObject.getInt("idProfissional"));
                profissional.setNomeProfissional(jsonObject.getString("nomeProfissional"));
                profissional.setCelularProfissional(jsonObject.getString("celProfissional"));
                profissional.setEmailProfissional(jsonObject.getString("emailProfissional"));
                objAgendamento.setFkProfissional(profissional);

                agendamento.add(objAgendamento);
            }

            return agendamento;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
