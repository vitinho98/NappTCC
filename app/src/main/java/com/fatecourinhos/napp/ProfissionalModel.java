package com.fatecourinhos.napp;

public class ProfissionalModel {

    public ProfissionalModel(String nome, String status){

        this.nome=nome;
        this.status=status;

    }

    private String nome;
    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
