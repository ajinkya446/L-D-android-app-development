package com.ajinkya.notetaking;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            NoteDao noteDao = instance.noteDao();
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                noteDao.insertNote(new Note("title 1", "sfsfa"));
                noteDao.insertNote(new Note("title 2", "sfsfa"));
                noteDao.insertNote(new Note("title 3", "sfsfa"));
                noteDao.insertNote(new Note("title 4", "sfsfa"));
            });
//            new PopulateAsyncTask(instance).execute();
        }
    };
/*
    private static class PopulateAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public PopulateAsyncTask(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(new Note("title 1", "sfsfa"));
            noteDao.insertNote(new Note("title 2", "sfsfa"));
            noteDao.insertNote(new Note("title 3", "sfsfa"));
            noteDao.insertNote(new Note("title 4", "sfsfa"));
            return null;
        }
    }*/
}
