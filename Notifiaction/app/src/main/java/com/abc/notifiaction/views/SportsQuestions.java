package com.abc.notifiaction.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abc.notifiaction.R;
import com.abc.notifiaction.adapter.MCQAdapter;
import com.abc.notifiaction.databinding.ActivityScienceQuestionBinding;
import com.abc.notifiaction.databinding.ActivitySportsQuestionsBinding;
import com.abc.notifiaction.interfaces.RecyclerViewInterface;
import com.abc.notifiaction.model.Category;
import com.abc.notifiaction.model.SingletonExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SportsQuestions extends AppCompatActivity implements RecyclerViewInterface {
    private ActivitySportsQuestionsBinding activitySportsQuestionsBinding;

    ArrayList<String> questionList = new ArrayList<>();
    String[] finalAnswer = {"45 gms", "Basket Ball", "5", "Betty Uber", "New Delhi",
            "British Empire Games", "All", "All of the above",
            "61", "1951"};
    ArrayList<ArrayList<String>> answersList = new ArrayList<>();
    //    public ArrayList<Boolean> answersMap = new ArrayList<>();
    SingletonExample singletonExample = new SingletonExample();
    int pageIndex = 0;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySportsQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_sports_questions);

        /// MCQ For Question 1
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("20 gms");
        answer1.add("25 gms");
        answer1.add("40 gms");
        answer1.add("45 gms");

        /// MCQ For question 2
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("Football");
        answer2.add("Basket Ball");
        answer2.add("Ice Hockey");
        answer2.add("Badminton");

        /// MCQ For question 3
        ArrayList<String> answer3 = new ArrayList<>();
        answer3.add("5");
        answer3.add("6");
        answer3.add("7");
        answer3.add("8");

        /// MCQ For question 4
        ArrayList<String> answer4 = new ArrayList<>();
        answer4.add("Betty Uber");
        answer4.add("Sarah Uber");
        answer4.add("Jonna Uber");
        answer4.add("Katrina Uber");

        /// MCQ For question 5
        ArrayList<String> answer5 = new ArrayList<>();
        answer5.add("Mumbai");
        answer5.add("New Delhi");
        answer5.add("Bangalore");
        answer5.add("Surat");

        /// MCQ For question 6
        ArrayList<String> answer6 = new ArrayList<>();
        answer6.add("British Commonwealth Games");
        answer6.add("British Empire and Commonwealth Games");
        answer6.add("British Empire Games");
        answer6.add("Commonwealth Games");

        /// MCQ For question 7
        ArrayList<String> answer7 = new ArrayList<>();
        answer7.add("The ITF");
        answer7.add("The Association of Tennis Professionals");
        answer7.add("Women’s Tennis Association");
        answer7.add("All");

        /// MCQ For question 8
        ArrayList<String> answer8 = new ArrayList<>();
        answer8.add("Lawn Balls");
        answer8.add("Netball");
        answer8.add("Squash");
        answer8.add("All of the above");

        /// MCQ For question 9
        ArrayList<String> answer9 = new ArrayList<>();
        answer9.add("50");
        answer9.add("61");
        answer9.add("80");
        answer9.add("75");

        /// MCQ For question 10
        ArrayList<String> answer10 = new ArrayList<>();
        answer10.add("1951");
        answer10.add("1962");
        answer10.add("1968");
        answer10.add("1970");

        questionList.add("What is the approximate maximum weight of Golf Ball as per Rules of Golf?");
        questionList.add("Which of the following sports was invented by James Naismith?");
        questionList.add("How many chapters are there in the Olympic Charter?");
        questionList.add("After whose name is the Uber Cup named?");
        questionList.add("Where are the headquarters of Indian Boxing Federation located?");
        questionList.add("What was the name of the Commonwealth Games in the starting from 1930 to 1950?");
        questionList.add("What tennis bodies together comprise the Tennis Integrity Unit?");
        questionList.add("Which of the following events are not a part of the Olympic Games but a part of the Commonwealth Games?");
        questionList.add("How many articles are there in the Olympic Charter?");
        questionList.add("When were the first Asian Games held?");


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
        activitySportsQuestionsBinding.answerRecyclerviewSports.setAdapter(mcqAdapter);
        activitySportsQuestionsBinding.answerRecyclerviewSports.setLayoutManager(new LinearLayoutManager(this));

        activitySportsQuestionsBinding.questionLabelSports.setText("Question " + (pageIndex + 1) + " of 10");
        activitySportsQuestionsBinding.questionSports.setText(questionList.get(pageIndex));
        Intent intent = getIntent();

        Category categoryModel = (Category) intent.getSerializableExtra("title");
//        Integer background = intent.getIntExtra("background", 0);
        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});

        activitySportsQuestionsBinding.layoutButton2Sports.setBackground(gradientDrawable);
        activitySportsQuestionsBinding.layoutButton2Sports.setOnClickListener(v -> {
            try {
                if (!singletonExample.answersMap.isEmpty()) {
                    if (pageIndex < 9 && singletonExample.answersMap.size() > pageIndex) {
                        if (singletonExample.answersMap.get(pageIndex) != null) {
                            pageIndex = pageIndex + 1;
                            if (pageIndex == 9) {
                                activitySportsQuestionsBinding.btnNextSports.setText("Submit");
                            }

                            activitySportsQuestionsBinding.questionLabelSports.setText("Question " + (pageIndex + 1) + " of 10");
                            activitySportsQuestionsBinding.questionSports.setText(questionList.get(pageIndex));
                            MCQAdapter adapter = new MCQAdapter(answersList.get(pageIndex), this, finalAnswer[pageIndex], this);
                            activitySportsQuestionsBinding.answerRecyclerviewSports.setAdapter(adapter);
                            activitySportsQuestionsBinding.answerRecyclerviewSports.setLayoutManager(new LinearLayoutManager(this));
                        }
                    } else {
                        if (!singletonExample.answersMap.isEmpty()) {
                            if (pageIndex < singletonExample.answersMap.size() && singletonExample.answersMap.size() != 10) {
                                Toast.makeText(this, "Please select answer first", Toast.LENGTH_SHORT).show();
                            } else {
                                if (pageIndex >= 9) {
                                    int occurrences = Collections.frequency(Arrays.asList(singletonExample.answersMap.toArray()), true);
                                    Intent rewardIntent = new Intent(getApplicationContext(), RewardScreen.class);
                                    rewardIntent.putExtra("score", String.valueOf(occurrences));
                                    startActivity(rewardIntent);
                                    finish();
                                } else {
                                    Toast.makeText(this, "Please select answer first", Toast.LENGTH_SHORT).show();
                                }
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
        if (pageIndex >= singletonExample.answersMap.size() || pageIndex < 0) {
            if (answersList.get(pageIndex).get(position).equals(finalAnswer[pageIndex])) {
                singletonExample.answersMap.add(pageIndex, true);
            } else {
                singletonExample.answersMap.add(pageIndex, false);
            }
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