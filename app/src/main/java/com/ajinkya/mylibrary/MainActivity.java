package com.ajinkya.mylibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ajinkya.toasty.Toaster;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toaster.simpleToast(this, "Hello World");
    }
}