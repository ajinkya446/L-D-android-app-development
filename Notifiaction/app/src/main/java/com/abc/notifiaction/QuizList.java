package com.abc.notifiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class QuizList extends AppCompatActivity {

    MaterialToolbar materialToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        materialToolbar = findViewById(R.id.topAppBar2);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        Integer background = intent.getIntExtra("background",0);

        materialToolbar.setTitle(title);
        materialToolbar.setBackgroundResource(background);
    }
}