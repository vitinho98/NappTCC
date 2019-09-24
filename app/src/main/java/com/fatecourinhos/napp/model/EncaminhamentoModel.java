package com.fatecourinhos.napp.model;

public class EncaminhamentoModel {

    private int idEncaminhamento;
    private Agendamento fkAgendamento;
    private ProfissionalExterno fkProfissionalExterno;
    private String data;
    private String hora;

    public EncaminhamentoModel() {
    }

    public int getIdEncaminhamento() {
        return idEncaminhamento;
    }

    public void setIdEncaminhamento(int idEncaminhamento) {
        this.idEncaminhamento = idEncaminhamento;
    }

    public Agendamento getFkAgendamento() {
        return fkAgendamento;
    }

    public void setFkAgendamento(Agendamento fkAgendamento) {
        this.fkAgendamento = fkAgendamento;
    }

    public ProfissionalExterno getFkProfissionalExterno() {
        return fkProfissionalExterno;
    }

    public void setFkProfissionalExterno(ProfissionalExterno fkProfissionalExterno) {
        this.fkProfissionalExterno = fkProfissionalExterno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
