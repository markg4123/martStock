package com.example.martstock;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class PricesAdapter extends RecyclerView.Adapter<PricesAdapter.ViewHolder> {
    private ArrayList<Prices> prices;

    public PricesAdapter(ArrayList<Prices> prices) {
        this.prices = prices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.priceslayout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prices price = prices.get(position);

        holder.animalTextView.setText(price.getAnimalType()+ "\n");
        holder.priceChangeTextView.setText("Price Change: " +price.getPriceChange()+ "\n");
        holder.ppkTextView.setText("Price: " +price.getPricePerKilo()+ "\n");


        if(price.getAnimalType().equalsIgnoreCase("Weekly kill")){
            holder.animalTextView.setText(price.getAnimalType()+ "\n");
            holder.priceChangeTextView.setText("Number Change: " +price.getPriceChange()+ "\n");
            holder.ppkTextView.setText("Number Killed: " +price.getPricePerKilo()+ "\n");
        }

        double change = Double.parseDouble(price.getPriceChange().replace(" Cent Per Kilo", ""));
        if(change>0){
            holder.animalTextView.setBackgroundColor(Color.parseColor("#FF4CAF50"));
            holder.priceChangeTextView.setBackgroundColor(Color.parseColor("#FF4CAF50"));
            holder.ppkTextView.setBackgroundColor(Color.parseColor("#FF4CAF50"));
        }
        if(change<0){
            holder.animalTextView.setBackgroundColor(Color.parseColor("#FFF10606"));
            holder.priceChangeTextView.setBackgroundColor(Color.parseColor("#FFF10606"));
            holder.ppkTextView.setBackgroundColor(Color.parseColor("#FFF10606"));
        }

    }


    @Override
    public int getItemCount() {
        return prices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView animalTextView, priceChangeTextView, ppkTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animalTextView = (TextView) itemView.findViewById(R.id.animalTextView);
            priceChangeTextView = (TextView) itemView.findViewById(R.id.priceChangeTextView);
            ppkTextView = (TextView) itemView.findViewById(R.id.ppkTextView);


        }
    }




}
