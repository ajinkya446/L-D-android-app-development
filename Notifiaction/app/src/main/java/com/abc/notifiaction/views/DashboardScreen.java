package com.abc.notifiaction.views;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.abc.notifiaction.model.Category;
import com.abc.notifiaction.model.CategoryModel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DashboardScreen extends AppCompatActivity {

    private ActivityDashboardScreenBinding activityDashboardScreenBinding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        ArrayList<CategoryModel> categoryArrayList = new ArrayList<>();
        ArrayList<Category> mapCategories = new ArrayList<>();


        /// Getting Detailed collection table data from FIREBASE Firestore.
        db.collection("quiz_category").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> details = document.getData();
                    int startColor = 0;
                    int centerColor = 0;
                    int endColor = 0;
                    Category category = null;
                    Map<String, String> colorMap = new HashMap<>();
                    String imageUrl = "";
                    GradientDrawable gradientDrawable = null;
                    if (details.containsKey("image_url")) {
                        imageUrl = details.get("image_url").toString();
                    }
                    if (details.containsKey("gradient_color")) {
                        Map<String, String> colors = (Map<String, String>) details.get("gradient_color");
                        if (colors.containsKey("center_color")) {
                            colorMap.put("center_color", colors.get("center_color").toString());
                            int colorInner = Color.parseColor(colors.get("center_color").toString());
                            centerColor = colorInner;
                        }
                        if (colors.containsKey("start_color")) {
                            colorMap.put("start_color", colors.get("start_color").toString());
                            int colorInner = Color.parseColor(colors.get("start_color").toString());
                            startColor = colorInner;
                        }
                        if (colors.containsKey("end_color")) {
                            colorMap.put("end_color", colors.get("center_color").toString());
                            int colorInner = Color.parseColor(colors.get("end_color").toString());
                            endColor = colorInner;
                        }
                        gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, centerColor, endColor});
                    }
                    category = new Category(imageUrl, details.get("cat_name").toString(), colorMap);
                    CategoryModel categoryModel = new CategoryModel(imageUrl, details.get("cat_name").toString(), gradientDrawable);
                    Log.d(TAG, document.getId() + " => " + document.getData());
                    categoryArrayList.add(categoryModel);
                    mapCategories.add(category);
                }
                GridViewAdapter gridViewAdapter = new GridViewAdapter(this, categoryArrayList);
                activityDashboardScreenBinding.gridview.setAdapter(gridViewAdapter);
                activityDashboardScreenBinding.gridview.setOnItemClickListener((parent, view, position, id) -> {

                    if (mapCategories.get(position).getTitleName().equals("General Knowledge")) {
                        openExamPage(mapCategories.get(position));
                    } else if (mapCategories.get(position).getTitleName().equals("Science")) {
                        openExamPage(mapCategories.get(position));
                    } else {
                        Toast.makeText(this, "This Test not been setup", Toast.LENGTH_SHORT).show();
                    }

                });
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    void openExamPage(Category categoryModel) {
        Intent intent = new Intent(this, QuizList.class);
        intent.putExtra("title", categoryModel); // put image data in Intent
        startActivity(intent);
    }
}