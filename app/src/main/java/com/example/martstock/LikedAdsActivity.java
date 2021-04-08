package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class LikedAdsActivity extends AppCompatActivity {
    RecyclerView likeAdsRCV;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;
    DatabaseReference ref;

    RecyclerView.Adapter adapter;
    final ArrayList<LikedAds> likedAds = new ArrayList<LikedAds>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_ads);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        likeAdsRCV = findViewById(R.id.likeAdsRCV);
        likeAdsRCV.setHasFixedSize(true);
        likeAdsRCV.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        ref = FirebaseDatabase.getInstance().getReference("LikedAd");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    LikedAds a = s.getValue(LikedAds.class);

                    if (userId.equals(a.getId())) {

                        likedAds.add(a);
                        Collections.reverse(likedAds);
                        adapter = new LikedAdsAdapter(likedAds, null);
                        likeAdsRCV.setAdapter(adapter);
                        adapter.notifyItemInserted(likedAds.size() - 1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(LikedAdsActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });


    }
 }
