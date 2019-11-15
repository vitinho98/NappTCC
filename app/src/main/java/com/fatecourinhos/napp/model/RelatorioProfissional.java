package com.fatecourinhos.napp.model;

public class RelatorioProfissional {

    private String nomeProfissional;
    private int quantidade;

    public RelatorioProfissional(String nomeProfissional, int quantidade) {
        this.nomeProfissional = nomeProfissional;
        this.quantidade = quantidade;
    }

    public RelatorioProfissional() {
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
