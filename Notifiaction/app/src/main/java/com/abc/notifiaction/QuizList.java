package com.abc.notifiaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

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

        String title = intent.getStringExtra("title");
        Integer background = intent.getIntExtra("background", 0);

        materialToolbar.setTitle(title);
        materialToolbar.setBackgroundResource(background);
        linearLayout.setBackgroundResource(background);

        linearLayout.setOnClickListener(v -> {
            Intent quizIntent = new Intent(getApplicationContext(), QuestionScreen.class);
//            Toast.makeText(this, "Quiz will start in 3 seconds", Toast.LENGTH_SHORT).show();
            quizIntent.putExtra("title", title); // put image data in Intent
            quizIntent.putExtra("background", background); // put image data in Intent
            startActivity(quizIntent);
        });
        materialToolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), DashboardScreen.class));
            finish();
        });
    }

}