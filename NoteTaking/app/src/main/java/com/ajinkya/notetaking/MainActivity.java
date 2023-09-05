package com.ajinkya.notetaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    ActivityResultLauncher<Intent> intentActivityResultLauncher;
    ActivityResultLauncher<Intent> intentActivityResultUpdateLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerActivity();
        registerUpdateActivity();


        RecyclerView recyclerView = findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> {
//Updating recyclerview here.
            noteAdapter.setNotes(notes);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.deleteNote(noteAdapter.getNote(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted Successfully...", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        noteAdapter.setOnItemClickListener(note -> {
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("id", note.getId());
            intent.putExtra("title", note.getTitle());
            intent.putExtra("description", note.getDescription());

            ///Activity Launcher
            intentActivityResultUpdateLauncher.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return true;
    }

    ///Registering the object of launcher
    public void registerActivity() {
        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            int resultCode = result.getResultCode();
            Intent data = result.getData();
            if ((resultCode == RESULT_OK && data != null)) {
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");
                Note note = new Note(title, description);
                noteViewModel.insertNote(note);
                Toast.makeText(this, "Note Added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registerUpdateActivity() {
        intentActivityResultUpdateLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            int resultCode = result.getResultCode();
            Intent data = result.getData();
            if (resultCode == RESULT_OK && data != null) {
                String title = data.getStringExtra("titleLast");
                String description = data.getStringExtra("descriptionLast");
                int id = data.getIntExtra("noteID", -1);
                Note note = new Note(title, description);
                note.setId(id);
                noteViewModel.updateNote(note);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_menu:
                Intent intent = new Intent(this, AddNoteActivity.class);
//                startActivity(intent);

                /// Calling old method for getting result
//                startActivityForResult(intent, 1);

                /// getting result from updated functionality
                intentActivityResultLauncher.launch(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");

            Note note = new Note(title, description);
            noteViewModel.insertNote(note);
        }
    }*/
}