package com.fatecourinhos.napp.model;

public class PrognosticoModel {

    private int idPrognostico;
    private Aluno fkAluno;
    private int opcao1;
    private int opcao2;
    private int opcao3;
    private int opcao4;
    private int opcao5;
    private int opcao6;

    public PrognosticoModel() {
    }

    public PrognosticoModel(Aluno fkAluno, int opcao1, int opcao2, int opcao3, int opcao4, int opcao5, int opcao6) {
        this.fkAluno = fkAluno;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.opcao4 = opcao4;
        this.opcao5 = opcao5;
        this.opcao6 = opcao6;
    }

    public int getIdPrognostico() {
        return idPrognostico;
    }

    public void setIdPrognostico(int idPrognostico) {
        this.idPrognostico = idPrognostico;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public int getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(int opcao1) {
        this.opcao1 = opcao1;
    }

    public int getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(int opcao2) {
        this.opcao2 = opcao2;
    }

    public int getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(int opcao3) {
        this.opcao3 = opcao3;
    }

    public int getOpcao4() {
        return opcao4;
    }

    public void setOpcao4(int opcao4) {
        this.opcao4 = opcao4;
    }

    public int getOpcao5() {
        return opcao5;
    }

    public void setOpcao5(int opcao5) {
        this.opcao5 = opcao5;
    }

    public int getOpcao6() {
        return opcao6;
    }

    public void setOpcao6(int opcao6) {
        this.opcao6 = opcao6;
    }
}
