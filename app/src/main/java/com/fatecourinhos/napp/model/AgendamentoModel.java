package com.fatecourinhos.napp.model;

import java.util.Date;

public class AgendamentoModel {

    private int idAgendamento;
    private Date dataHoraAgendamento;
    private Date dataHoraRegistro;
    private String observacao;
    private boolean Status;
    private AlunoModel fkAluno;
    private ProfissionalModel fkProfissional;
    private LocalAtendimentoModel fkLocalAtendimento;

    public AgendamentoModel() {
    }

    public AgendamentoModel(int idAgendamento, Date dataHoraAgendamento, Date dataHoraRegistro, String observacao, boolean status, AlunoModel fkAluno, ProfissionalModel fkProfissional, LocalAtendimentoModel fkLocalAtendimento) {
        this.idAgendamento = idAgendamento;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.dataHoraRegistro = dataHoraRegistro;
        this.observacao = observacao;
        Status = status;
        this.fkAluno = fkAluno;
        this.fkProfissional = fkProfissional;
        this.fkLocalAtendimento = fkLocalAtendimento;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Date getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(Date dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public Date getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Date dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
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
}
