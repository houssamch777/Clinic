package com.example.clinic;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        Button login = findViewById(R.id.button);
        TextView signup = findViewById(R.id.signupbtn);
        EditText email = findViewById(R.id.email_login);
        EditText password = findViewById(R.id.password_login);
        signup.setOnClickListener(v -> {
            Intent i = new Intent(LogInActivity.this, SignupActivity.class);
            LogInActivity.this.startActivity(i);
        });
        login.setOnClickListener(v -> {
            String email_txt, password_txt;
            email_txt = email.getText().toString().trim();
            password_txt = password.getText().toString().trim();
            if (!email_txt.isEmpty() && !password_txt.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email_txt, password_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mAuth.getCurrentUser().getUid().equals("fzPVimWQ2Gh0W7iOElNCOAfhKKJ2")) {
                                Intent i = new Intent(LogInActivity.this, AdminActivity.class);
                                LogInActivity.this.startActivity(i);
                            } else {
                                Intent i = new Intent(LogInActivity.this, Home.class);
                                LogInActivity.this.startActivity(i);
                            }


                        } else {
                            Toast.makeText(LogInActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(LogInActivity.this, "please entre your mail and password .", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            if (currentUser.getUid().equals("fzPVimWQ2Gh0W7iOElNCOAfhKKJ2")) {
                Intent i = new Intent(LogInActivity.this, AdminActivity.class);
                LogInActivity.this.startActivity(i);
            } else {
                Intent i = new Intent(LogInActivity.this, Home.class);
                LogInActivity.this.startActivity(i);
            }

        }
    }
}