package com.ajinkya.fire_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ajinkya.fire_example.databinding.ActivityDashboardScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardScreen extends AppCompatActivity {
    ActivityDashboardScreenBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser userData = firebaseAuth.getCurrentUser();
        /// Added data biding for the screen.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_screen);
//        binding.nameText.setText("Welcome" + " " + user.getEmail());
        UserModel userModel= new UserModel(userData.getEmail(),"*******");
        binding.setData(userModel);

    }
}