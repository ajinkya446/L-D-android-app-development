package com.example.api_task;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.api_task.Adapter.TodoAdapter;
import com.example.api_task.Model.Todos;
import com.example.api_task.databinding.ActivityTodosListBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodosList extends AppCompatActivity {
    ActivityTodosListBinding activityTodosListBinding;
    private static final String base_url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTodosListBinding= DataBindingUtil.setContentView(this,R.layout.activity_todos_list);
        activityTodosListBinding.recyclerViewTodo.setVisibility(View.INVISIBLE);
        activityTodosListBinding.indicatorTodo.setVisibility(View.VISIBLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TODO's");
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", 0);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);

        Call<List<Todos>> listCall = apiService.getTodos(userId);
        listCall.enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {
                Log.d(TAG, response.body().toString());
                TodoAdapter todoAdapter = new TodoAdapter(response.body());
                activityTodosListBinding.recyclerViewTodo.setAdapter(todoAdapter);
                activityTodosListBinding.recyclerViewTodo.setLayoutManager(
                        new LinearLayoutManager(TodosList.this));
                activityTodosListBinding.indicatorTodo.setVisibility(View.INVISIBLE);
                activityTodosListBinding.recyclerViewTodo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {
                Toast.makeText(TodosList.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                activityTodosListBinding.indicatorTodo.setVisibility(View.INVISIBLE);
            }
        });
    }
}