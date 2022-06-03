package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView login = findViewById(R.id.loginbtn);
        Button signup = findViewById(R.id.button2);
        EditText firstname, lastname, email, password, confirmed_password, phone_number, age, username;
        RadioButton checked_gender_male, checked_gender_female;
        FirebaseAuth mAuth;
        final FirebaseUser[] user = new FirebaseUser[1];
        DatabaseReference myRef;
        firstname = findViewById(R.id.first_name_signup);
        lastname = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone);
        checked_gender_female = findViewById(R.id.female);
        checked_gender_male = findViewById(R.id.male);
        password = findViewById(R.id.edtSignUpPassword);
        confirmed_password = findViewById(R.id.edtSignUpConfirmPassword);
        age = findViewById(R.id.age);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users");
        login.setOnClickListener(v -> {
            Intent i = new Intent(SignupActivity.this, LogInActivity.class);
            SignupActivity.this.startActivity(i);
        });
        signup.setOnClickListener(v -> {

            String firstname_txt, lastname_txt, email_txt, password_txt, confirmed_password_txt, phone_number_txt, age_txt, gender_txt;
            firstname_txt = firstname.getText().toString().trim();
            lastname_txt = lastname.getText().toString().trim();
            email_txt = email.getText().toString().trim();
            password_txt = password.getText().toString().trim();
            confirmed_password_txt = confirmed_password.getText().toString().trim();
            phone_number_txt = phone_number.getText().toString().trim();
            age_txt = age.getText().toString().trim();
            if (checked_gender_female.isChecked()) {
                gender_txt = "female";
            } else {
                if (checked_gender_male.isChecked()) {
                    gender_txt = "male";
                } else {
                    gender_txt = "None";
                }
            }


            if (password_txt.equals(confirmed_password_txt)) {
                mAuth.createUserWithEmailAndPassword(email_txt, password_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser firebaseUser;
                        if (task.isSuccessful()) {
                            firebaseUser = mAuth.getCurrentUser();
                            //DatabaseReference new_firebaseUser=myRef.child(user[0].getUid());
                            DatabaseReference new_firebaseUser = myRef.child(firebaseUser.getUid());
                            UserModule newUser = new UserModule(firstname_txt, lastname_txt, age_txt, phone_number_txt, password_txt, gender_txt, email_txt);
                            new_firebaseUser.setValue(newUser);
                            if (firebaseUser.getUid().equals("fzPVimWQ2Gh0W7iOElNCOAfhKKJ2")) {
                                Intent i = new Intent(SignupActivity.this, AdminActivity.class);
                                SignupActivity.this.startActivity(i);
                            } else {
                                Intent i = new Intent(SignupActivity.this, Home.class);
                                SignupActivity.this.startActivity(i);
                            }

                        } else {
                            Toast.makeText(SignupActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}