package com.fatecourinhos.napp.model;

public class AgendaProfissionalModel {

    private int idAgendaProfissional;
    private String horario;
    private String diaDaSemana;
    private ProfissionalModel fkProfissional;

    public AgendaProfissionalModel() {
    }

    public AgendaProfissionalModel(int idAgendaProfissional, String horario, String diaDaSemana, ProfissionalModel fkProfissional) {
        this.idAgendaProfissional = idAgendaProfissional;
        this.horario = horario;
        this.diaDaSemana = diaDaSemana;
        this.fkProfissional = fkProfissional;
    }

    public int getIdAgendaProfissional() {
        return idAgendaProfissional;
    }

    public void setIdAgendaProfissional(int idAgendaProfissional) {
        this.idAgendaProfissional = idAgendaProfissional;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public ProfissionalModel getFkProfissional() {
        return fkProfissional;
    }

    public void setFkProfissional(ProfissionalModel fkProfissional) {
        this.fkProfissional = fkProfissional;
    }
}
