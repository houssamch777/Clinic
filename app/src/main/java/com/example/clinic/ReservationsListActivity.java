package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ReservationsListActivity extends AppCompatActivity {
    String name = null, id_name = "";
    ArrayList<String> list;
    ArrayList<String> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_list);
        TextView textView = findViewById(R.id.textView11);
        Bundle extras = getIntent().getExtras();
        list = new ArrayList<String>();
        date = new ArrayList<String>();
        ReservationAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.resList);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ReservationAdapter(this, list, date);
        recyclerView.setAdapter(adapter);

        if (extras != null) {
            name = extras.getString("name");
            textView.setText("Dr' " + name);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Reservation").child(name.toLowerCase());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot data : snapshot.getChildren()) {
                        date.add(data.getKey());
                        id_name = null;
                        for (DataSnapshot sick_name : data.getChildren()
                        ) {
                            if (id_name == null) {
                                id_name = sick_name.getKey() + "\n";
                            } else {
                                id_name += sick_name.getKey() + "\n";
                            }
                        }
                        list.add(id_name);
                    }
                    if (list.isEmpty() && date.isEmpty()) {
                        Toast.makeText(ReservationsListActivity.this, "there is no reservation", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservationsListActivity.this, Objects.requireNonNull(error.getMessage()), Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button back;
        back = findViewById(R.id.button6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReservationsListActivity.this, AdminActivity.class);
                ReservationsListActivity.this.startActivity(i);
            }
        });


    }

}