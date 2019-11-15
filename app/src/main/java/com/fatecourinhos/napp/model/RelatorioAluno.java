package com.fatecourinhos.napp.model;

public class RelatorioAluno {

    private String nomeAluno;
    private int quantidade;

    public RelatorioAluno(String nomeAluno, int quantidade) {
        this.nomeAluno = nomeAluno;
        this.quantidade = quantidade;
    }

    public RelatorioAluno() {
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
