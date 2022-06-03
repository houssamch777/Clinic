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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminEditDoctorActivity extends AppCompatActivity {
    private String specialty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_doctor);
        Bundle extras = getIntent().getExtras();
        Spinner spinner = findViewById(R.id.spinnerEdit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Specialty, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialty = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (extras != null) {
            String value = extras.getString("name");
            setupEditPage(value);
        }
        Button cancel_Edit = findViewById(R.id.edit_cancel);
        cancel_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminEditDoctorActivity.this, AdminActivity.class);
                AdminEditDoctorActivity.this.startActivity(i);
            }
        });
        Button editBtn = findViewById(R.id.edit_doctor);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extras != null) {
                    String value = extras.getString("name");
                    databaseAddDoctor(value);
                }

            }
        });

    }

    private void databaseAddDoctor(String name) {
        EditText EditTxtFirstname, EditTxtLastname, EditTxtEmail, EditTxtPhone;
        String txtFirstname, txtLastname, txtEmail, txtPhone, txtGender = "None", txtSpecialty, txtDays;
        EditTxtFirstname = findViewById(R.id.editFirstName);
        EditTxtLastname = findViewById(R.id.editLastName);
        EditTxtPhone = findViewById(R.id.editPhone);
        EditTxtEmail = findViewById(R.id.editEmail);
        Switch[] days = new Switch[7];
        days[0] = findViewById(R.id.switchEdit1);
        days[1] = findViewById(R.id.switchEdit2);
        days[2] = findViewById(R.id.switchEdit3);
        days[3] = findViewById(R.id.switchEdit4);
        days[4] = findViewById(R.id.switchEdit5);
        days[5] = findViewById(R.id.switchEdit6);
        days[6] = findViewById(R.id.switchEdit7);
        RadioButton maleEdit, femaleEdit;
        maleEdit = findViewById(R.id.maleEdit);
        femaleEdit = findViewById(R.id.femaleEdit);
        ArrayList<String> list;
        list = new ArrayList<>();
        for (Switch day : days) {
            if (day.isChecked()) {
                list.add(day.getText().toString().trim());
            }
        }
        if (maleEdit.isChecked()) {
            txtGender = maleEdit.getText().toString().trim();
        }
        if (femaleEdit.isChecked()) {
            txtGender = femaleEdit.getText().toString().trim();
        }
        txtFirstname = EditTxtFirstname.getText().toString().trim();
        txtEmail = EditTxtEmail.getText().toString().trim();
        txtLastname = EditTxtLastname.getText().toString().trim();
        txtPhone = EditTxtPhone.getText().toString().trim();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("DoctorsList").child(name);
        myRef.removeValue();
        if (!txtFirstname.isEmpty() && !txtLastname.isEmpty() && !txtEmail.isEmpty() && !txtPhone.isEmpty() && !specialty.isEmpty() && !txtGender.isEmpty() && !list.isEmpty()) {
            DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference().child("DoctorsList");
            DoctorModel doctor1 = new DoctorModel(txtFirstname, txtLastname, txtEmail, txtPhone, specialty, txtGender, list);
            DatabaseReference newDoctors = myRef1.child(doctor1.getFirstname() + " " + doctor1.getLastname());
            newDoctors.setValue(doctor1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AdminEditDoctorActivity.this, "doctor Add", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AdminEditDoctorActivity.this, AdminActivity.class);
                    AdminEditDoctorActivity.this.startActivity(i);
                }
            });
        } else {
            Toast.makeText(AdminEditDoctorActivity.this, "input empty", Toast.LENGTH_SHORT).show();
        }
    }

    void setupEditPage(String doctorName) {

        EditText EditTxtFirstname, EditTxtLastname, EditTxtEmail, EditTxtPhone;
        EditTxtFirstname = findViewById(R.id.editFirstName);
        EditTxtLastname = findViewById(R.id.editLastName);
        EditTxtPhone = findViewById(R.id.editPhone);
        EditTxtEmail = findViewById(R.id.editEmail);
        Switch[] days = new Switch[7];
        days[0] = findViewById(R.id.switchEdit1);
        days[1] = findViewById(R.id.switchEdit2);
        days[2] = findViewById(R.id.switchEdit3);
        days[3] = findViewById(R.id.switchEdit4);
        days[4] = findViewById(R.id.switchEdit5);
        days[5] = findViewById(R.id.switchEdit6);
        days[6] = findViewById(R.id.switchEdit7);
        RadioButton maleEdit, femaleEdit;
        maleEdit = findViewById(R.id.maleEdit);
        femaleEdit = findViewById(R.id.femaleEdit);

        FirebaseDatabase.getInstance().getReference().child("DoctorsList").child(doctorName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DoctorModel module = snapshot.getValue(DoctorModel.class);
                EditTxtEmail.setText(module.getEmail());
                EditTxtFirstname.setText(module.getFirstname());
                EditTxtLastname.setText(module.getLastname());
                EditTxtPhone.setText(module.getPhone());
                for (String day : module.getListDayOfWork()) {
                    if (day.equals("Sunday")) {
                        days[0].setChecked(true);
                    }
                    if (day.equals("Monday")) {
                        days[1].setChecked(true);
                    }
                    if (day.equals("Tuesday.")) {
                        days[2].setChecked(true);
                    }
                    if (day.equals("Wednesday")) {
                        days[3].setChecked(true);
                    }
                    if (day.equals("Thursday")) {
                        days[4].setChecked(true);
                    }
                    if (day.equals("Friay")) {
                        days[5].setChecked(true);
                    }
                    if (day.equals("Saturday")) {
                        days[6].setChecked(true);
                    }
                }
                if (module.getGender().equals("Male")) {
                    maleEdit.setChecked(true);
                }
                if (module.getGender().equals("Female")) {
                    femaleEdit.setChecked(true);
                }
                specialty = module.getSpecialty();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}