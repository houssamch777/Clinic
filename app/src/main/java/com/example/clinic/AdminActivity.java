package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setupAdminPage();
        Button logOutBtn;
        logOutBtn = findViewById(R.id.button3);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(AdminActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AdminActivity.this, LogInActivity.class);
                AdminActivity.this.startActivity(i);
            }
        });
        Button addDoctor = findViewById(R.id.button5);
        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, AddDoctorsActivity.class);
                AdminActivity.this.startActivity(i);
            }
        });
    }

    public void setupAdminPage() {
        RecyclerView recyclerView;
        DatabaseReference database;
        AdminAdapter myAdapter;
        ArrayList<DoctorModel> list;
        recyclerView = findViewById(R.id.rv_admin);
        database = FirebaseDatabase.getInstance().getReference().child("DoctorsList");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        myAdapter = new AdminAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                    list.add(doctor);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}