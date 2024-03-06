package com.abc.notifiaction.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.abc.notifiaction.R;
import com.abc.notifiaction.model.Category;
import com.google.android.material.appbar.MaterialToolbar;

public class QuizList extends AppCompatActivity {
    ImageView imageViewTimer, imageViewQuestion, imageViewBadge;
    LinearLayout linearLayout;
    MaterialToolbar materialToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        materialToolbar = findViewById(R.id.topAppBar2);
        imageViewBadge = findViewById(R.id.badgeImage);
        imageViewQuestion = findViewById(R.id.questionImage);
        imageViewTimer = findViewById(R.id.timerImage);
        linearLayout = findViewById(R.id.layout_button);
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int value = AppCompatDelegate.getDefaultNightMode();
        Log.d("Mode:", String.valueOf(value));
        String themeColor = value == 2 ? "#ffffff" : "#000000";

        Category categoryModel = (Category) intent.getSerializableExtra("title");
//        Integer background = intent.getIntExtra("background", 0);
        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});

        materialToolbar.setTitle(categoryModel.getTitleName());
        materialToolbar.setTitleTextColor(Color.parseColor(themeColor));
        materialToolbar.setNavigationIconTint(Color.parseColor(themeColor));
        materialToolbar.setBackground(gradientDrawable);
        linearLayout.setBackground(gradientDrawable);

        linearLayout.setOnClickListener(v -> {
            Intent quizIntent = null;
            if (categoryModel.getTitleName().equals("General Knowledge")) {
                quizIntent = new Intent(this, QuestionScreen.class);
                openExamPage(quizIntent, categoryModel);
            } else if (categoryModel.getTitleName().equals("Science")) {
                quizIntent = new Intent(this, ScienceQuestion.class);
                openExamPage(quizIntent, categoryModel);
            } else if (categoryModel.getTitleName().equals("Entertainment")) {
                quizIntent = new Intent(this, EntertainmentScreen.class);
                openExamPage(quizIntent, categoryModel);
            } else if (categoryModel.getTitleName().equals("Sports")) {
                quizIntent = new Intent(this, SportsQuestions.class);
                openExamPage(quizIntent, categoryModel);
            } else {
                Toast.makeText(this, "This Test not been setup", Toast.LENGTH_SHORT).show();
            }
           /* quizIntent.putExtra("title", categoryModel);
            startActivity(quizIntent);*/
        });
        materialToolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), DashboardScreen.class));
            finish();
        });
    }

    void openExamPage(Intent quizIntent, Category categoryModel) {
        quizIntent.putExtra("title", categoryModel);
        startActivity(quizIntent);
    }

}