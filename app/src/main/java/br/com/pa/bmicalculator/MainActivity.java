package br.com.pa.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private TextView ageEditText;
    private TextView feetEditText;
    private TextView inchesEditText;
    private TextView weightEditText;
    private TextView resultText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        resultText = findViewById(R.id.edit_text_result);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmi);
                } else {
                    displayGuidance(bmi);
                }
            }
        });
    }

    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;

        // BMI formula: weight in Kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiText = decimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiText + " - You are underweight";
        } else if (bmi > 25) {
            fullResultString = bmiText + " - You are overweight";
        } else {
            fullResultString = bmiText + " - You are healthy weight";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiText = decimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()) {
            fullResultString = bmiText + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            fullResultString = bmiText + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        } else {
            fullResultString = bmiText + " - As you are under 18, please consult with your doctor for the healthy range";
        }

        resultText.setText(fullResultString);
    }
}