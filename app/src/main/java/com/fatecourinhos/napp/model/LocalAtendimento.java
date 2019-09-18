package com.fatecourinhos.napp.model;

public class LocalAtendimento {

    private int idLocalAtendimento;
    private String nomeLocal;
    private String nomeBloco;
    private int status;

    public LocalAtendimento() {

    }

    public int getIdLocalAtendimento() {
        return idLocalAtendimento;
    }

    public void setIdLocalAtendimento(int idLocalAtendimento) {
        this.idLocalAtendimento = idLocalAtendimento;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getNomeBloco() {
        return nomeBloco;
    }

    public void setNomeBloco(String nomeBloco) {
        this.nomeBloco = nomeBloco;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
