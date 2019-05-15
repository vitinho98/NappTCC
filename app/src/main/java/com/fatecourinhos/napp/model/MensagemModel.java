package com.fatecourinhos.napp.model;

public class MensagemModel {

    private int idMensagem;
    private AlunoModel fkAluno;
    private ProfissionalExternoModel fkProfissionalExterno;
    private String mensagem;
    private String data;
    private String hora;

    public MensagemModel() {
    }

    public MensagemModel(AlunoModel fkAluno, ProfissionalExternoModel fkProfissionalExterno, String mensagem, String data, String hora) {
        this.fkAluno = fkAluno;
        this.fkProfissionalExterno = fkProfissionalExterno;
        this.mensagem = mensagem;
        this.data = data;
        this.hora = hora;
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public AlunoModel getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(AlunoModel fkAluno) {
        this.fkAluno = fkAluno;
    }

    public ProfissionalExternoModel getFkProfissionalExterno() {
        return fkProfissionalExterno;
    }

    public void setFkProfissionalExterno(ProfissionalExternoModel fkProfissionalExterno) {
        this.fkProfissionalExterno = fkProfissionalExterno;
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
