package com.example.concocte;

import android.content.Context;
import android.os.Handler;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.concocte.data.APIRequest;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizzFragment extends Fragment {
    ListQuestions listQuestions = new ListQuestions();

    private final Handler handler = new Handler(Looper.getMainLooper());
    String themeUrl;
    String countUrl;
    String difficultyUrl;


    public QuizzFragment(String theme, String count, String difficulty){
        themeUrl = theme;
        countUrl = count;
        difficultyUrl = difficulty;
    }

    public static QuizzFragment newInstance(String theme, String count, String difficulty) {
        QuizzFragment fragment = new QuizzFragment(theme, count, difficulty);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface APIQuizCallback {
        Boolean AddListQuestions(JSONObject jsonObject);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quizz, container, false);
        APIRequest apiRequest = new APIRequest();
        Context context = getContext();
        ImageButton stopQuizz = view.findViewById(R.id.stopQuizz);

        apiRequest.run(new APIQuizCallback() {
            @Override
            public Boolean AddListQuestions(JSONObject jsonObject) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        addQuestions(jsonObject);
                        Next(view, context);
                    }
                });
                return true;
            }
        }, themeUrl, countUrl, difficultyUrl);

        stopQuizz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Arrêter le quizz ?");
                builder.setPositiveButton("Oui", (dialog, which) -> {
                    listQuestions.reset();

                    FragmentManager manager = getParentFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    FormQuizzFragment formQuizzFragment = FormQuizzFragment.newInstance();
                    transaction.replace(R.id.fragmentContainerView, formQuizzFragment); // newInstance() is a static factory method.
                    transaction.commit();
                });
                builder.setNegativeButton("Non", (dialog, which) -> {
                    dialog.cancel();
                });
                builder.show();

            }
        });

        return view;
    }

    private void Next(View view, Context context) {
        displayQuestion(view, context);

        Button nextButton = view.findViewById(R.id.nextQuestionButton);
        nextButton.setOnClickListener(v -> {
            if (listQuestions.isLastQuestion()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Fin du quiz");
                builder.setMessage("Vous avez terminé le quizz avec un score de " + listQuestions.getScore() + "/" + listQuestions.getQuestions().size() + ".");
                builder.setPositiveButton("Recommencer", (dialog, which) -> {
                    clearGridLayout(view);
                    displayQuestion(view, context);
                });
                builder.setNegativeButton("Nouveau Quizz", (dialog, which) -> {
                    FragmentManager manager = getParentFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    FormQuizzFragment formQuizzFragment = FormQuizzFragment.newInstance();
                    transaction.replace(R.id.fragmentContainerView, formQuizzFragment); // newInstance() is a static factory method.
                    transaction.commit();
                    listQuestions.reset();
                });
                listQuestions.reset();
                builder.show();
            } else {
                listQuestions.nextQuestion();
                clearGridLayout(view);
                displayQuestion(view, context);
            }
        });
    }

    private void displayQuestion(View view, Context context) {
        GridLayout layout = view.findViewById(R.id.gridLayoutAnswers);
        TextView questionTextView = view.findViewById(R.id.questionTextView);
        Button nextButton = view.findViewById(R.id.nextQuestionButton);
        TextView explication = view.findViewById(R.id.textViewExplication);
        Question question = listQuestions.getCurrentQuestion();
        TextView scoreTextView = view.findViewById(R.id.scoreTextView);
        Log.d("score", listQuestions.getScore() + "");

        questionTextView.setText(question.getQuestion());
        scoreTextView.setText(listQuestions.getScore() + "/" + listQuestions.getQuestions().size());
        explication.setText(question.getExplication());
        explication.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        ArrayList<Button> buttons = new ArrayList<>();
        for (String choice : question.getChoices()) {
            Button choiceButton = new Button(context);
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
                    button.setBackgroundColor(question.checkAnswer(button.getText().toString()) ? Color.argb(255, 225, 255, 208) : Color.argb(255, 255, 234, 234));
                    button.setTextColor(Color.argb(255, 0, 0, 0));
                }
            });
            buttons.add(choiceButton);
            layout.addView(choiceButton);
        }
    }

    private void addQuestions(JSONObject jsonObject) {

        JSONArray quizzes;

        try {
            quizzes = jsonObject.getJSONArray("quizzes");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i < quizzes.length(); i++) {
            String question;
            String answer;
            String explication = "";
            String [] choices;
            try {
                question = quizzes.getJSONObject(i).getString("question");
                answer = quizzes.getJSONObject(i).getString("answer");
                choices = new String[] {answer, quizzes.getJSONObject(i).getJSONArray("badAnswers").getString(0), quizzes.getJSONObject(i).getJSONArray("badAnswers").getString(1), quizzes.getJSONObject(i).getJSONArray("badAnswers").getString(2)};
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            listQuestions.addQuestion(new Question(question, answer, explication, choices));
        }
    }
    private void clearGridLayout(View view) {
        GridLayout layout = view.findViewById(R.id.gridLayoutAnswers);
        layout.removeAllViews();
    }
}