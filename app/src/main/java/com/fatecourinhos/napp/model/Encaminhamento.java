package com.fatecourinhos.napp.model;

public class Encaminhamento {

    private int idEncaminhamento;
    private Aluno fkAluno;
    private Profissional fkProfissional;
    private ProfissionalExterno fkProfissionalExterno;

    public Encaminhamento() {
    }

    public int getIdEncaminhamento() {
        return idEncaminhamento;
    }

    public void setIdEncaminhamento(int idEncaminhamento) {
        this.idEncaminhamento = idEncaminhamento;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Profissional getFkProfissional() {
        return fkProfissional;
    }

    public void setFkProfissional(Profissional fkProfissional) {
        this.fkProfissional = fkProfissional;
    }

    public ProfissionalExterno getFkProfissionalExterno() {
        return fkProfissionalExterno;
    }

    public void setFkProfissionalExterno(ProfissionalExterno fkProfissionalExterno) {
        this.fkProfissionalExterno = fkProfissionalExterno;
    }

}
