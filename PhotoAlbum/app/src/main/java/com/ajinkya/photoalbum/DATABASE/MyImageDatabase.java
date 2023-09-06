package com.ajinkya.photoalbum.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ajinkya.photoalbum.DAO.MyImageDAO;
import com.ajinkya.photoalbum.Model.MyImages;

@Database(entities = MyImages.class, version = 1)
public abstract class MyImageDatabase extends RoomDatabase {
    private static MyImageDatabase instance;

    public abstract MyImageDAO myImageDAO();

    public static synchronized MyImageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyImageDatabase.class, "my_image_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
