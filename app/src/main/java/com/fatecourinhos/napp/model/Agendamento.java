package com.fatecourinhos.napp.model;

public class Agendamento {

    private int idAgendamento;
    private Aluno fkAluno;
    private LocalAtendimento fkLocalAtendimento;
    private Horario fkHorario;

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

}
