package com.fatecourinhos.napp.model;

public class ProfissionalExterno {

    private int idProfissionalExterno;
    private String nomeProfissionalExterno;
    private String cidadeProfissionalExterno;
    private String bairro;
    private String endereco;
    private String numero;
    private String emailProfissionalExterno;
    private String telefoneProfissionalExterno;
    private String celularProfissionalExterno;
    private CampoAtuacao fkCampoAtuacao;
    private Responsavel fkResponsavel;
    private int status;

    public ProfissionalExterno() {

    }

    public int getIdProfissionalExterno() {
        return idProfissionalExterno;
    }

    public void setIdProfissionalExterno(int idProfissionalExterno) {
        this.idProfissionalExterno = idProfissionalExterno;
    }

    public String getNomeProfissionalExterno() {
        return nomeProfissionalExterno;
    }

    public void setNomeProfissionalExterno(String nomeProfissionalExterno) {
        this.nomeProfissionalExterno = nomeProfissionalExterno;
    }

    public String getCidadeProfissionalExterno() {
        return cidadeProfissionalExterno;
    }

    public void setCidadeProfissionalExterno(String cidadeProfissionalExterno) {
        this.cidadeProfissionalExterno = cidadeProfissionalExterno;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmailProfissionalExterno() {
        return emailProfissionalExterno;
    }

    public void setEmailProfissionalExterno(String emailProfissionalExterno) {
        this.emailProfissionalExterno = emailProfissionalExterno;
    }

    public String getTelefoneProfissionalExterno() {
        return telefoneProfissionalExterno;
    }

    public void setTelefoneProfissionalExterno(String telefoneProfissionalExterno) {
        this.telefoneProfissionalExterno = telefoneProfissionalExterno;
    }

    public String getCelularProfissionalExterno() {
        return celularProfissionalExterno;
    }

    public void setCelularProfissionalExterno(String celularProfissionalExterno) {
        this.celularProfissionalExterno = celularProfissionalExterno;
    }

    public CampoAtuacao getFkCampoAtuacao() {
        return fkCampoAtuacao;
    }

    public void setFkCampoAtuacao(CampoAtuacao campoAtuacao) {
        this.fkCampoAtuacao = campoAtuacao;
    }

    public Responsavel getFkResponsavel() {
        return fkResponsavel;
    }

    public void setFkResponsavel(Responsavel fkResponsavel) {
        this.fkResponsavel = fkResponsavel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
