package com.abc.notifiaction.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.abc.notifiaction.R;
import com.abc.notifiaction.databinding.ActivityRewardScreenBinding;

public class RewardScreen extends AppCompatActivity {
    private ActivityRewardScreenBinding activityRewardScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRewardScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_reward_screen);

        Intent intent = getIntent();
        String resultScore = intent.getExtras().getString("score");
        activityRewardScreenBinding.scoreResult.setText(resultScore + "/10");
        activityRewardScreenBinding.layoutButtonReward.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), DashboardScreen.class));
            finish();
        });
    }
}