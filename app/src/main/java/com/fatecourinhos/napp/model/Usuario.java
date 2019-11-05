package com.fatecourinhos.napp.model;

public class Usuario {

    private int idUsuario;
    private String login;
    private String senha;
    private String tipoUsuario;
    private int status;
    private int primeiroLogin;

    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrimeiroLogin() {
        return primeiroLogin;
    }

    public void setPrimeiroLogin(int primeiroLogin) {
        this.primeiroLogin = primeiroLogin;
    }
}
