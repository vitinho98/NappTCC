package com.fatecourinhos.napp.model;

public class Prognostico {

    private int idPrognostico;
    private String obs;
    private Atendimento fkAtendimento;
    private int opcao1;
    private int opcao2;
    private int opcao3;
    private int opcao4;
    private int opcao5;

    public Prognostico() {
    }

    public int getIdPrognostico() {
        return idPrognostico;
    }

    public void setIdPrognostico(int idPrognostico) {
        this.idPrognostico = idPrognostico;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Atendimento getFkAtendimento() {
        return fkAtendimento;
    }

    public void setFkAtendimento(Atendimento fkAtendimento) {
        this.fkAtendimento = fkAtendimento;
    }

}
