package com.example.martstock;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {
    private ArrayList<Summary> summary;

    public SummaryAdapter(ArrayList<Summary> summary) {
        this.summary = summary;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_rcv, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Summary summaries = summary.get(position);

        final String bullockSum = summaries.getBullockAvgPrice();
        final String heiferSum = summaries.getHeiferAvgPrice();
        final String cowSum = summaries.getCowAvgPrice();
        final String calfSum = summaries.getCalfAvgPrice();

        holder.price1.setText("                Mart Summary\n" +
                "         Average Prices per Kilo\n\nBullocks: €" + bullockSum);
        holder.price2.setText("\nHeifer: €" + heiferSum);
        holder.price3.setText("\nFat/Dry Cows: €" + cowSum);
        holder.price4.setText("\nCalves: €" + calfSum);

        holder.graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(v.getContext(), GraphActivity.class);
                ii.putExtra("price1", bullockSum);
                ii.putExtra("price2", heiferSum);
                ii.putExtra("price3", calfSum);
                ii.putExtra("price4", cowSum);
                v.getContext().startActivity(ii);
            }
        });

    }


    @Override
    public int getItemCount() {
        return summary.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView price1, price2,price3,price4;
        public Button graphButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            price1 = (TextView) itemView.findViewById(R.id.price1);
            price2 = (TextView) itemView.findViewById(R.id.price2);
            price3 = (TextView) itemView.findViewById(R.id.price3);
            price4 = (TextView) itemView.findViewById(R.id.price4);
            graphButton = (Button) itemView.findViewById(R.id.graphButton);




        }
    }




}
