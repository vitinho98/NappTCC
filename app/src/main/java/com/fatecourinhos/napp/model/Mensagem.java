package com.fatecourinhos.napp.model;

import java.util.Date;
import java.util.List;

public class Mensagem {

    private int idMensagem;
    private List<Aluno> fkAluno;
    private Profissional fkProfissional;
    private String mensagem;
    private Date dataHora;

    public Mensagem() {

    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public List<Aluno> getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(List<Aluno> fkAluno) {
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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

}
