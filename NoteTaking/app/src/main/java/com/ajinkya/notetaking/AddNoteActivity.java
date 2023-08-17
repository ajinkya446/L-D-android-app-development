package com.ajinkya.notetaking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    EditText editTextTitle, editTextDescription;
    Button buttonCancel, buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add new note");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_note);
        buttonCancel = findViewById(R.id.cancel);
        buttonSave = findViewById(R.id.saveNote);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextTitle = findViewById(R.id.editTextTitle);

        buttonSave.setOnClickListener(v -> saveNote());
        buttonCancel.setOnClickListener(v -> finish());
    }

    public void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        if (!title.equals("") && !description.equals("")) {
            Intent intent = new Intent();
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            setResult(RESULT_OK, intent);
            finish();

        } else {
            Toast.makeText(this, "Please enter Title and description for note", Toast.LENGTH_SHORT).show();
        }
    }
}