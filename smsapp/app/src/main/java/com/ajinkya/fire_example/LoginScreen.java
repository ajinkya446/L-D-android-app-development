package com.ajinkya.fire_example;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonSignIn, buttonSignUp;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonSignIn = findViewById(R.id.buttonSignIN);
        buttonSignUp = findViewById(R.id.buttonSignUP);
        buttonSignIn.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    String emailData = user.getEmail();
                    if (emailData.toLowerCase() == editTextEmail.getText().toString().toLowerCase()) {
                        Intent intent = new Intent(LoginScreen.this, DashboardScreen.class);
                        startActivity(intent);
                        finish();
                    } else {
                        firebaseAuth.signOut();
                        signInDetails();
                    }
                } else {
                    signInDetails();
                }

            } else {
                Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
            }
        });
        buttonSignUp.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                registerUser();
            } else {
                Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /// Signing in the user as per the user details
    public void signInDetails() {
        firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(LoginScreen.this, DashboardScreen.class);
                            startActivity(intent);
                            finish();
                        } else {
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            Toast.makeText(LoginScreen.this, "User email does not exists please sign up once",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    /// Register the user details as per the EMAIL authentications.
    public void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginScreen.this, "Your account has been created successfully..", Toast.LENGTH_SHORT).show();
//                            updateUI(user);
                            Intent intent = new Intent(LoginScreen.this, DashboardScreen.class);
                            startActivity(intent);
                            finish();
                        } else {
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            Toast.makeText(LoginScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }
}