
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
import com.abc.notifiaction.databinding.ActivityEntertainmentScreenBinding;
import com.abc.notifiaction.interfaces.RecyclerViewInterface;
import com.abc.notifiaction.model.Category;
import com.abc.notifiaction.model.SingletonExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EntertainmentScreen extends AppCompatActivity implements RecyclerViewInterface {
    private ActivityEntertainmentScreenBinding activityEntertainmentScreenBinding;

    ArrayList<String> questionList = new ArrayList<>();
    String[] finalAnswer = {"Oppenheimer", "Achyut Chopade", "Vikrant Massey", "Moushumi Chatterjee", "Karishma Tanna",
            "Naatu Naatu", "Both 1 and 2", "Ken Ghosh",
            "Ranveer Singh", "We Are"};
    ArrayList<ArrayList<String>> answersList = new ArrayList<>();
    //    public ArrayList<Boolean> answersMap = new ArrayList<>();
    SingletonExample singletonExample = new SingletonExample();
    int pageIndex = 0;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEntertainmentScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_entertainment_screen);

        /// MCQ For Question 1
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("Barbie");
        answer1.add("Aquaman and the Lost Kingdom");
        answer1.add("Oppenheimer");
        answer1.add("John Wick: Chapter 4");

        /// MCQ For question 2
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("Achyut Chopade");
        answer2.add("Sujay Dahake");
        answer2.add("Nagraj Manjule");
        answer2.add("Aditya Sarpotdar");

        /// MCQ For question 3


        /// MCQ For question 4
        ArrayList<String> answer3 = new ArrayList<>();
        answer3.add("Vicky Kaushal");
        answer3.add("Vikrant Massey");
        answer3.add("Bobby Deol");
        answer3.add("Shah Rukh Khan");

        /// MCQ For question 5
        ArrayList<String> answer4 = new ArrayList<>();
        answer4.add("Rekha");
        answer4.add("Waheeda Rehman");
        answer4.add("Maushumi Chatterjee");
        answer4.add("Jaya Bacchan");

        /// MCQ For question 6
        ArrayList<String> answer5 = new ArrayList<>();
        answer5.add("Karishma Tanna");
        answer5.add("Raveena Tandon");
        answer5.add("Rupali Ganguly");
        answer5.add("Kareena Kapoor Khan");

        /// MCQ For question 7
        ArrayList<String> answer6 = new ArrayList<>();
        answer6.add("Naacho Naacho");
        answer6.add("Yara Teri Meri Yaari");
        answer6.add("Neendraan Ni Aandiyan");
        answer6.add("Naatu Naatu");

        /// MCQ For question 8
        ArrayList<String> answer7 = new ArrayList<>();
        answer7.add("Jai Bhim");
        answer7.add("Marakkar: Arabijadalinte Simham");
        answer7.add("Karnan");
        answer7.add("Both 1 and 2");

        /// MCQ For question 9
        ArrayList<String> answer8 = new ArrayList<>();
        answer8.add("Ken Ghosh");
        answer8.add("Vishal Bhardwaj");
        answer8.add("Anurag Kashyap");
        answer8.add("S. S. Rajamauli");


        ArrayList<String> answer9 = new ArrayList<>();
        answer9.add("Vicky Kaushal");
        answer9.add("Sidharth Malhotra");
        answer9.add("Dhanush");
        answer9.add("Ranveer Singh");

/// MCQ For question 10
        ArrayList<String> answer10 = new ArrayList<>();
        answer10.add("We Are");
        answer10.add("My Love");
        answer10.add("Help Yourself");
        answer10.add("This Time Tomorrow");

        questionList.add("Which movie won the top film prize at the Producers Guild of America (PGA) Awards in Feb 2024?");
        questionList.add("Who received the Best Debut Director Award at the 57th Maharashtra State Marathi Film Awards Ceremony?");
        questionList.add("In the Dadasaheb Phalke International Film Festival Awards 2024 who received the award for the Most Promising Actor?");
        questionList.add("During the Dadasaheb Phalke International Film Festival Awards 2024 who was honored with the Outstanding Contribution to the Film Industry award?");
        questionList.add("Who won the Best Actress in a Web Series award in the Dadasaheb Phalke International Film Festival Awards 2024");
        questionList.add("Which of the following song has won the \"Best original song\" award at Oscar 2023?");
        questionList.add("Which of the following is/are among the only Indian films to get nominated at the 94th Academy Awards?");
        questionList.add("Who among the followings has been awarded the best director award by the Dadasaheb Phalke International Film Festival Awards 2022?");
        questionList.add("Who among the following received the best actor in leading role (male) popular choice in the Filmfare Awards 2022?");
        questionList.add("Which album has won the 'Album of the Year' Grammy award 2022?");


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
        activityEntertainmentScreenBinding.answerRecyclerviewEntertainment.setAdapter(mcqAdapter);
        activityEntertainmentScreenBinding.answerRecyclerviewEntertainment.setLayoutManager(new LinearLayoutManager(this));

        activityEntertainmentScreenBinding.questionLabelEntertainment.setText("Question " + (pageIndex + 1) + " of 10");
        activityEntertainmentScreenBinding.questionEntertainment.setText(questionList.get(pageIndex));
        Intent intent = getIntent();

        Category categoryModel = (Category) intent.getSerializableExtra("title");
//        Integer background = intent.getIntExtra("background", 0);
        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});

        activityEntertainmentScreenBinding.layoutButton2Entertainment.setBackground(gradientDrawable);
        activityEntertainmentScreenBinding.layoutButton2Entertainment.setOnClickListener(v -> {
            try {
                if (!singletonExample.answersMap.isEmpty()) {
                    if (pageIndex < 9 && singletonExample.answersMap.size() > pageIndex) {
                        if (singletonExample.answersMap.get(pageIndex) != null) {
                            pageIndex = pageIndex + 1;
                            if (pageIndex == 9) {
                                activityEntertainmentScreenBinding.btnNextEntertainment.setText("Submit");
                            }

                            activityEntertainmentScreenBinding.questionLabelEntertainment.setText("Question " + (pageIndex + 1) + " of 10");
                            activityEntertainmentScreenBinding.questionEntertainment.setText(questionList.get(pageIndex));
                            MCQAdapter adapter = new MCQAdapter(answersList.get(pageIndex), this, finalAnswer[pageIndex], this);
                            activityEntertainmentScreenBinding.answerRecyclerviewEntertainment.setAdapter(adapter);
                            activityEntertainmentScreenBinding.answerRecyclerviewEntertainment.setLayoutManager(new LinearLayoutManager(this));
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