package com.abc.notifiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.abc.notifiaction.adapter.GridViewAdapter;
import com.abc.notifiaction.model.CategoryModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardScreen extends AppCompatActivity {
    MaterialToolbar toolbar;
    ImageView imageView;
    Button button;
    GridView gridView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);
        toolbar = findViewById(R.id.topAppBar);
        imageView = findViewById(R.id.userImage);
        button = findViewById(R.id.signOut);
        gridView = findViewById(R.id.gridview);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance()
                .getCurrentUser();
        toolbar.setTitle(firebaseUser.getDisplayName());
        Picasso.get().load(firebaseUser.getPhotoUrl().toString()).into(imageView);

        button.setOnClickListener(v -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            finish();
        });

        ArrayList<CategoryModel> courseModelArrayList = new ArrayList<>();

        courseModelArrayList.add(new CategoryModel(R.drawable.gk, "General Knowledge", R.drawable.bg2));
        courseModelArrayList.add(new CategoryModel(R.drawable.science, "Science", R.drawable.bg1));
        courseModelArrayList.add(new CategoryModel(R.drawable.maths, "Maths", R.drawable.bg2));
        courseModelArrayList.add(new CategoryModel(R.drawable.entertainment, "Entertainment", R.drawable.bg3));
        courseModelArrayList.add(new CategoryModel(R.drawable.sports, "Sport", R.drawable.bg4));
        courseModelArrayList.add(new CategoryModel(R.drawable.tech, "Technology", R.drawable.bg1));

        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, courseModelArrayList);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, QuizList.class);
            intent.putExtra("title", courseModelArrayList.get(position).titleName); // put image data in Intent
            intent.putExtra("background", courseModelArrayList.get(position).drawable); // put image data in Intent
            startActivity(intent);
        });
    }
}