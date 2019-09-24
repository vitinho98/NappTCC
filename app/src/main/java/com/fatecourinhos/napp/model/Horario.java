package com.fatecourinhos.napp.model;

import java.util.Date;

public class Horario {

    private int idAgendaProfissional;
    private Date dataHora;
    private Profissional fkProfissional;

    public Horario() {

    }

    public int getIdAgendaProfissional() {
        return idAgendaProfissional;
    }

    public void setIdAgendaProfissional(int idAgendaProfissional) {
        this.idAgendaProfissional = idAgendaProfissional;
    }

    public Date getData() {
        return dataHora;
    }

    public void setData(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Profissional getFkProfissional() {
        return fkProfissional;
    }

    public void setFkProfissional(Profissional fkProfissional) {
        this.fkProfissional = fkProfissional;
    }

}
