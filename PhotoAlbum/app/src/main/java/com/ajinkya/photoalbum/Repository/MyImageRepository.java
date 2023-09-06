package com.ajinkya.photoalbum.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ajinkya.photoalbum.DAO.MyImageDAO;
import com.ajinkya.photoalbum.DATABASE.MyImageDatabase;
import com.ajinkya.photoalbum.Model.MyImages;

import java.util.List;

public class MyImageRepository {

    private MyImageDAO myImageDAO;
    private LiveData<List<MyImages>> imageList;

    public MyImageRepository(Application application) {
        MyImageDatabase database = MyImageDatabase.getInstance(application);
        myImageDAO = database.myImageDAO();
        imageList = myImageDAO.getAllImages();
    }

    public void insert(MyImages image) {
        new InsertAsyncTask(myImageDAO).execute(image);
    }

    public void update(MyImages image) {
    }

    public void delete(MyImages image) {
    }

    public LiveData<List<MyImages>> getAllImageList() {
        return imageList;
    }

    private static class InsertAsyncTask extends AsyncTask<MyImages, Void, Void> {
        MyImageDAO dao;

        public InsertAsyncTask(MyImageDAO dao) {
            this.dao = dao;
        }

        @Override
        public Void doInBackground(MyImages... myImages) {
            dao.insertImage(myImages[0]);
            return null;
        }
    }
}
