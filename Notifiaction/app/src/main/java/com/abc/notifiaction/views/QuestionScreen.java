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
import com.abc.notifiaction.databinding.ActivityQuestionScreenBinding;
import com.abc.notifiaction.interfaces.RecyclerViewInterface;
import com.abc.notifiaction.model.Category;
import com.abc.notifiaction.model.SingletonExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuestionScreen extends AppCompatActivity implements RecyclerViewInterface {
    private ActivityQuestionScreenBinding activityQuestionScreenBinding;

    ArrayList<String> questionList = new ArrayList<>();
    String[] finalAnswer = {"Chambal", "Karnataka", "November 12", "25 years", "2019",
            "The ratio of the length to the height of the flag is 3:2", "Option 1 and 2", "Punjab",
            "5+3+3+4", "Chlorine"};
    ArrayList<ArrayList<String>> answersList = new ArrayList<>();
//    public ArrayList<Boolean> answersMap = new ArrayList<>();
    int pageIndex = 0;
    SingletonExample singletonExample=new SingletonExample();

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityQuestionScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_screen);

        /// MCQ For Question 1
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("Indus");
        answer1.add("Brahmaputra");
        answer1.add("Chambal");
        answer1.add("Kosi");

        /// MCQ For question 2
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("Andhra Pradesh");
        answer2.add("Telangana");
        answer2.add("Tamil Nadu");
        answer2.add("Karnataka");

        /// MCQ For question 3
        ArrayList<String> answer3 = new ArrayList<>();
        answer3.add("December 21");
        answer3.add("March 7");
        answer3.add("September 3");
        answer3.add("November 12");

        /// MCQ For question 4
        ArrayList<String> answer4 = new ArrayList<>();
        answer4.add("30 years");
        answer4.add("18 years");
        answer4.add("25 years");
        answer4.add("21 years");

        /// MCQ For question 5
        ArrayList<String> answer5 = new ArrayList<>();
        answer5.add("2018");
        answer5.add("2019");
        answer5.add("2014");
        answer5.add("2016");

        /// MCQ For question 6
        ArrayList<String> answer6 = new ArrayList<>();
        answer6.add("The ratio of the length to the height of the flag is 3:2");
        answer6.add("The ratio of the length to the height of the flag is 2:3");
        answer6.add("The ratio of the length to the height of the flag is 3:3");
        answer6.add("None of the above");

        /// MCQ For question 7
        ArrayList<String> answer7 = new ArrayList<>();
        answer7.add("Africa South of Sahara");
        answer7.add("South Asia");
        answer7.add("Option 1 and 2");
        answer7.add("Europe");

        /// MCQ For question 8
        ArrayList<String> answer8 = new ArrayList<>();
        answer8.add("Haryana");
        answer8.add("Punjab");
        answer8.add("Maharashtra");
        answer8.add("Gujarat");

        /// MCQ For question 9
        ArrayList<String> answer9 = new ArrayList<>();
        answer9.add("3+4+5+3");
        answer9.add("3+5+3+4");
        answer9.add("5+3+3+4");
        answer9.add("4+3+5+3");

        /// MCQ For question 10
        ArrayList<String> answer10 = new ArrayList<>();
        answer10.add("Calcium");
        answer10.add("Chlorine");
        answer10.add("Phosphorus");
        answer10.add("Bromine");

        questionList.add("Which river among the following starts its Journey and ends its journey in India itself?");
        questionList.add("The dam Krishna Raja Sagara is located in which state?");
        questionList.add("Which day is known as World Pneumonia Day?");
        questionList.add("What is the minimum age to be a Chief Minister of any state in India?");
        questionList.add("In which year was the late Shri. Pranab Mukherjee awarded the Bharat Ratna?");
        questionList.add("Which statement is true among the following?");
        questionList.add("According to the Global Hunger Index, which among the following regions has the highest hunger and undernutrition levels in the world?");
        questionList.add("Which state of India is called the Land of Five Rivers?");
        questionList.add("With changes in the education policy, the 10+2 system was abolished and replaced by which system by the NEP 2020?");
        questionList.add("Which ion is the most dissolved in ocean water?");

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
        activityQuestionScreenBinding.answerRecyclerview.setAdapter(mcqAdapter);
        activityQuestionScreenBinding.answerRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityQuestionScreenBinding.questionLabel.setText("Question " + (pageIndex + 1) + " of 10");
        activityQuestionScreenBinding.question.setText(questionList.get(pageIndex));
        Intent intent = getIntent();
        Category categoryModel = (Category) intent.getSerializableExtra("title");

        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});
        activityQuestionScreenBinding.layoutButton2.setBackground(gradientDrawable);
        activityQuestionScreenBinding.layoutButton2.setOnClickListener(v -> {
            try {
                if (!singletonExample.answersMap.isEmpty()) {
                    if (pageIndex < 9 && singletonExample.answersMap.size() > pageIndex) {
                        if (singletonExample.answersMap.get(pageIndex) != null) {
                            pageIndex = pageIndex + 1;
                            if (pageIndex == 9) {
                                activityQuestionScreenBinding.btnNext.setText("Submit");
                            }

                            activityQuestionScreenBinding.questionLabel.setText("Question " + (pageIndex + 1) + " of 10");
                            activityQuestionScreenBinding.question.setText(questionList.get(pageIndex));
                            MCQAdapter adapter = new MCQAdapter(answersList.get(pageIndex), this, finalAnswer[pageIndex], this);
                            activityQuestionScreenBinding.answerRecyclerview.setAdapter(adapter);
                            activityQuestionScreenBinding.answerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
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
        isFinish("Want to exit from test?");
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