package com.example.mylibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mylibrary.databinding.ActivityDashboardScreenBinding;

public class DashboardScreen extends AppCompatActivity {
    ActivityDashboardScreenBinding activityDashboardScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_screen);
    activityDashboardScreenBinding.btn.setOnClickListener(v->{
        finish();
    });
    }
}