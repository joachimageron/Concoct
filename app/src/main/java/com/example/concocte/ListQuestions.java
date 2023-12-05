package com.example.concocte;

import java.util.ArrayList;

// Class to manage a list of questions
public class ListQuestions {
// List to store the questions
    private ArrayList<Question> questions;
// Index to keep track of the current question
private int currentQuestionIndex;
// Variable to store the score
    private int score;

// Constructor for ListQuestions
public ListQuestions() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
    }
// Method to add a question to the list
public void addQuestion(Question question) {
        questions.add(question);
    }
// Method to get the current question
public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
// Method to move to the next question
public void nextQuestion() {
        currentQuestionIndex++;
    }
// Method to check if the current question is the last one
public Boolean isLastQuestion() {
        return currentQuestionIndex == questions.size() - 1;
    }
// Method to get the score
public int getScore() {
        return score;
    }
// Method to increment the score
public void incrementScore() {
        score++;
    }
// Method to get the index of the current question
public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
// Method to get the list of questions
public ArrayList<Question> getQuestions() {
        return questions;
    }
// Method to reset the index and score
public void reset() {
        currentQuestionIndex = 0;
        score = 0;
    }
}
