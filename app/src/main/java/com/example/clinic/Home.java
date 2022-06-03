package com.example.clinic;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class Home extends AppCompatActivity {


    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setup();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home2) {
                    Home.this.recreate();
                }
                if (item.getItemId() == R.id.search2) {
                    Intent i = new Intent(Home.this, DoctorsListActivity.class);
                    i.putExtra("specialty", "all");
                    Home.this.startActivity(i);
                    finish();
                }
                if (item.getItemId() == R.id.profile2) {
                    Intent i = new Intent(Home.this, UserProfilActivity.class);
                    Home.this.startActivity(i);
                }
                if (item.getItemId() == R.id.logout2) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(Home.this, "LogOut", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Home.this, LogInActivity.class);
                    Home.this.startActivity(i);

                }
                return item.getItemId() != R.id.placeholder2;
            }
        });
        imgClick();
    }

    void imgClick() {
        ImageView cardiology = findViewById(R.id.cardiology_img);
        ImageView gynecology = findViewById(R.id.gynecologyimg);
        ImageView ophthalmology = findViewById(R.id.ophthalmologyimg);
        ImageView facial_plastic_surgery = findViewById(R.id.facial_plastic_surgeryimg);
        ImageView oral_health = findViewById(R.id.oral_healthimg);
        ImageView orthopedics = findViewById(R.id.orthopedicsimg);
        ImageView rhinology = findViewById(R.id.rhinologyimg);
        ImageView neurology = findViewById(R.id.neurologyimg);
        ImageView dermatology = findViewById(R.id.dermatology_img);
        ImageView neurosurgery = findViewById(R.id.neurosurgeryimg);
        ImageView pulmonology = findViewById(R.id.pulmonology_img);
        ImageView hepatology = findViewById(R.id.hepatologyimg);
        ImageView urology = findViewById(R.id.urologyimg);
        ImageView otology = findViewById(R.id.otologyimg);
        ImageView gastroenterology = findViewById(R.id.gastroenterologyimg);
        ImageView plastic_surgery = findViewById(R.id.plastic_surgeryimg);
        cardiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "cardiology");
                Home.this.startActivity(i);
            }
        });
        gynecology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "gynecology");
                Home.this.startActivity(i);
            }
        });
        ophthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "ophthalmology");
                Home.this.startActivity(i);
            }
        });
        facial_plastic_surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "facial plastic surgery");
                Home.this.startActivity(i);
            }
        });
        oral_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "oral health");
                Home.this.startActivity(i);
            }
        });
        orthopedics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "orthopedics");
                Home.this.startActivity(i);
            }
        });
        rhinology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "rhinology");
                Home.this.startActivity(i);
            }
        });
        neurology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "neurology");
                Home.this.startActivity(i);
            }
        });
        dermatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "dermatology");
                Home.this.startActivity(i);
            }
        });
        neurosurgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "neurosurgery");
                Home.this.startActivity(i);
            }
        });
        pulmonology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "pulmonology");
                Home.this.startActivity(i);
            }
        });
        hepatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "hepatology");
                Home.this.startActivity(i);
            }
        });
        urology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "urology");
                Home.this.startActivity(i);
            }
        });
        gastroenterology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "gastroenterology");
                Home.this.startActivity(i);
            }
        });
        plastic_surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "plastic surgery");
                Home.this.startActivity(i);
            }
        });
        otology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DoctorsListActivity.class);
                i.putExtra("specialty", "otology");
                Home.this.startActivity(i);
            }
        });
    }

    private void setup() {
        UserModule module = new UserModule();
        FirebaseAuth mAuth_home;
        mAuth_home = FirebaseAuth.getInstance();
        FirebaseUser currentUser_home = mAuth_home.getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser_home.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView email_home, username_home;
                ImageView imageView_home;
                UserModule module = snapshot.getValue(UserModule.class);
                email_home = findViewById(R.id.home_email);
                imageView_home = findViewById(R.id.imageView_home);
                if (module.getGender().equals("male")) {
                    imageView_home.setBackgroundResource(R.drawable.cartoon_blue_cute_gender);
                } else {
                    imageView_home.setBackgroundResource(R.drawable.cartoon_pink_cute_gender);
                }
                username_home = findViewById(R.id.home_username);
                email_home.setText(module.getEmail());
                username_home.setText(module.getFirstname());
                ImageView profile_home_img;
                profile_home_img = findViewById(R.id.profile_img_home);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                storageRef.child(FirebaseAuth.getInstance().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(Home.this).load(uri).into(profile_home_img);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}