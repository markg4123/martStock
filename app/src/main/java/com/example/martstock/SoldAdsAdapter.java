package com.example.martstock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoldAdsAdapter extends RecyclerView.Adapter<SoldAdsAdapter.ViewHolder> {
    private ArrayList<SoldAds> soldAds;

    public SoldAdsAdapter(ArrayList<SoldAds> soldAds) {
        this.soldAds = soldAds;

    }

    @NonNull
    @Override
    public SoldAdsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.soldadslayout, parent, false);

        return new SoldAdsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SoldAdsAdapter.ViewHolder holder, int position) {
        SoldAds oldAd = soldAds.get(position);



        holder.soldAdTitleText.setText( oldAd.getTitle()+"\n");
        holder.soldPriceText.setText( "Price Recieved: €" +oldAd.getPrice()+"\n");
        holder.soldMartText.setText( "Mart: " +oldAd.getMart()+"\n");
        holder.soldDateText.setText( "Date: " +oldAd.getDate()+"\n");
        holder.noSoldText.setText( "Amount Sold: " +oldAd.getNumSold()+"\n");
        holder.avgWText.setText( "Average Weight: " +oldAd.getAvgWeight()+"\n");
        holder.pricepkgText.setText("Price/kg: €" + oldAd.getPricePerKilo()+"\n");
        holder.askingPriceText.setText( "Asking Price: €" +oldAd.getAskingPrice()+"\n");
        if(oldAd.getDifference()>0)
        holder.differenceText.setText( "You sold for €" +oldAd.getDifference()+" more than you asked for!");
        else holder.differenceText.setText( "You sold for €" +oldAd.getDifference()+" less than you asked for!");

    }

    @Override
    public int getItemCount() {
        return soldAds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView soldAdTitleText, soldMartText, differenceText,soldDateText, noSoldText, avgWText, soldPriceText,pricepkgText,askingPriceText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            soldAdTitleText = (TextView) itemView.findViewById(R.id.soldAdTitleText);
            soldPriceText = (TextView) itemView.findViewById(R.id.soldPriceText);
            soldMartText = (TextView) itemView.findViewById(R.id.soldMartText);
            soldDateText = (TextView) itemView.findViewById(R.id.soldDateText);
            noSoldText = (TextView) itemView.findViewById(R.id.noSoldText);
            avgWText = (TextView) itemView.findViewById(R.id.avgWText);
            pricepkgText = (TextView) itemView.findViewById(R.id.pricepkgText);
            askingPriceText = (TextView) itemView.findViewById(R.id.askingPriceText);
            differenceText = (TextView) itemView.findViewById(R.id.differenceText);



        }
    }
}