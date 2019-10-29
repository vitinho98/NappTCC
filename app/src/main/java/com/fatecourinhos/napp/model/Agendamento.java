package com.fatecourinhos.napp.model;

public class Agendamento {

    private int idAgendamento;
    private Aluno fkAluno;
    private LocalAtendimento fkLocalAtendimento;
    private Horario fkHorario;
    private String motivo;
    private String observacao;

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
