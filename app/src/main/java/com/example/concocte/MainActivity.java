package com.example.concocte;

import android.graphics.Color;
import android.util.Log;
import android.widget.*;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ListQuestions listQuestions = new ListQuestions();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	addQuestions();
	displayQuestion();
	
	Button nextButton = findViewById(R.id.nextQuestionButton);
	nextButton.setOnClickListener(v -> {
		if (listQuestions.isLastQuestion()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Fin du quiz");
			builder.setMessage("Vous avez terminé le quizz avec un score de " + listQuestions.getScore() + "/" + listQuestions.getQuestions().size() + ".");
			builder.setPositiveButton("OK", (dialog, which) -> {
			
			});
			listQuestions.reset();
			clearGridLayout();
			displayQuestion();
			builder.show();
		} else {
			listQuestions.nextQuestion();
			clearGridLayout();
			displayQuestion();
		}
	});
	
	
}

private void displayQuestion() {
	GridLayout layout = findViewById(R.id.gridLayoutAnswers);
	TextView questionTextView = findViewById(R.id.questionTextView);
	Button nextButton = findViewById(R.id.nextQuestionButton);
	TextView explication = findViewById(R.id.textViewExplication);
	Question question = listQuestions.getCurrentQuestion();
	TextView scoreTextView = findViewById(R.id.scoreTextView);
	Log.d("score", listQuestions.getScore() + "");
	
	questionTextView.setText(question.getQuestion());
	scoreTextView.setText(listQuestions.getScore() + "/" + listQuestions.getQuestions().size());
	explication.setText(question.getExplication());
	explication.setVisibility(View.INVISIBLE);
	nextButton.setVisibility(View.INVISIBLE);
	
	ArrayList<Button> buttons = new ArrayList<>();
	for (String choice : question.getChoices()) {
		Button choiceButton = new Button(this);
		choiceButton.setText(choice);
		choiceButton.setOnClickListener(v -> {
			explication.setVisibility(View.VISIBLE);
			nextButton.setVisibility(View.VISIBLE);
			
			if (question.checkAnswer(choice)) {
				Log.d("choice true", choice);
				listQuestions.incrementScore();
			} else {
				Log.d("choice false", choice);
			}
			for (Button button : buttons) {
				button.setEnabled(false);
				button.setBackgroundColor(question.checkAnswer(button.getText().toString()) ? Color.argb(255, 0, 102, 0) : Color.argb(255, 102, 0, 0));
			}
		});
		buttons.add(choiceButton);
		layout.addView(choiceButton);
	}
}

private void addQuestions() {
	Question question1 = new Question("Quel est le nom de la capitale de la France ?", "Paris", "Paris est la capitale de la France.", new String[]{"Paris", "Lyon", "Marseille", "Toulouse"});
	Question question2 = new Question("Quel est le nom de la capitale de l'Espagne ?", "Madrid", "Madrid est la capitale de l'Espagne.", new String[]{"Madrid", "Barcelone", "Séville", "Valence"});
	Question question3 = new Question("Quel est le nom de la capitale de l'Italie ?", "Rome", "Rome est la capitale de l'Italie.", new String[]{"Rome", "Milan", "Naples", "Turin"});
	Question question4 = new Question("Quel est le nom de la capitale de l'Allemagne ?", "Berlin", "Berlin est la capitale de l'Allemagne.", new String[]{"Berlin", "Hambourg", "Munich", "Cologne"});
	listQuestions.addQuestion(question1);
	listQuestions.addQuestion(question2);
	listQuestions.addQuestion(question3);
	listQuestions.addQuestion(question4);
}
private void clearGridLayout() {
	GridLayout layout = findViewById(R.id.gridLayoutAnswers);
	layout.removeAllViews();
}
}

