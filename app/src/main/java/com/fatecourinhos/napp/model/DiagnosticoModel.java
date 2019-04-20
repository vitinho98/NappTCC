package com.fatecourinhos.napp.model;

public class DiagnosticoModel {

    private int idDiagostico;
    private String nomeDiagnotico;

    public DiagnosticoModel(int idDiagostico, String nomeDiagostico) {
        this.idDiagostico = idDiagostico;
        this.nomeDiagnotico=nomeDiagostico;
    }

    public  DiagnosticoModel(){
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
