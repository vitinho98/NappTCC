package com.fatecourinhos.napp.model;

public class RelatorioFeedback {

    private String nomeAluno;
    private String opcao1;
    private String opcao2;
    private String data;

    public RelatorioFeedback(String nomeAluno, String opcao1, String opcao2, String data) {
        this.nomeAluno = nomeAluno;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.data = data;
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


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
