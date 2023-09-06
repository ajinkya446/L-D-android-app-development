package com.ajinkya.photoalbum.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ajinkya.photoalbum.Model.MyImages;

import java.util.List;

@Dao
public interface MyImageDAO {
    @Insert
    void insertImage(MyImages image);

    @Delete
    void deleteImage(MyImages images);

    @Update
    void updateImageDetails(MyImages images);

    @Query("SELECT * FROM my_images ORDER BY imageId ASC")
    LiveData<List<MyImages>> getAllImages();
}
