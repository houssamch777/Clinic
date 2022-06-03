package com.example.clinic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UserProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        setup_profile();
        FloatingActionButton back_Button;
        back_Button = findViewById(R.id.profile_backButton);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfilActivity.this, Home.class);
                UserProfilActivity.this.startActivity(i);
            }
        });
        Button logout_btn;
        logout_btn = findViewById(R.id.profile_logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserProfilActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UserProfilActivity.this, LogInActivity.class);
                UserProfilActivity.this.startActivity(i);
            }
        });
        TextView edit_img;
        edit_img = findViewById(R.id.edit_img_profile);
        edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Avatar();

            }
        });


    }

    private void setup_profile() {


        FirebaseAuth mAuth_profile;
        mAuth_profile = FirebaseAuth.getInstance();
        FirebaseUser currentUser_profile = mAuth_profile.getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser_profile.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView email_profile, firstname_profile, lastname_profile, gender_profile, age_profile, phone_profile, name_profile;
                ImageView gender_profile_img;
                UserModule module_profile = snapshot.getValue(UserModule.class);
                email_profile = findViewById(R.id.profil_mail);
                email_profile.setText(module_profile.getEmail());
                firstname_profile = findViewById(R.id.profil_first_name);
                firstname_profile.setText(module_profile.getFirstname());
                lastname_profile = findViewById(R.id.profil_last_name);
                gender_profile = findViewById(R.id.profile_gender);
                age_profile = findViewById(R.id.profil_age);
                lastname_profile.setText(module_profile.getLastname());
                gender_profile.setText(module_profile.getGender());
                age_profile.setText(module_profile.getAge());
                phone_profile = findViewById(R.id.profil_phone);
                phone_profile.setText(module_profile.getPhone());
                name_profile = findViewById(R.id.profile_name);
                name_profile.setText(module_profile.getLastname() + " " + module_profile.getFirstname());
                gender_profile_img = findViewById(R.id.profile_gender_img);
                if (module_profile.getGender().equals("male")) {
                    gender_profile_img.setBackgroundResource(R.drawable.cartoon_blue_cute_gender);
                } else {
                    gender_profile_img.setBackgroundResource(R.drawable.cartoon_pink_cute_gender);
                }
                ImageView profile_img;
                profile_img = findViewById(R.id.imageprofile);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                storageRef.child(FirebaseAuth.getInstance().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(UserProfilActivity.this).load(uri).into(profile_img);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Add_Avatar() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        ProgressDialog progressDialog = new ProgressDialog(this);

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        if (requestCode == 100 && resultCode == RESULT_OK) {
            progressDialog.show();
            progressDialog.setMessage("uploading");
            Uri imageUri = data.getData();

            storageRef.child(FirebaseAuth.getInstance().getUid()).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    setup_profile();
                    progressDialog.dismiss();

                    Toast.makeText(UserProfilActivity.this, "Success upload", Toast.LENGTH_SHORT).show();


                }
            });
        }

    }
}
