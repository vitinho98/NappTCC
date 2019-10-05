package com.fatecourinhos.napp.model;

import java.util.Date;

public class Horario {

    private int idHorarioProfissional;
    private Date dataHora;
    private Profissional fkProfissional;

    public Horario() {

    }

    public int getIdHorarioProfissional() {
        return idHorarioProfissional;
    }

    public void setIdHorarioProfissional(int idHorarioProfissional) {
        this.idHorarioProfissional = idHorarioProfissional;
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
