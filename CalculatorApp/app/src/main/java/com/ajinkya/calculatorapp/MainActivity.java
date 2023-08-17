package com.ajinkya.calculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button buttonDOT, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonMinus, buttonMult, buttonDivide, buttonEqual, buttonAC, buttonDEL;
    TextView textViewHistory, textViewResult;
    private String number = "";
    double firstNumber = 0, secondNumber = 0;
    String status = "";
    boolean operator = false;
    String history, currentResult;

    DecimalFormat decimalFormat = new DecimalFormat("######.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewHistory = findViewById(R.id.textHistory);
        textViewResult = findViewById(R.id.textResult);
        buttonAC = findViewById(R.id.acButton);
        buttonDEL = findViewById(R.id.delButton);
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.addButton);
        buttonMinus = findViewById(R.id.minusButton);
        buttonMult = findViewById(R.id.multiplyButton);
        buttonDivide = findViewById(R.id.devideButton);
        buttonEqual = findViewById(R.id.equalButton);
        buttonDOT = findViewById(R.id.dotButton);

        button0.setOnClickListener(v -> numberClicked("0"));
        buttonAC.setOnClickListener(v -> {
            number = "0";
            status = null;
            firstNumber = 0;
            secondNumber = 0;
            textViewResult.setText("0");
            textViewHistory.setText("0");
        });
        button1.setOnClickListener(v -> numberClicked("1"));
        button2.setOnClickListener(v -> numberClicked("2"));
        button3.setOnClickListener(v -> numberClicked("3"));
        button4.setOnClickListener(v -> numberClicked("4"));
        button6.setOnClickListener(v -> numberClicked("6"));
        button5.setOnClickListener(v -> numberClicked("5"));
        button7.setOnClickListener(v -> numberClicked("7"));
        button8.setOnClickListener(v -> numberClicked("8"));
        button9.setOnClickListener(v -> numberClicked("9"));
        buttonDOT.setOnClickListener(v -> {
            if (number == null) {
                number = "0.";
            } else {
                number = number + ".";
            }
            textViewResult.setText(number);
        });
        buttonDEL.setOnClickListener(v -> {
            number = number.substring(0, number.length() - 1);
            textViewResult.setText(number);
        });
        buttonEqual.setOnClickListener(v -> {
            if (operator) {
                switch (status) {
                    case "multiplication":
                        multiplyFields();
                        break;
                    case "minus":
                        minusFields();
                        break;
                    case "divide":
                        divideFields();
                        break;
                    case "sum":
                        addFields();
                        break;
                    default:
                        //addFields();
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                        break;
                }
            }
            operator = false;
        });
        buttonMult.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "/");
            performOperation("multiplication");
        });
        buttonMinus.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "-");
            performOperation("minus");
        });
        buttonDivide.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "/");
            performOperation("divide");
        });
        buttonAdd.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "+");
            performOperation("sum");
        });


    }

    public void performOperation(String statusLoaded) {
        if (operator) {
            switch (status) {
                case "multiplication":
                    multiplyFields();
                    break;
                case "minus":
                    minusFields();
                    break;
                case "divide":
                    divideFields();
                    break;
                default:
                    addFields();
                    break;
            }
            status = statusLoaded;
            number = null;
            operator = false;
        }
    }

    public void numberClicked(String data) {
        if (number == null || number.equals("0")) {
            number = data;
        } else {
            number = number + data;
        }
        textViewResult.setText(number);
        operator = true;
    }

    public void addFields() {
        secondNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + secondNumber;
        textViewResult.setText(" " + decimalFormat.format(firstNumber));
        operator = true;
    }

    public void minusFields() {
        if (firstNumber == 0) {
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            secondNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - secondNumber;
        }
        textViewResult.setText(" " + decimalFormat.format(firstNumber));
        operator = true;
    }

    public void multiplyFields() {
        if (firstNumber == 0) {
            firstNumber = 1;
            secondNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * secondNumber;
        } else {
            secondNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * secondNumber;
        }
        textViewResult.setText(" " + decimalFormat.format(firstNumber));
        operator = true;
    }

    public void divideFields() {
        secondNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber / secondNumber;
        textViewResult.setText(" " + decimalFormat.format(firstNumber));
        operator = true;
    }
}