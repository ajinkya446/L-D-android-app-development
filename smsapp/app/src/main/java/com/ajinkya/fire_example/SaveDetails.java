package com.ajinkya.fire_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SaveDetails extends AppCompatActivity {
    TextInputEditText textInputEditTextData;
    TextView textViewWelcome;

    Button buttonSubmit, buttonAuthenticate;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("users");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_details);
        textInputEditTextData = findViewById(R.id.data);
        buttonSubmit = findViewById(R.id.saveDetails);
        buttonAuthenticate = findViewById(R.id.authenticate);
        textViewWelcome = findViewById(R.id.welcomeName);

        buttonSubmit.setOnClickListener(v -> {
            if (textInputEditTextData.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter data to save...", Toast.LENGTH_SHORT).show();
            } else {
                String userName = textInputEditTextData.getText().toString();
                databaseReference.push().child("name").setValue(userName);
                Toast.makeText(this, "Data added successfully...", Toast.LENGTH_SHORT).show();
                textInputEditTextData.setText("");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ///Pulling the firebase data from using snapshot object.
                        String name = snapshot.child("name").getValue().toString();
                        textViewWelcome.setText("welcome" + " " + name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        buttonAuthenticate.setOnClickListener(v -> {
            Intent authenticationIntent = new Intent(this, LoginScreen.class);
            startActivity(authenticationIntent);
        });
    }
}