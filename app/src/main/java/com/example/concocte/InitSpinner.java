package com.example.concocte;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

// Class to initialize a spinner
public class InitSpinner implements AdapterView.OnItemSelectedListener {

    // Arrays for the spinner text and values
    private String[] spinner;
    private String[] spinnerValue;

    // Constructor for InitSpinner
    public InitSpinner(String[] spinner, String[] spinnerValue) {
        this.spinner = spinner;
        this.spinnerValue = spinnerValue;
    }

    // Method to initialize the spinner
    public void initSpinner(Spinner viewSpinner, Context context) {
        // Set the OnItemSelectedListener for the spinner
        viewSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter with the spinner text
        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, spinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter on the spinner
        viewSpinner.setAdapter(aa);
    }

    // Method to get the value corresponding to the selected item
    public String equalsTo(String selectedItem) {
        String selectedValue = null;
        for (int i = 0; i < spinner.length; i++) {
            if (spinner[i].equals(selectedItem)) {
                selectedValue = spinnerValue[i];
                break;
            }
        }
        return selectedValue;
    }

    // Method called when an item is selected in the spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // You can add code here to handle the item selection if needed
    }

    // Method called when no item is selected in the spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // You can add code here to handle the case where no item is selected if needed
    }
}