package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorsListActivity extends AppCompatActivity {
    ArrayList<DoctorModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("specialty");
            setupSpecialtyPage(value);
        }
        FloatingActionButton back_Button;
        back_Button = findViewById(R.id.list_backButton);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorsListActivity.this, Home.class);
                DoctorsListActivity.this.startActivity(i);
            }
        });


    }

    private void setupSpecialtyPage(String value) {

        RecyclerView recyclerView;
        DatabaseReference database;
        MyAdapter myAdapter;
        setContentView(R.layout.activity_doctors_list);
        recyclerView = findViewById(R.id.rv_doctors);
        database = FirebaseDatabase.getInstance().getReference().child("DoctorsList");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        if (value.equalsIgnoreCase("all")) {
            list.clear();
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
        if (value.equalsIgnoreCase("cardiology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("gynecology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("ophthalmology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("facial plastic surgery")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("oral health")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("orthopedics")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("rhinology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("neurology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("neurosurgery")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("dermatology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("pulmonology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("hepatology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("urology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("gastroenterology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("plastic surgery")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (value.equalsIgnoreCase("otology")) {
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DoctorModel doctor = datasnapshot.getValue(DoctorModel.class);
                        if (doctor.getSpecialty().equalsIgnoreCase(value)) {
                            list.add(doctor);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        SearchView searchView;

        searchView = findViewById(R.id.searchView);


        if (searchView != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

    }

    private void search(String str) {
        ArrayList<DoctorModel> newList = new ArrayList<>();
        for (DoctorModel model : list) {
            String name = model.getFirstname().toLowerCase() + " " + model.getLastname().toLowerCase();
            if (name.contains(str.toLowerCase())) {
                newList.add(model);
            }
        }
        MyAdapter myAdapter;
        RecyclerView recyclerView = findViewById(R.id.rv_doctors);
        myAdapter = new MyAdapter(this, newList);
        recyclerView.setAdapter(myAdapter);
    }
}