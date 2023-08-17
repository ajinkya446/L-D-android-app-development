package com.ajinkya.notetaking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> listLiveData;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        listLiveData = noteRepository.getNoteList();
    }

    public void insertNote(Note note) {
        noteRepository.insertNotes(note);
    }

    public void updateNote(Note note) {
        noteRepository.updateNotes(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNotes(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return listLiveData;
    }
}
