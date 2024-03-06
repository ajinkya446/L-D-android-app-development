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

import com.example.api_task.Adapter.AlbumAdapter;
import com.example.api_task.Model.Album;
import com.example.api_task.databinding.ActivityAlbumListBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumList extends AppCompatActivity {
    ActivityAlbumListBinding activityAlbumListBinding;
    private static final String base_url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAlbumListBinding = DataBindingUtil.setContentView(this, R.layout.activity_album_list);
        activityAlbumListBinding.recyclerViewAlbum.setVisibility(View.INVISIBLE);
        activityAlbumListBinding.indicatorAlbum.setVisibility(View.VISIBLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Album List");
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", 0);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService = retrofit.create(APIService.class);

        Call<List<Album>> listCall = apiService.getAlbumDetails(userId);
        listCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                Log.d(TAG, response.body().toString());
                AlbumAdapter albumAdapter = new AlbumAdapter(response.body());
                activityAlbumListBinding.recyclerViewAlbum.setAdapter(albumAdapter);
                activityAlbumListBinding.recyclerViewAlbum.setLayoutManager(
                        new LinearLayoutManager(AlbumList.this));
                activityAlbumListBinding.indicatorAlbum.setVisibility(View.INVISIBLE);
                activityAlbumListBinding.recyclerViewAlbum.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(AlbumList.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                activityAlbumListBinding.indicatorAlbum.setVisibility(View.INVISIBLE);
            }
        });
    }
}