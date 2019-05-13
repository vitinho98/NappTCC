package com.fatecourinhos.napp.model;

public class AlunoModel {

    private int idAluno;
    private String nomeAluno;
    private String ra;
    private String cpf;
    private String celularAluno;
    private String curso;
    private int semestre;
    private String anoEntrada;
    private String dataNascimento;
    private String sexo;
    private String cidadeAluno;
    private String estadoCivil;
    private boolean empregado;
    private String emailAluno;
    private UsuarioModel fkUsuario;

    public AlunoModel() {
    }

    public AlunoModel(int idAluno, String nomeAluno, String ra, String cpf, String celularAluno, String curso, int semestre, String anoEntrada, String dataNascimento, String sexo, String cidadeAluno, String estadoCivil, boolean empregado, String emailAluno,  UsuarioModel fkUsuario) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.ra = ra;
        this.cpf = cpf;
        this.celularAluno = celularAluno;
        this.curso = curso;
        this.semestre = semestre;
        this.anoEntrada = anoEntrada;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cidadeAluno = cidadeAluno;
        this.estadoCivil = estadoCivil;
        this.empregado = empregado;
        this.emailAluno = emailAluno;
        this.fkUsuario = fkUsuario;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelularAluno() {
        return celularAluno;
    }

    public void setCelularAluno(String celularAluno) {
        this.celularAluno = celularAluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(String anoEntrada) {
        this.anoEntrada = anoEntrada;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCidadeAluno() {
        return cidadeAluno;
    }

    public void setCidadeAluno(String cidadeAluno) {
        this.cidadeAluno = cidadeAluno;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public boolean isEmpregado() {
        return empregado;
    }

    public void setEmpregado(boolean empregado) {
        this.empregado = empregado;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public UsuarioModel getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioModel fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
