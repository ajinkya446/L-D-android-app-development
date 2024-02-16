package com.abc.notifiaction.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.abc.notifiaction.R;
import com.abc.notifiaction.adapter.GridViewAdapter;
import com.abc.notifiaction.databinding.ActivityDashboardScreenBinding;
import com.abc.notifiaction.model.CategoryModel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardScreen extends AppCompatActivity {

    private ActivityDashboardScreenBinding activityDashboardScreenBinding;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_screen);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance()
                .getCurrentUser();
        assert firebaseUser != null;
        activityDashboardScreenBinding.topAppBar.setTitle(firebaseUser.getDisplayName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.topAppBar);
        DrawerLayout drawer = findViewById(R.id.my_drawer_layout);
        View headerLayout =
                activityDashboardScreenBinding.navView.inflateHeaderView(R.layout.drawer_layout);
        ImageView imageViewUser = headerLayout.findViewById(R.id.userImage);
        TextView userName = headerLayout.findViewById(R.id.userName);
        TextView userEmail = headerLayout.findViewById(R.id.email);
        LinearLayout linearLayoutProfile = headerLayout.findViewById(R.id.profile_menu);
        LinearLayout linearLayoutSetting = headerLayout.findViewById(R.id.layoutSetting);
        LinearLayout linearLayoutExit = headerLayout.findViewById(R.id.exitMenu);
        SwitchMaterial switchMaterial = headerLayout.findViewById(R.id.theme);
        switchMaterial.setText("Light Theme");
        switchMaterial.setChecked(false);
        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isChecked()) {
                buttonView.setText("Dark Theme");
                switchMaterial.setChecked(isChecked);
            } else {
                buttonView.setText("Light Theme");
                switchMaterial.setChecked(isChecked);
            }
        });


        Picasso.get().load(Objects.requireNonNull(firebaseUser.getPhotoUrl()).toString()).into(imageViewUser);
        userName.setText(firebaseUser.getDisplayName());
        userEmail.setText(firebaseUser.getEmail());

        linearLayoutProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Still Under Development", Toast.LENGTH_SHORT).show();
        });

        linearLayoutSetting.setOnClickListener(v -> {
            Toast.makeText(this, "Still Under Development", Toast.LENGTH_SHORT).show();
        });

        linearLayoutExit.setOnClickListener(v -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            Toast.makeText(this, "User logged out...", Toast.LENGTH_SHORT).show();
            finish();
        });

        toolbar.setNavigationOnClickListener(v -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }

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