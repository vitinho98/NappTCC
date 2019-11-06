package com.fatecourinhos.napp.model;

import java.util.Date;

public class Atendimento {

    private int idAtendimento;
    private Date dataHora;
    private Agendamento fkAgendamento;

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Agendamento getFkAgendamento() {
        return fkAgendamento;
    }

    public void setFkAgendamento(Agendamento fkAgendamento) {
        this.fkAgendamento = fkAgendamento;
    }

}
