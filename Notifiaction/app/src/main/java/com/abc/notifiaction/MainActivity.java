package com.abc.notifiaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.abc.notifiaction.adapter.ViewPager2Adapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    Button buttonGetStarted;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewpager);
        buttonGetStarted = findViewById(R.id.getStarted);
        buttonGetStarted.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("walkthrough", true);
        editor.apply();
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter);

        // To get swipe event of viewpager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 2) {
                    buttonGetStarted.setVisibility(View.VISIBLE);
                } else {
                    buttonGetStarted.setVisibility(View.INVISIBLE);
                }
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        buttonGetStarted.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(intent);
        });
    }
}