package com.example.distanceconvertor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText inputEditText;
    private EditText resultTextView;
    private Spinner fromSpinner;
    private Spinner toSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
    }


    public void convert(View view) {
        String inputStr = inputEditText.getText().toString();
        if (inputStr.isEmpty()) {
            resultTextView.setText(R.string.please_enter_a_value_to_convert);
            return;
        }

        double inputValue = Double.parseDouble(inputStr);
        double resultValue;

        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        if (fromUnit.equals(toUnit)) {
            resultValue = inputValue;
        } else {
            double fromFactor = getConversionFactor(fromUnit);
            double toFactor = getConversionFactor(toUnit);
            resultValue = (inputValue * fromFactor) / toFactor;
        }

        resultTextView.setText(String.format("%.6f ",resultValue));
    }

    private double getConversionFactor(String unit) {
        switch (unit) {
            case "Meter":
                return 1.0;
            case "Kilometer":
                return 1.0/0.001;
            case "Mile":
                return 1.0/0.000621371;
            case "Foot":
                return 1.0/3.28084;
            // Add more cases for other units as needed
            default:
                return 1.0;
        }
    }
}