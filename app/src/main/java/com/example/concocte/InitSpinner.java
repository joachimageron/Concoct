package com.example.concocte;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InitSpinner implements
        AdapterView.OnItemSelectedListener{

    String [] spinner;
    String [] spinnerValue;

    public InitSpinner(String [] spinner, String [] spinnerValue){
        this.spinner = spinner;
        this.spinnerValue = spinnerValue;
    }



    public void initSpinner(Spinner viewSpinner, Context context){
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        viewSpinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(context,android.R.layout.simple_spinner_item, spinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        viewSpinner.setAdapter(aa);
    }

    public String equalsTo(String selectedItem){
        String selectedValue = null;
        for (int i = 0; i<spinner.length ; i++){
            if (spinner[i] == selectedItem){
                selectedValue = spinnerValue[i];
                break;
            }
        }
        return selectedValue;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
