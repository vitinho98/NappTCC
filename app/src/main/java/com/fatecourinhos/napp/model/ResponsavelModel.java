package com.fatecourinhos.napp.model;

public class ResponsavelModel {

    private int idResponsavel;
    private String nomeResponsavel;
    private String celularResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;

    public ResponsavelModel() {
    }

    public ResponsavelModel(int idResponsavel, String nomeResponsavel, String celularResponsavel, String telefoneResponsavel, String emailResponsavel) {
        this.idResponsavel = idResponsavel;
        this.nomeResponsavel = nomeResponsavel;
        this.celularResponsavel = celularResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.emailResponsavel = emailResponsavel;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCelularResponsavel() {
        return celularResponsavel;
    }

    public void setCelularResponsavel(String celularResponsavel) {
        this.celularResponsavel = celularResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }
}
