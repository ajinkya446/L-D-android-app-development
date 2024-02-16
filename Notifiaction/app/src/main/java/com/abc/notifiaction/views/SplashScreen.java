package com.abc.notifiaction.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.abc.notifiaction.R;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreen extends AppCompatActivity {
    GifImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageView);

        /// Creating animation
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
//        imageView.startAnimation(animation);
//        imageView.setImageResource(R.drawable.test);
//        Picasso.get().load(R.drawable.test).into(imageView);
        /// Adding handler
        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_preference", MODE_PRIVATE);
            boolean value = sharedPreferences.getBoolean("walkthrough", false);
            Intent intent;
            if (value) {
                intent = new Intent(SplashScreen.this, LoginScreen.class);
            } else {
                intent = new Intent(SplashScreen.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 3000);
    }
}