package com.abc.notifiaction;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.abc.notifiaction.databinding.ActivityHomeScreenBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class HomeScreen extends AppCompatActivity {
    private ActivityHomeScreenBinding activityHomeScreenBinding;
    // Initialize Firebase Auth
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);
        // Check if user is signed in (non-null) and update UI accordingly.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("672878074599-ocqjoollkuvto76c8sbulbpbi3a4fbfe.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(HomeScreen.this, googleSignInOptions);

        // Initialize firebase user
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            startActivity(new Intent(HomeScreen.this, DashboardScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        activityHomeScreenBinding.containedButton.setOnClickListener(v -> {
            // Initialize sign in intent
            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            someActivityResultLauncher.launch(intent);
        });
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // There are no request codes
                    Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
                    // check condition
                    if (signInAccountTask.isSuccessful()) {
                        // When google sign in successful initialize string
                        String s = "Google sign in successful";
                        // Initialize sign in account
                        try {
                            // Initialize sign in account
                            GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                            // Check condition
                            if (googleSignInAccount != null) {
                                // When sign in account is not equal to null initialize auth credential
                                AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                                // Check credential
                                mAuth.signInWithCredential(authCredential).addOnCompleteListener(HomeScreen.this, task -> {
                                    // Check condition
                                    if (task.isSuccessful()) {
                                        // When task is successful redirect to profile activity display Toast
                                        startActivity(new Intent(HomeScreen.this, DashboardScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        Toast.makeText(HomeScreen.this, "Authentication is successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // When task is unsuccessful display Toast
                                        Toast.makeText(HomeScreen.this, "Authentication Failed :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


}