package com.abc.notifiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class QuestionScreen extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        linearLayout = findViewById(R.id.layout_button2);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        Integer background = intent.getIntExtra("background", 0);
        linearLayout.setBackgroundResource(background);
    }
}