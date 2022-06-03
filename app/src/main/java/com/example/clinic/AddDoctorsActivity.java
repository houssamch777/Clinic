package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddDoctorsActivity extends AppCompatActivity {
    private String specialty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctors);
        Button addDoctor = findViewById(R.id.add_doctor);
        DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("DoctorsList");
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Specialty, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialty = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddDoctorsActivity.this, specialty, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                specialty = "General";
            }
        });
        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAddDoctor();
            }
        });
        Button cancel_btn = findViewById(R.id.cancel);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddDoctorsActivity.this, AdminActivity.class);
                AddDoctorsActivity.this.startActivity(i);
            }
        });

    }

    private void databaseAddDoctor() {
        String txtFirstname, txtLastname, txtEmail, txtPhone, txtGender, txtSpecialty, txtDays;
        EditText EditTxtFirstname, EditTxtLastname, EditTxtEmail, EditTxtPhone;
        EditTxtFirstname = findViewById(R.id.editTextFirstName);
        EditTxtLastname = findViewById(R.id.editTextLastName);
        EditTxtPhone = findViewById(R.id.editTextPhone);
        EditTxtEmail = findViewById(R.id.editTextEmail);
        txtFirstname = EditTxtFirstname.getText().toString().trim();
        txtEmail = EditTxtEmail.getText().toString().trim();
        txtLastname = EditTxtLastname.getText().toString().trim();
        txtPhone = EditTxtPhone.getText().toString().trim();
        Switch[] days = new Switch[7];
        ArrayList<String> list;
        list = new ArrayList<>();
        days[0] = findViewById(R.id.switch1);
        days[1] = findViewById(R.id.switch2);
        days[2] = findViewById(R.id.switch3);
        days[3] = findViewById(R.id.switch4);
        days[4] = findViewById(R.id.switch5);
        days[5] = findViewById(R.id.switch6);
        days[6] = findViewById(R.id.switch7);
        for (Switch day : days) {
            if (day.isChecked()) {
                list.add(day.getText().toString().trim());
            }
        }
        RadioButton male, female;
        male = findViewById(R.id.maleAdmin);
        female = findViewById(R.id.femaleAdmin);
        if (male.isChecked()) {
            txtGender = male.getText().toString().trim();
        } else {
            txtGender = female.getText().toString().trim();
        }

        if (!txtFirstname.isEmpty() && !txtLastname.isEmpty() && !txtEmail.isEmpty() && !txtPhone.isEmpty() && !specialty.isEmpty() && !txtGender.isEmpty() && !list.isEmpty()) {
            DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("DoctorsList");
            DoctorModel doctor1 = new DoctorModel(txtFirstname, txtLastname, txtEmail, txtPhone, specialty, txtGender, list);
            DatabaseReference newDoctors = myRef1.child(doctor1.getFirstname() + " " + doctor1.getLastname());
            newDoctors.setValue(doctor1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AddDoctorsActivity.this, "doctor Add", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddDoctorsActivity.this, AdminActivity.class);
                    AddDoctorsActivity.this.startActivity(i);
                }
            });
        } else {
            Toast.makeText(AddDoctorsActivity.this, "input empty", Toast.LENGTH_SHORT).show();
        }
    }
}