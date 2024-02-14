package com.abc.notifiaction.views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abc.notifiaction.R;
import com.abc.notifiaction.databinding.ActivityLoginScreenBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {
    private ActivityLoginScreenBinding activityHomeScreenBinding;
    // Initialize Firebase Auth
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        // Check if user is signed in (non-null) and update UI accordingly.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("672878074599-ocqjoollkuvto76c8sbulbpbi3a4fbfe.apps.googleusercontent.com")
                .requestEmail()
                .build();
        activityHomeScreenBinding.indicator.setVisibility(View.INVISIBLE);
        // Initialize sign in client
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(LoginScreen.this, googleSignInOptions);

        // Initialize firebase user
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            startActivity(new Intent(LoginScreen.this, DashboardScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        activityHomeScreenBinding.containedButton.setOnClickListener(v -> {
            if (activityHomeScreenBinding.indicator.getVisibility() == View.INVISIBLE) {
                activityHomeScreenBinding.indicator.getIndeterminateDrawable().setColorFilter(0xff0B05F6, android.graphics.PorterDuff.Mode.MULTIPLY);
                activityHomeScreenBinding.indicator.setVisibility(View.VISIBLE);
                Intent intent = googleSignInClient.getSignInIntent();
                someActivityResultLauncher.launch(intent);
            } else {
                // Either gone or invisible
                Toast.makeText(this, "Please wait for sometime to complete operation first...", Toast.LENGTH_SHORT).show();
            }

        });
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
                    if (signInAccountTask.isSuccessful()) {
                        try {
                            GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                            if (googleSignInAccount != null) {
                                AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                                mAuth.signInWithCredential(authCredential).addOnCompleteListener(LoginScreen.this, task -> {
                                    if (task.isSuccessful()) {
                                        activityHomeScreenBinding.indicator.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(LoginScreen.this, DashboardScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        Toast.makeText(LoginScreen.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(LoginScreen.this, "Login Failed :" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (ApiException e) {
                            e.printStackTrace();
                            activityHomeScreenBinding.indicator.setVisibility(View.INVISIBLE);
                        }
                    }
                } else {
                    activityHomeScreenBinding.indicator.setVisibility(View.INVISIBLE);
                    Log.d("Result", result.toString());
                    Toast.makeText(this, "Cant Login", Toast.LENGTH_SHORT).show();
                }
            });


}