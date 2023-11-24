package com.example.concocte;

public class Question {
    private String question;
    private String explication;
    private String answer;
    private String[] choices;

    public Question(String question, String answer, String explication, String[] choices) {
        this.question = question;
        this.answer = answer;
        this.explication = explication;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public String getExplication() {
        return explication;
    }

    public String[] getChoices() {
        return choices;
    }

    public Boolean checkAnswer(String answer) {
        if (answer.equals(this.answer)) {
            return true;
        } else {
            return false;
        }
    }

}
