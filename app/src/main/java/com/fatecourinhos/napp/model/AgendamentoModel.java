package com.fatecourinhos.napp.model;

import java.util.Date;

public class AgendamentoModel {

    private int idAgendamento;
    private String dataAgendamento;
    private String horaAgendamento;
    private String dataRegistro;
    private String horaRegistro;
    private String observacao;
    private boolean status;
    private AlunoModel fkAluno;
    private ProfissionalModel fkProfissional;
    private LocalAtendimentoModel fkLocalAtendimento;

    public AgendamentoModel() {
    }

    public AgendamentoModel(int idAgendamento, String dataAgendamento, String horaAgendamento, String dataRegistro, String horaRegistro, String observacao, boolean status, AlunoModel fkAluno, ProfissionalModel fkProfissional, LocalAtendimentoModel fkLocalAtendimento) {
        this.idAgendamento = idAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.dataRegistro = dataRegistro;
        this.horaRegistro = horaRegistro;
        this.observacao = observacao;
        this.status = status;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        status = status;
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
