package com.fatecourinhos.napp.model;

public class MensagemModel {

    private int idMensagem;
    private Aluno fkAluno;
    private Profissional fkProfissional;
    private String mensagem;
    private String data;
    private String hora;

    public MensagemModel() {
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
