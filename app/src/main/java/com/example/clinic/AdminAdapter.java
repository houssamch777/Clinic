package com.example.clinic;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    Context context;
    ArrayList<DoctorModel> list;

    public AdminAdapter(Context context, ArrayList<DoctorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_item_all_doctors, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DoctorModel doctor = list.get(position);
        holder.email.setText(doctor.getEmail());
        holder.name.setText(doctor.getFirstname() + " " + doctor.getLastname());
        holder.specialty.setText(doctor.getSpecialty());
        holder.gender.setText(doctor.getGender());
        holder.phone_number.setText(doctor.getPhone());
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + doctor.getEmail()));
                context.startActivity(intent);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AdminEditDoctorActivity.class);
                i.putExtra("name", holder.name.getText().toString().trim());
                context.startActivity(i);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("DoctorsList").child(holder.name.getText().toString());
                myRef.removeValue();
                Intent i = new Intent(context, AdminActivity.class);
                context.startActivity(i);

            }
        });
        holder.res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ReservationsListActivity.class);
                i.putExtra("name", holder.name.getText().toString().trim());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, specialty, phone_number, gender;
        TextView edit, delete, res;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameAdmin);
            specialty = itemView.findViewById(R.id.textSpecialtyAdmin);
            email = itemView.findViewById(R.id.text_EmailAdmin);
            gender = itemView.findViewById(R.id.textGenderAdmin);
            phone_number = itemView.findViewById(R.id.textPhoneAdmin);
            edit = itemView.findViewById(R.id.textView4);
            delete = itemView.findViewById(R.id.textView6);
            res = itemView.findViewById(R.id.textView9);
        }
    }
}
