package com.ajinkya.smsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class SendMessageScreen extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_screen);
        toolbar = findViewById(R.id.sendMsgToolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        try {
            String title = intent.getStringExtra("title");
            String subTitle = intent.getStringExtra("subTitle");
            ActionBar actionbar = getSupportActionBar();
            actionbar.setTitle(title);
            toolbar.setSubtitle(subTitle);
        } catch (Exception e) {
            throw e;
        }
    }
}