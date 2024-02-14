package com.abc.notifiaction.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.abc.notifiaction.R;
import com.abc.notifiaction.adapter.GridViewAdapter;
import com.abc.notifiaction.databinding.ActivityDashboardScreenBinding;
import com.abc.notifiaction.model.CategoryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardScreen extends AppCompatActivity {

    private ActivityDashboardScreenBinding activityDashboardScreenBinding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_screen);

        activityDashboardScreenBinding.topAppBar.setOnMenuItemClickListener(item -> {

            return true;
        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance()
                .getCurrentUser();
        assert firebaseUser != null;
        activityDashboardScreenBinding.topAppBar.setTitle(firebaseUser.getDisplayName());
        Picasso.get().load(Objects.requireNonNull(firebaseUser.getPhotoUrl()).toString()).into(activityDashboardScreenBinding.userImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.topAppBar);
        DrawerLayout drawer = findViewById(R.id.my_drawer_layout);

        toolbar.setNavigationOnClickListener(v -> {
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.END);
            }else{
                drawer.openDrawer(GravityCompat.START);
            }

        });
        activityDashboardScreenBinding.signOut.setOnClickListener(v -> {
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
        activityDashboardScreenBinding.gridview.setAdapter(gridViewAdapter);
        activityDashboardScreenBinding.gridview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, QuizList.class);
            intent.putExtra("title", courseModelArrayList.get(position).titleName); // put image data in Intent
            intent.putExtra("background", courseModelArrayList.get(position).drawable); // put image data in Intent
            startActivity(intent);
        });
    }
}