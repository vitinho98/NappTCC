package com.fatecourinhos.napp.model;

public class CampoAtuacao {

    private Integer idCampoAtuacao;
    private String nomeCampoAtuacao;

    public CampoAtuacao(){

    }

    public CampoAtuacao(int idCampoAtuacao, String nomeCampoAtuacao){
        this.idCampoAtuacao = idCampoAtuacao;
        this.nomeCampoAtuacao = nomeCampoAtuacao;
    }

    public Integer getIdCampoAtuacao() {
        return idCampoAtuacao;
    }

    public void setIdCampoAtuacao(Integer idCampoAtuacao) {
        this.idCampoAtuacao = idCampoAtuacao;
    }

    public String getNomeCampoAtuacao() {
        return nomeCampoAtuacao;
    }

    public void setNomeCampoAtuacao(String nomeCampoAtuacao) {
        this.nomeCampoAtuacao = nomeCampoAtuacao;
    }
}
