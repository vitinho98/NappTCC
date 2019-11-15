package com.fatecourinhos.napp.model;

public class RelatorioFeedback {

    private String nomeAluno;
    private String opcao1;
    private String opcao2;

    public RelatorioFeedback(String nomeAluno, String opcao1, String opcao2) {
        this.nomeAluno = nomeAluno;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
    }

    public RelatorioFeedback() {
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }
}
