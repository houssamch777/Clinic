package com.example.clinic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> list;
    ArrayList<String> dateList;

    public ReservationAdapter(Context context, ArrayList<String> list, ArrayList<String> dateList) {
        this.context = context;
        this.list = list;
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public ReservationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reservation_row, parent, false);
        return new ReservationAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.MyViewHolder holder, int position) {
        String name = list.get(position);
        String date = dateList.get(position);
        holder.name.setText(name);
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView12);
            date = itemView.findViewById(R.id.textView13);
        }
    }
}
