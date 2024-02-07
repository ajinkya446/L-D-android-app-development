package com.abc.notifiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageView);

        /// Creating animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
        imageView.startAnimation(animation);
        imageView.setImageResource(R.drawable.img);

        /// Adding handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_preference", MODE_PRIVATE);
                boolean value = sharedPreferences.getBoolean("walkthrough", false);
                Intent intent;
                if (value) {
                    intent = new Intent(SplashScreen.this, HomeScreen.class);
                } else {
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}