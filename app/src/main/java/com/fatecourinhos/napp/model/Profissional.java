package com.fatecourinhos.napp.model;

public class Profissional {

    private int idProfissional;
    private String nomeProfissional;
    private CampoAtuacao fkCampoAtuacao;
    private String celularProfissional;
    private String emailProfissional;
    private Usuario fkUsuario;

    public Profissional() {
    }

    public Profissional(int idProfissional, String nomeProfissional, CampoAtuacao campoAtuacao, String celularProfissional, String emailProfissional, Usuario fkUsuario) {
        this.idProfissional = idProfissional;
        this.nomeProfissional = nomeProfissional;
        this.fkCampoAtuacao = campoAtuacao;
        this.celularProfissional = celularProfissional;
        this.emailProfissional = emailProfissional;
        this.fkUsuario = fkUsuario;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
}

    public CampoAtuacao getCampoAtuacao() {
        return fkCampoAtuacao;
    }

    public void setCampoAtuacao(CampoAtuacao campoAtuacao) {
        this.fkCampoAtuacao = campoAtuacao;
    }

    public String getCelularProfissional() {
        return celularProfissional;
    }

    public void setCelularProfissional(String celularProfissional) {
        this.celularProfissional = celularProfissional;
    }

    public String getEmailProfissional() {
        return emailProfissional;
    }

    public void setEmailProfissional(String emailProfissional) {
        this.emailProfissional = emailProfissional;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
