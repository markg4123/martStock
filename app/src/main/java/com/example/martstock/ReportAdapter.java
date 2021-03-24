package com.example.martstock;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private ArrayList<Report> reports;

    public ReportAdapter(ArrayList<Report> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_rcv, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report report = reports.get(position);

        holder.weightTextView.setText(report.getWeight());
        holder.priceTextView.setText("€" +report.getPrice()+ "/kg");
        holder.genderTextView.setText(report.getGender());
        holder.breedTextView.setText(report.getBreed());
        holder.dobTextView.setText(report.getDob()+ "\n");
        holder.dateTextView.setText("Date: "+report.getDate());


    }


    @Override
    public int getItemCount() {
        return reports.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView weightTextView, priceTextView, genderTextView, breedTextView, dobTextView, dateTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            weightTextView = (TextView) itemView.findViewById(R.id.weightTextView);
            priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
            genderTextView = (TextView) itemView.findViewById(R.id.genderTextView);
            breedTextView = (TextView) itemView.findViewById(R.id.breedTextView);
            dobTextView = (TextView) itemView.findViewById(R.id.dobTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);


        }
    }




}
