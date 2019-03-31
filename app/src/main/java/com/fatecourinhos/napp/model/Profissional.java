package com.fatecourinhos.napp.model;

public class Profissional {

    private int idProfissional;
    private String nomeProfissional;
    private String formacao;
    private String campoAtuacao;
    private String celularProfissional;
    private byte[] foto;
    private String emailProfissional;
    private UsuarioModel fkUsuario;

    public Profissional() {
    }

    public Profissional(int idProfissional, String nomeProfissional, String formacao, String campoAtuacao, String celularProfissional, byte[] foto, String emailProfissional, UsuarioModel fkUsuario) {
        this.idProfissional = idProfissional;
        this.nomeProfissional = nomeProfissional;
        this.formacao = formacao;
        this.campoAtuacao = campoAtuacao;
        this.celularProfissional = celularProfissional;
        this.foto = foto;
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

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getCampoAtuacao() {
        return campoAtuacao;
    }

    public void setCampoAtuacao(String campoAtuacao) {
        this.campoAtuacao = campoAtuacao;
    }

    public String getCelularProfissional() {
        return celularProfissional;
    }

    public void setCelularProfissional(String celularProfissional) {
        this.celularProfissional = celularProfissional;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getEmailProfissional() {
        return emailProfissional;
    }

    public void setEmailProfissional(String emailProfissional) {
        this.emailProfissional = emailProfissional;
    }

    public UsuarioModel getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioModel fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
