package com.ajinkya.notetaking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText editTextUpdateTitle, editTextUpdateDescription;
    Button buttonUpdateCancel, buttonUpdate;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit note");
        setContentView(R.layout.activity_update);
        editTextUpdateDescription = findViewById(R.id.updateTextDescription);
        editTextUpdateTitle = findViewById(R.id.updateTextTitle);
        buttonUpdateCancel = findViewById(R.id.cancelUpdate);
        buttonUpdate = findViewById(R.id.updateNote);
        getData();
        buttonUpdate.setOnClickListener(v -> updateNoteDetails());
        buttonUpdateCancel.setOnClickListener(v -> finish());
    }

    public void updateNoteDetails() {
        String title = editTextUpdateTitle.getText().toString();
        String description = editTextUpdateDescription.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("titleLast", title);
        intent.putExtra("descriptionLast", description);
        if (noteId != -1) {
            intent.putExtra("noteID", noteId);
            setResult(RESULT_OK, intent);
            finish();
        }
//

    }

    public void getData() {
        Intent i = getIntent();
        noteId = i.getIntExtra("id", -1);
        String titleString = i.getStringExtra("title");
        String descriptionString = i.getStringExtra("description");
        editTextUpdateTitle.setText(titleString);
        editTextUpdateDescription.setText(descriptionString);
    }
}