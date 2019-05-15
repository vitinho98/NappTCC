package com.fatecourinhos.napp.model;

public class AgendamentoModel {

    private int idAgendamento;
    private AlunoModel fkAluno;
    private ProfissionalModel fkProfissional;
    private LocalAtendimentoModel fkLocalAtendimento;
    private String dataRegistro;
    private String horaRegistro;
    private String dataAgendamento;
    private String horaAgendamento;
    private String observacao;
    private int status;

    public AgendamentoModel() {
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public AlunoModel getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(AlunoModel fkAluno) {
        this.fkAluno = fkAluno;
    }

    public ProfissionalModel getFkProfissional() {
        return fkProfissional;
    }

    public void setFkProfissional(ProfissionalModel fkProfissional) {
        this.fkProfissional = fkProfissional;
    }

    public LocalAtendimentoModel getFkLocalAtendimento() {
        return fkLocalAtendimento;
    }

    public void setFkLocalAtendimento(LocalAtendimentoModel fkLocalAtendimento) {
        this.fkLocalAtendimento = fkLocalAtendimento;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
