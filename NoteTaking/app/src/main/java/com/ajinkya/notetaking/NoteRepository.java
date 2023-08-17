package com.ajinkya.notetaking;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> noteList;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList = noteDao.getAllNotes();
    }

    public void insertNotes(Note note) {
        executorService.execute(() -> {
            noteDao.insertNote(note);
        });
//        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void updateNotes(Note note) {
        executorService.execute(() -> {
            noteDao.updateNote(note);
        });
//        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteNotes(Note note) {
        executorService.execute(() -> {
            noteDao.deleteDelete(note);
        });
//        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }
/*
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updateNote(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteDelete(notes[0]);
            return null;
        }
    }*/
}
