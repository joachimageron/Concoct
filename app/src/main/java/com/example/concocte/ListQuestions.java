package com.example.concocte;

import java.util.ArrayList;

public class ListQuestions {
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public ListQuestions() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
    public void nextQuestion() {
        currentQuestionIndex++;
    }
    public Boolean isLastQuestion() {
        return currentQuestionIndex == questions.size() - 1;
    }
    public int getScore() {
        return score;
    }
    public void incrementScore() {
        score++;
    }
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void reset() {
        currentQuestionIndex = 0;
        score = 0;
    }
}
