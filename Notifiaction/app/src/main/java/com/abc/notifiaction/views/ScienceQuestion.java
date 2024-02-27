package com.abc.notifiaction.views;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abc.notifiaction.R;
import com.abc.notifiaction.adapter.MCQAdapter;
import com.abc.notifiaction.databinding.ActivityScienceQuestionBinding;
import com.abc.notifiaction.interfaces.RecyclerViewInterface;
import com.abc.notifiaction.model.Category;

import java.util.ArrayList;

public class ScienceQuestion extends AppCompatActivity implements RecyclerViewInterface {
    private ActivityScienceQuestionBinding activityMathQuestionBinding;

    ArrayList<String> questionList = new ArrayList<>();
    String[] finalAnswer = {"7", "Hydrogen", "Alcohol", "Hertz", "Red",
            "Trypsin", "Salivary", "Adrenal",
            "Neuron", "Nephron"};
    ArrayList<ArrayList<String>> answersList = new ArrayList<>();
    public ArrayList<Boolean> answersMap = new ArrayList<>();
    int pageIndex = 0;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMathQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_science_question);

        /// MCQ For Question 1
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("6");
        answer1.add("7");
        answer1.add("8");
        answer1.add("9");

        /// MCQ For question 2
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("Oxygen");
        answer2.add("Helium");
        answer2.add("Carbon");
        answer2.add("Hydrogen");

        /// MCQ For question 3
        ArrayList<String> answer3 = new ArrayList<>();
        answer3.add("Aldehyde");
        answer3.add("Acetic acid");
        answer3.add("Alcohol");
        answer3.add("Ketone");

        /// MCQ For question 4
        ArrayList<String> answer4 = new ArrayList<>();
        answer4.add("Diopter");
        answer4.add("Second");
        answer4.add("Hertz");
        answer4.add("Meter");

        /// MCQ For question 5
        ArrayList<String> answer5 = new ArrayList<>();
        answer5.add("Black");
        answer5.add("Blue");
        answer5.add("Red");
        answer5.add("Orange");

        /// MCQ For question 6
        ArrayList<String> answer6 = new ArrayList<>();
        answer6.add("Pepsin");
        answer6.add("Hydrochloric acid");
        answer6.add("Mucus");
        answer6.add("Trypsin");

        /// MCQ For question 7
        ArrayList<String> answer7 = new ArrayList<>();
        answer7.add("Adrenal");
        answer7.add("Pituitary");
        answer7.add("Gonads");
        answer7.add("Salivary");

        /// MCQ For question 8
        ArrayList<String> answer8 = new ArrayList<>();
        answer8.add("Haryana");
        answer8.add("Pituitary");
        answer8.add("Gonads");
        answer8.add("Adrenal");

        /// MCQ For question 9
        ArrayList<String> answer9 = new ArrayList<>();
        answer9.add("Neutron");
        answer9.add("Neuron");
        answer9.add("Nephron");
        answer9.add("Nucleon");

        /// MCQ For question 10
        ArrayList<String> answer10 = new ArrayList<>();
        answer10.add("Neutron");
        answer10.add("Neuron");
        answer10.add("Nephron");
        answer10.add("Nucleon");

        questionList.add("What is the PH of H2O?");
        questionList.add("Which of the following gas is reduced in the reduction process?");
        questionList.add("Which of the following compound is mainly used in hand sanitizer?");
        questionList.add("What is the S.I unit of frequency?");
        questionList.add("Acid turns blue litmus paper into which color?");
        questionList.add("Which of the following enzymes is not present in the human stomach?");
        questionList.add("Which of the following gland is present in the human mouth?");
        questionList.add("Name the gland which is present above our kidneys?");
        questionList.add("What is the basic unit of our neural system?");
        questionList.add("What is the basic unit of our excretory system?");


        answersList.add(answer1);
        answersList.add(answer2);
        answersList.add(answer3);
        answersList.add(answer4);
        answersList.add(answer5);
        answersList.add(answer6);
        answersList.add(answer7);
        answersList.add(answer8);
        answersList.add(answer9);
        answersList.add(answer10);
        MCQAdapter mcqAdapter = new MCQAdapter(answersList.get(pageIndex), this, finalAnswer[pageIndex], this);
        activityMathQuestionBinding.answerRecyclerviewMath.setAdapter(mcqAdapter);
        activityMathQuestionBinding.answerRecyclerviewMath.setLayoutManager(new LinearLayoutManager(this));

        activityMathQuestionBinding.questionLabelMath.setText("Question " + (pageIndex + 1) + " of 10");
        activityMathQuestionBinding.questionMath.setText(questionList.get(pageIndex));
        Intent intent = getIntent();

        Category categoryModel = (Category) intent.getSerializableExtra("title");
//        Integer background = intent.getIntExtra("background", 0);
        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});

        activityMathQuestionBinding.layoutButton2Math.setBackground(gradientDrawable);
        activityMathQuestionBinding.layoutButton2Math.setOnClickListener(v -> {
            try {
                if (!answersMap.isEmpty()) {
                    if (pageIndex < 9 && answersMap.size() > pageIndex) {
                        if (answersMap.get(pageIndex) != null) {
                            pageIndex = pageIndex + 1;
                            if (pageIndex == 9) {
                                activityMathQuestionBinding.btnNext.setText("Submit");
                            }

                            activityMathQuestionBinding.questionLabelMath.setText("Question " + (pageIndex + 1) + " of 10");
                            activityMathQuestionBinding.questionMath.setText(questionList.get(pageIndex));
                            MCQAdapter adapter = new MCQAdapter(answersList.get(pageIndex), this, finalAnswer[pageIndex], this);
                            activityMathQuestionBinding.answerRecyclerviewMath.setAdapter(adapter);
                            activityMathQuestionBinding.answerRecyclerviewMath.setLayoutManager(new LinearLayoutManager(this));
                        }
                    } else {
                        if (pageIndex <= answersMap.size()) {
                            Toast.makeText(this, "Please select answer first", Toast.LENGTH_SHORT).show();
                        } else {
                            if (pageIndex == 9) {
                                startActivity(new Intent(getApplicationContext(), RewardScreen.class));
                                finish();
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Please select answer first", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                throw e;
            }
        });
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        if (pageIndex >= answersMap.size() || pageIndex < 0) {
            answersMap.add(pageIndex, true);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        isFinish("Want to close app?");
    }

    public void isFinish(String alertMessage) {

        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    startActivity(new Intent(getApplicationContext(), DashboardScreen.class));
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        builder.setMessage(alertMessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}