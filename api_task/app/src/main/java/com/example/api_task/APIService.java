package com.example.api_task;

import com.example.api_task.Model.Album;
import com.example.api_task.Model.Todos;
import com.example.api_task.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("albums")
    Call<List<Album>> getAlbumDetails(@Query("userId") int userId);

    @GET("todos")
    Call<List<Todos>> getTodos(@Query("userId") int userId);

    @GET("users")
    Call<List<User>> getUserDetails();
}
