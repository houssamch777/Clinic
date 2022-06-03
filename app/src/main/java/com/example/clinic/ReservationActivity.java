package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {
    String str = null, dateOfRes;
    String txt;
    Date date1 = null;
    UserModule userModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        TextView listRes = findViewById(R.id.textView16);
        Bundle extras = getIntent().getExtras();
        CalendarView calendarView = findViewById(R.id.calendarView);
        String value = extras.getString("doctorName");
        FirebaseDatabase.getInstance().getReference().child("DoctorsList").child(value).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DoctorModel module = snapshot.getValue(DoctorModel.class);
                listRes.setText("");
                for (String day : module.getListDayOfWork()) {
                    txt = listRes.getText().toString();
                    listRes.setText(txt + " " + day);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        calendarView.setMinDate(calendarView.getDate());
        calendarView.setMaxDate(calendarView.getDate() + 2629800000L);
        Format f = new SimpleDateFormat("EEEE");
        str = f.format(calendarView.getDate());
        TextView textView = findViewById(R.id.textView20);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String sDate1;
                sDate1 = (dayOfMonth) + "/" + (month + 1) + "/" + year;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                str = f.format(date1);
                if (listRes.getText().toString().indexOf(str) == -1) {
                    Toast.makeText(getApplicationContext(), "doctor don't work in this day ", Toast.LENGTH_SHORT).show();
                }

                textView.setText(str + "\t" + sDate1);

            }
        });
        Button cancelBtn = findViewById(R.id.button9);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReservationActivity.this, Home.class);
                ReservationActivity.this.startActivity(i);
            }
        });
        Button reserve = findViewById(R.id.button8);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReservation(value);
            }
        });

    }

    private void setReservation(String doctorName) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        TextView listRes = findViewById(R.id.textView16);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Reservation");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userModule = snapshot.getValue(UserModule.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (listRes.getText().toString().indexOf(str) != -1) {
            DatabaseReference myRef1 = myRef.child(doctorName).child(date1.toString());
            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() < 20) {
                        myRef1.child(userModule.getFirstname() + " " + userModule.getLastname()).setValue(userModule);
                        Intent i = new Intent(ReservationActivity.this, Home.class);
                        ReservationActivity.this.startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "doctor reservations list is full this day pleas try other day", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "doctor don't work in this day ", Toast.LENGTH_SHORT).show();
        }


    }
}