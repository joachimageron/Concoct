package com.example.concocte;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
private String question;
private String explication;
private String answer;
private String[] choices;

public Question(String question, String answer, String explication, String[] choices) {
	this.question = question;
	this.answer = answer;
	this.explication = explication;
	this.choices = shuffleChoices(choices);
}

private String[] shuffleChoices(String[] choices) {
	List<String> choicesList = Arrays.asList(choices);
	Collections.shuffle(choicesList);
	return choicesList.toArray(new String[0]);
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
