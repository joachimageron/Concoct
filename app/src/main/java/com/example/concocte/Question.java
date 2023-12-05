package com.example.concocte;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Class to represent a question in the quiz
public class Question {
    // Variables to store the question, explanation, answer and choices
    private String question;
    private String explication;
    private String answer;
    private String[] choices;

    // Constructor for Question
    public Question(String question, String answer, String explication, String[] choices) {
        this.question = question;
        this.answer = answer;
        this.explication = explication;
        this.choices = shuffleChoices(choices);
    }

    // Method to shuffle the choices
    private String[] shuffleChoices(String[] choices) {
        List<String> choicesList = Arrays.asList(choices);
        Collections.shuffle(choicesList);
        return choicesList.toArray(new String[0]);
    }

    // Method to get the question
    public String getQuestion() {
        return question;
    }

    // Method to get the explanation
    public String getExplication() {
        return explication;
    }

    // Method to get the choices
    public String[] getChoices() {
        return choices;
    }

    // Method to check if the provided answer is correct
    public Boolean checkAnswer(String answer) {
        if (answer.equals(this.answer)) {
            return true;
        } else {
            return false;
        }
    }
}