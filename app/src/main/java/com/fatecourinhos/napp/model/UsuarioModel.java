package com.fatecourinhos.napp.model;

public class UsuarioModel {

    private int idUsuario;
    private String login;
    private String senha;
    private String tipoUsuario;
    private boolean status;

    public UsuarioModel() {
    }

    public UsuarioModel(int idUsuario, String login, String senha, String tipoUsuario, boolean status) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
