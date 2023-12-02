package com.example.cw2_app11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private StringBuilder currentInput;
    private double operand1;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        currentInput = new StringBuilder();
        operand1 = 0;
        operator = "";

        // Set click listeners for all buttons
        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10,
                R.id.button11, R.id.button12, R.id.button13, R.id.button14,
                R.id.button15, R.id.button16
        };

        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(buttonText);
                break;
            default:
                appendInput(buttonText);
                break;
        }
    }

    private void appendInput(String input) {
        currentInput.append(input);
        updateDisplay();
    }

    private void handleOperator(String newOperator) {
        if (currentInput.length() > 0) {
            operand1 = Double.parseDouble(currentInput.toString());
            operator = newOperator;
            currentInput.setLength(0);
            updateDisplay();
        }
    }

    private void calculate() {
        if (currentInput.length() > 0) {
            double operand2 = Double.parseDouble(currentInput.toString());
            double result = performOperation(operand1, operand2, operator);
            currentInput.setLength(0);
            currentInput.append(result);
            updateDisplay();
            operand1 = result;
            operator = "";
        }
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {

                    return Double.NaN;
                }
            default:
                return operand2;
        }
    }

    private void clear() {
        currentInput.setLength(0);
        operand1 = 0;
        operator = "";
        updateDisplay();
    }

    private void updateDisplay() {
        textView.setText(currentInput.toString());
    }
}
