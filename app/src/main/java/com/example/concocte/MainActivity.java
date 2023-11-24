package com.example.concocte;

import android.graphics.Color;
import android.util.Log;
import android.widget.*;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.concocte.data.ReadJSON;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Question question = new Question(
            "Quel est le nom de la capitale de la France ?",
            "Paris",
            "Paris est la capitale de la France.",
            new String[]{"Paris", "Lyon", "Marseille", "Toulouse"}
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout layout = findViewById(R.id.gridLayoutAnswers);
        TextView questionTextView = findViewById(R.id.questionTextView);
        Button nextButton = findViewById(R.id.nextQuestionButton);
        TextView explication = findViewById(R.id.textViewExplication);

        questionTextView.setText(question.getQuestion());
        explication.setText(question.getExplication());
        explication.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        ArrayList<Button> buttons = new java.util.ArrayList<>();
        for (String choice : question.getChoices()) {
            Button choiceButton = new Button(this);
            choiceButton.setText(choice);
            choiceButton.setOnClickListener(v -> {
                explication.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);

                if (question.checkAnswer(choice)) {
                    // Afficher la bonne réponse
                    Log.d("choice true", choice);
                } else {
                    // Afficher la mauvaise réponse
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

    private void showAnswer(String answer) {


    }

}