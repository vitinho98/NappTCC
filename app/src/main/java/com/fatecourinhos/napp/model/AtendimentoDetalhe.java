package com.fatecourinhos.napp.model;

public class AtendimentoDetalhe {

    private String obsAtendimento;
    private String diagnosticos;
    private String profsExternos;
    private Prognostico fkPrognostico;

    public String getObsAtendimento() {
        return obsAtendimento;
    }

    public void setObsAtendimento(String obsAtendimento) {
        this.obsAtendimento = obsAtendimento;
    }

    public String getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(String diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public String getProfsExternos() {
        return profsExternos;
    }

    public void setProfsExternos(String profsExternos) {
        this.profsExternos = profsExternos;
    }

    public Prognostico getFkPrognostico() {
        return fkPrognostico;
    }

    public void setFkPrognostico(Prognostico fkPrognostico) {
        this.fkPrognostico = fkPrognostico;
    }

}
