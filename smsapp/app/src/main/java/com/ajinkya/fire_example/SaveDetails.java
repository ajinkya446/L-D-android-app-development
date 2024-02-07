package com.ajinkya.fire_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class SaveDetails extends AppCompatActivity {
    TextInputEditText textInputEditTextData, textInputEditTextOTP;
    TextView textViewWelcome;

    Button buttonSubmit, buttonAuthenticate, btnSendOTP;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("users");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String codeSent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_details);
        textInputEditTextData = findViewById(R.id.data);
        textInputEditTextOTP = findViewById(R.id.dataOTP);
        buttonSubmit = findViewById(R.id.saveDetails);
        buttonAuthenticate = findViewById(R.id.authenticate);
        btnSendOTP = findViewById(R.id.phone);
        textViewWelcome = findViewById(R.id.welcomeName);

        textInputEditTextOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codeSent, textInputEditTextOTP.getText().toString());
                firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(SaveDetails.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SaveDetails.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                codeSent = s;
            }
        };
        btnSendOTP.setOnClickListener(v -> {
            PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber("8446619483").setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(SaveDetails.this).setCallbacks(mCallback).build();
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
        });

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