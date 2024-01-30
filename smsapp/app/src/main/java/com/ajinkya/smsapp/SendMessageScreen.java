package com.ajinkya.smsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SendMessageScreen extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imageViewSend, imageViewMic;
    EditText editTextMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_screen);
        toolbar = findViewById(R.id.sendMsgToolbar);
        imageViewMic = findViewById(R.id.micButton);
        imageViewSend = findViewById(R.id.sendButton);
        editTextMessage = findViewById(R.id.edit_text);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        try {
            String title = intent.getStringExtra("title");
            String subTitle = intent.getStringExtra("subTitle");
            ActionBar actionbar = getSupportActionBar();
            actionbar.setTitle(title);
            toolbar.setSubtitle(subTitle);
            imageViewSend.setOnClickListener(v -> {
                if (editTextMessage.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please enter message to send", Toast.LENGTH_SHORT).show();
                } else {
                    sendSMS(subTitle);
                }
            });
            imageViewMic.setOnClickListener(v -> {
                convertSpeech();
            });
        } catch (Exception e) {
            throw e;
        }
    }

    private void convertSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100 && resultCode == RESULT_OK) {
            ArrayList<String> speakResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            editTextMessage.setText(speakResult.get(0));
        }
    }

    private void sendSMS(String phone) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, editTextMessage.getText().toString(), null, null);
            Toast.makeText(SendMessageScreen.this, "Message Send successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }
    }
}