package com.example.clinic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<DoctorModel> list;

    public MyAdapter(Context context, ArrayList<DoctorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.doctor_row, parent, false);
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
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ReservationActivity.class);
                i.putExtra("doctorName", holder.name.getText().toString().trim());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, specialty, phone_number, gender;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.doctor_name_list);
            specialty = itemView.findViewById(R.id.doctor_specialty_list);
            email = itemView.findViewById(R.id.doctor_email_list);
            gender = itemView.findViewById(R.id.textgender);
            phone_number = itemView.findViewById(R.id.textphone);
            button = itemView.findViewById(R.id.button4);


        }
    }
}
