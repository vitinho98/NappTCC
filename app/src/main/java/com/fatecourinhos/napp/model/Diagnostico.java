package com.fatecourinhos.napp.model;

public class Diagnostico {

    private int idDiagostico;
    private String nomeDiagnotico;

    public Diagnostico(int idDiagostico, String nomeDiagostico) {
        this.idDiagostico = idDiagostico;
        this.nomeDiagnotico=nomeDiagostico;
    }

    public Diagnostico() {

    }

    public int getIdDiagostico() {
        return idDiagostico;
    }

    public void setIdDiagostico(int idDiagostico) {
        this.idDiagostico = idDiagostico;
    }

    public String getNomeDiagnotico() {
        return nomeDiagnotico;
    }

    public void setNomeDiagnotico(String nomeDiagnotico) {
        this.nomeDiagnotico = nomeDiagnotico;
    }
}
