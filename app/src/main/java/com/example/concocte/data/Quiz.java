package com.example.concocte.data;

public class Quiz {

    private String name;
    private String question;
    private String [] propositions;
    private int idResponse;
    private int nbOfQuestions;    public void setIdResponse(int idResponse) {
        this.idResponse = idResponse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbOfQuestions(int nbOfQuestions) {
        this.nbOfQuestions = nbOfQuestions;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPropositions(String[] propositions) {
        this.propositions = propositions;
    }

    public int getIdResponse() {
        return idResponse;
    }

    public String getResponse() {
        return getPropositions()[getIdResponse()];
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPropositions() {
        return propositions;
    }
}
