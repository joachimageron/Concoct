package com.example.concocte;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

// Class representing the form for the quiz
public class FormQuizzFragment extends Fragment  {

    // Arrays for the themes and difficulties
    String[] themeText = { "Tv et Cinema", "Art et littérature", "Musique", "Actualités et Politiques", "Culture Générale", "Sport", "Jeux-Vidéos"};
    String[] themeValue = { "tv_cinema", "art_litterature", "musique", "actu_politique", "culture_generale", "sport", "jeux_videos"};
    String[] difficulty = { "facile", "normal", "difficile"};

    // Factory method to create a new instance of FormQuizzFragment
    public static FormQuizzFragment newInstance() {
        FormQuizzFragment fragment = new FormQuizzFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // onCreate lifecycle method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateView lifecycle method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();

        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_form_quizz, container, false);

        // Get the play button, count text, theme spinner and difficulty spinner
        Button playQuizz = (Button) view.findViewById(R.id.playquizz);
        EditText countText = (EditText) view.findViewById(R.id.count);
        Spinner spinTheme = (Spinner) view.findViewById(R.id.themespinner);
        Spinner spinDiff = (Spinner) view.findViewById(R.id.difficultyspinner);

        // Initialize the spinners
        InitSpinner themeSpinner = new InitSpinner(themeText, themeValue);
        themeSpinner.initSpinner(spinTheme, context);
        InitSpinner difficultySpinner = new InitSpinner(difficulty, null);
        difficultySpinner.initSpinner(spinDiff, context);

        // Set the onClickListener for the play button
        playQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the form
                String counValue = countText.getText().toString();
                String themevalue = themeSpinner.equalsTo(spinTheme.getSelectedItem().toString());
                String difficultyValue = spinDiff.getSelectedItem().toString();

                // Start the quiz
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                QuizzFragment quizzFragment = QuizzFragment.newInstance(themevalue, counValue, difficultyValue);
                transaction.replace(R.id.fragmentContainerView, quizzFragment); // newInstance() is a static factory method.
                transaction.commit();
            }
        });
        return view;
    }

    // onViewCreated lifecycle method
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}