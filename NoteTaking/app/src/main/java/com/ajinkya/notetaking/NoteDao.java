package com.ajinkya.notetaking;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteDelete(Note note);

    @Query("SELECT * FROM note_tbl  ORDER BY  id DESC")
    LiveData<List<Note>> getAllNotes();
}
