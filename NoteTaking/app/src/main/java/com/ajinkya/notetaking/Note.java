package com.ajinkya.notetaking;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_tbl")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
