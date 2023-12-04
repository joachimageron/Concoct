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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FormQuizzFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {

    String[] theme = { "tv_cinema", "art_litterature", "musique", "actu_politique", "culture_generale", "sport", "jeux_videos"};
    String[] difficulty = { "facile", "normal", "difficile"};

    public static FormQuizzFragment newInstance() {
        FormQuizzFragment fragment = new FormQuizzFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView");
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_form_quizz, container, false);
        Button playQuizz = (Button) view.findViewById(R.id.playquizz);
        EditText countText = (EditText) view.findViewById(R.id.count);
        Spinner spinTheme = (Spinner) view.findViewById(R.id.themespinner);
        Spinner spinDiff = (Spinner) view.findViewById(R.id.difficultyspinner);

        initSpinners(spinTheme, spinDiff);

        playQuizz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String counValue = countText.getText().toString();
                String themevalue = spinTheme.getSelectedItem().toString();
                String difficultyValue = spinDiff.getSelectedItem().toString();

                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                QuizzFragment quizzFragment = QuizzFragment.newInstance(themevalue, counValue, difficultyValue);
                transaction.replace(R.id.fragmentContainerView, quizzFragment); // newInstance() is a static factory method.
                transaction.commit();
            }
        });
        return view;
    }

    private void initSpinners(Spinner spinTheme, Spinner spinDiff) {
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinTheme.setOnItemSelectedListener(this);
        spinDiff.setOnItemSelectedListener(this);
        Context context = getContext();

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter themeaa = new ArrayAdapter(context,android.R.layout.simple_spinner_item, theme);
        themeaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinTheme.setAdapter(themeaa);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter difficultyaa = new ArrayAdapter(context,android.R.layout.simple_spinner_item, difficulty);
        difficultyaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinDiff.setAdapter(difficultyaa);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("onViewCreated");
    }
}