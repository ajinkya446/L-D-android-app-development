package com.abc.notifiaction.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.abc.notifiaction.R;
import com.abc.notifiaction.adapter.ViewPager2Adapter;
import com.abc.notifiaction.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.getStarted.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("walkthrough", true);
        editor.apply();
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        activityMainBinding.viewpager.setAdapter(viewPager2Adapter);

        // To get swipe event of viewpager2
        activityMainBinding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 2) {
                    activityMainBinding.getStarted.setVisibility(View.VISIBLE);
                } else {
                    activityMainBinding.getStarted.setVisibility(View.INVISIBLE);
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
        activityMainBinding.getStarted.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intent);
            finish();
        });
    }
}