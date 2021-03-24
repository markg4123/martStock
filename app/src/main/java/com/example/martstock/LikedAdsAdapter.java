package com.example.martstock;


import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LikedAdsAdapter extends RecyclerView.Adapter<LikedAdsAdapter.ViewHolder>{
    private ArrayList<LikedAds> likedAds;
    private ArrayList<Ad> ads;

    Uri myUri;
    String uri;
    DatabaseReference fireDB;
     long difference_In_Days;
     String key, key1;


    public LikedAdsAdapter(ArrayList<LikedAds> likedAds,ArrayList<Ad> ads ) {
        this.likedAds = likedAds;
        this.ads = ads;

    }
    @NonNull
    @Override
    public LikedAdsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.likedadslayout, parent, false);


        return new LikedAdsAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        LikedAds likedAd = likedAds.get(position);

        holder.likedadTitleText.setText(likedAd.getAdTitle() + "\n");
        holder.likedpriceText.setText("Price \n €" + likedAd.getPrice() + "\n");
        holder.likedmartText.setText(likedAd.getMart() + " " + likedAd.getDate() + "\n");
        holder.likedbreedText.setText("Breed: " + likedAd.getBreed() + "\n");
        holder.likedageText.setText("Age: " + likedAd.getAge() + "\n");
        holder.likeddescText.setText(likedAd.getDescription() + "\n");

        uri = likedAd.getUrl();
        myUri = Uri.parse(uri);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                fireDB = FirebaseDatabase.getInstance().getReference("LikedAds");
                fireDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            LikedAds likedAds = dataSnapshot.getValue(LikedAds.class);

                            Intent intent = ((Activity) v.getContext()).getIntent();
                            key = intent.getStringExtra("key");

                            key1 = dataSnapshot.getKey();

                            if(key.equals(key1)){
                                fireDB.child(key).removeValue();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        try {
            Date martDate = new SimpleDateFormat("dd-MM-yy").parse(likedAd.getDate());
            Date today = Calendar.getInstance().getTime();

            long difference_In_Time = martDate.getTime()- today.getTime();
            difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

            if(difference_In_Days>0) {
                difference_In_Days = difference_In_Days + 1;
                holder.numOfDays.setText("Mart is in: " + difference_In_Days + " days");
            }

            if(difference_In_Days == 0)
                holder.numOfDays.setText("The mart is today!");

            if(difference_In_Days < 0){
                holder.numOfDays.setText("The mart was " + Math.abs(difference_In_Days )+ " days ago!");
            }




        } catch (ParseException e) {
            e.printStackTrace();
        }



        Picasso.get().load(myUri).into(holder.likedimage);



    }






    @Override
    public int getItemCount() {
        return likedAds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView likedadTitleText, likedmartText,likedbreedText,likedageText,likeddescText,likedpriceText,numOfDays;
        Button removeButton;
        ImageView likedimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            likedadTitleText = (TextView) itemView.findViewById(R.id.likedadTitleText);
            likedmartText = (TextView) itemView.findViewById(R.id.likedmartText);
            likedbreedText = (TextView) itemView.findViewById(R.id.likedbreedText);
            likedageText = (TextView) itemView.findViewById(R.id.likedageText);
            likeddescText = (TextView) itemView.findViewById(R.id.likeddescText);
            likedpriceText = (TextView) itemView.findViewById(R.id.likedpriceText);
            numOfDays = (TextView) itemView.findViewById(R.id.numOfDays);
            likedimage = (ImageView) itemView.findViewById(R.id.likedimage);
            removeButton = (Button) itemView.findViewById(R.id.removeButton);



        }


        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();




        }
    }
}
