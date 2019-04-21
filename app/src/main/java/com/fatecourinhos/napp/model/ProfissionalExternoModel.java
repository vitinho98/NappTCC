package com.fatecourinhos.napp.model;

public class ProfissionalExternoModel {

    private int idProfissionalExterno;
    private String nomeProfissionalExterno;
    private String cidadeProfissionalExterno;
    private String bairro;
    private String endereco;
    private String numero;
    private String emailProfissionalExterno;
    private String telefoneProfissionalExterno;
    private String celularProfissionalExterno;
    private CampoAtuacaoModel campoAtuacao;
    private ResponsavelModel fkResponsavel;

    public ProfissionalExternoModel() {
    }

    public ProfissionalExternoModel(int idProfissionalExterno, String nomeProfissionalExterno, String cidadeProfissionalExterno, String bairro, String endereco, String numero, String emailProfissionalExterno, String telefoneProfissionalExterno, String celularProfissionalExterno, CampoAtuacaoModel campoAtuacao, ResponsavelModel fkResponsavel) {
        this.idProfissionalExterno = idProfissionalExterno;
        this.nomeProfissionalExterno = nomeProfissionalExterno;
        this.cidadeProfissionalExterno = cidadeProfissionalExterno;
        this.bairro = bairro;
        this.endereco = endereco;
        this.numero = numero;
        this.emailProfissionalExterno = emailProfissionalExterno;
        this.telefoneProfissionalExterno = telefoneProfissionalExterno;
        this.celularProfissionalExterno = celularProfissionalExterno;
        this.campoAtuacao = campoAtuacao;
        this.fkResponsavel = fkResponsavel;
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

    public CampoAtuacaoModel getCampoAtuacao() {
        return campoAtuacao;
    }

    public void setCampoAtuacao(CampoAtuacaoModel campoAtuacao) {
        this.campoAtuacao = campoAtuacao;
    }

    public ResponsavelModel getFkResponsavel() {
        return fkResponsavel;
    }

    public void setFkResponsavel(ResponsavelModel fkResponsavel) {
        this.fkResponsavel = fkResponsavel;
    }
}
