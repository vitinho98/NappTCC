package com.fatecourinhos.napp.model;

public class Agendamento {

    private int idAgendamento;
    private Aluno fkAluno;
    private LocalAtendimento fkLocalAtendimento;
    private Horario fkHorario;
    private String motivoAgendamento;
    private String motivoCancelamento;
    private String observacao;
    private String status;

    public Agendamento() {

    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public LocalAtendimento getFkLocalAtendimento() {
        return fkLocalAtendimento;
    }

    public void setFkLocalAtendimento(LocalAtendimento fkLocalAtendimento) {
        this.fkLocalAtendimento = fkLocalAtendimento;
    }

    public Horario getFkHorario() {
        return fkHorario;
    }

    public void setFkHorario(Horario fkHorario) {
        this.fkHorario = fkHorario;
    }

    public String getMotivoAgendamento() {
        return motivoAgendamento;
    }

    public void setMotivoAgendamento(String motivoAgendamento) {
        this.motivoAgendamento = motivoAgendamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
