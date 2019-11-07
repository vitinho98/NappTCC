package com.fatecourinhos.napp.model;

public class Anamnese {

    private int idAnamnese;
    private Aluno fkAluno;
    private String questao1;
    private String questao2;
    private String questao3;
    private String questao4;
    private String questao5;
    private String questao6;
    private String questao7;
    private String questao8;
    private String questao9;

    public Anamnese() {
    }


    public int getIdAnamnese() {
        return idAnamnese;
    }

    public void setIdAnamnese(int idAnamnese) {
        this.idAnamnese = idAnamnese;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public String getQuestao1() {
        return questao1;
    }

    public void setQuestao1(String questao1) {
        this.questao1 = questao1;
    }

    public String getQuestao2() {
        return questao2;
    }

    public void setQuestao2(String questao2) {
        this.questao2 = questao2;
    }

    public String getQuestao3() {
        return questao3;
    }

    public void setQuestao3(String questao3) {
        this.questao3 = questao3;
    }

    public String getQuestao4() {
        return questao4;
    }

    public void setQuestao4(String questao4) {
        this.questao4 = questao4;
    }

    public String getQuestao5() {
        return questao5;
    }

    public void setQuestao5(String questao5) {
        this.questao5 = questao5;
    }

    public String getQuestao6() {
        return questao6;
    }

    public void setQuestao6(String questao6) {
        this.questao6 = questao6;
    }

    public String getQuestao7() {
        return questao7;
    }

    public void setQuestao7(String questao7) {
        this.questao7 = questao7;
    }

    public String getQuestao8() {
        return questao8;
    }

    public void setQuestao8(String questao8) {
        this.questao8 = questao8;
    }

    public String getQuestao9() {
        return questao9;
    }

    public void setQuestao9(String questao9) {
        this.questao9 = questao9;
    }
}
