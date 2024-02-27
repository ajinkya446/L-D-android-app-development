package com.abc.notifiaction.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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

        Category categoryModel = (Category) intent.getSerializableExtra("title");
//        Integer background = intent.getIntExtra("background", 0);
        int startColor = Color.parseColor(categoryModel.getColorMap().get("start_color"));
        int centerColor = Color.parseColor(categoryModel.getColorMap().get("center_color"));
        int endColor = Color.parseColor(categoryModel.getColorMap().get("end_color"));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});

        materialToolbar.setTitle(categoryModel.getTitleName());
        materialToolbar.setBackground(gradientDrawable);
        linearLayout.setBackground(gradientDrawable);

        linearLayout.setOnClickListener(v -> {
            Intent quizIntent = null;
            if (categoryModel.getTitleName().equals("General Knowledge")) {
                quizIntent = new Intent(this, QuestionScreen.class);
            } else if (categoryModel.getTitleName().equals("Science")) {
                quizIntent = new Intent(this, ScienceQuestion.class);
            }
//
//            Toast.makeText(this, "Quiz will start in 3 seconds", Toast.LENGTH_SHORT).show();
            quizIntent.putExtra("title", categoryModel); // put image data in Intent
            // put image data in Intent
//            quizIntent.putExtra("background", background); // put image data in Intent
            startActivity(quizIntent);
        });
        materialToolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), DashboardScreen.class));
            finish();
        });
    }

}