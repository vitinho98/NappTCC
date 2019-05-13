package com.fatecourinhos.napp.model;

public class AgendaProfissionalModel {

    private int idAgendaProfissional;
    private String hora;
    private String minutos;
    private String diaDaSemana;
    private ProfissionalModel fkProfissional;

    public AgendaProfissionalModel() {
    }

    public AgendaProfissionalModel(int idAgendaProfissional, String hora, String minutos, String diaDaSemana, ProfissionalModel fkProfissional) {
        this.idAgendaProfissional = idAgendaProfissional;
        this.hora = hora;
        this.minutos = minutos;
        this.diaDaSemana = diaDaSemana;
        this.fkProfissional = fkProfissional;
    }

    public int getIdAgendaProfissional() {
        return idAgendaProfissional;
    }

    public void setIdAgendaProfissional(int idAgendaProfissional) {
        this.idAgendaProfissional = idAgendaProfissional;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
