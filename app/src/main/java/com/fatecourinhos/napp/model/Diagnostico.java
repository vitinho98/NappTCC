package com.fatecourinhos.napp.model;

public class Diagnostico {

    private int idDiagnostico;
    private String nomeDiagnostico;
    private int status;

    public Diagnostico() {

    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getNomeDiagnostico() {
        return nomeDiagnostico;
    }

    public void setNomeDiagnostico(String nomeDiagnostico) {
        this.nomeDiagnostico = nomeDiagnostico;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
