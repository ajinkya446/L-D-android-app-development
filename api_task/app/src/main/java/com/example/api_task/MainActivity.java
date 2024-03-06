package com.example.api_task;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.api_task.Adapter.UserAdapter;
import com.example.api_task.Model.User;
import com.example.api_task.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    private static final String base_url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.recyclerView.setVisibility(View.INVISIBLE);
        activityMainBinding.indicator.setVisibility(View.VISIBLE);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Home");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);

        Call<List<User>> listCall = apiService.getUserDetails();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, response.body().toString());
                UserAdapter userAdapter = new UserAdapter(response.body(), MainActivity.this);
                activityMainBinding.recyclerView.setAdapter(userAdapter);
                activityMainBinding.recyclerView.setLayoutManager(
                        new LinearLayoutManager(MainActivity.this));
                activityMainBinding.indicator.setVisibility(View.INVISIBLE);
                activityMainBinding.recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                activityMainBinding.indicator.setVisibility(View.INVISIBLE);
            }
        });

    }

}